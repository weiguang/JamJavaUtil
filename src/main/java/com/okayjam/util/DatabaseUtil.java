package com.okayjam.util;

import com.alibaba.fastjson.JSONObject;
import com.okayjam.test.User;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.*;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * @author: Chen weiguang <chen2621978@gmail.com>
 * @create: 2018/09/06 11:22
 **/
public class DatabaseUtil {
    private static final Logger logger =  LoggerFactory.getLogger(DatabaseUtil.class);

    // JDBC driver name and database URL
    static final String JDBC_DRIVER =  ResourceBundle.getBundle("jdbc").getString("driverClassName");
    static final String DB_URL = ResourceBundle.getBundle("jdbc").getString("url");
    //  Database credentials
    static final String USER = ResourceBundle.getBundle("jdbc").getString("user" );
    static final String PASS = ResourceBundle.getBundle("jdbc").getString("password");
    static final int MAX_ACTIVE = Integer.valueOf(ResourceBundle.getBundle("jdbc").getString("maxActive"));


    static Connection conn = null;

    static  DataSource DATASOURCE;

    public static void main(String[] args) {
        String table = "jam1";
        User user = new User();
        user.setName("李四2");
        user.setAge(12);
        user.setBirthday(new Date());
        user.setWeight(65.3);

        insertEntity(User.class,user,table);

        String sql = "select * from  " + table ;
        List<User> list = null;
        try {
            list = (List<User>)selectResultToList(sql,User.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        for (Object o : list) {
            System.out.println(o);
        }
    }

    public static void close(Closeable conn) {
        try {
            if(conn != null)  {conn.close();}
        } catch (Exception e) {
            logger.error("SQL error:", e);
        }
    }

    public static void close(AutoCloseable conn) {
        try {
            if(conn != null)  {conn.close();}
        } catch (Exception e) {
            logger.error("SQL error:", e);
        }
    }


    /**
     * 初始化线程池
     */
    private static void initPool() {
        try {
            BasicDataSource ds = new BasicDataSource();
            ds.setDriverClassName(JDBC_DRIVER);
            ds.setUsername(USER);
            ds.setPassword(PASS);
            ds.setUrl(DB_URL);
            // 初始的连接数；
            ds.setMaxTotal(MAX_ACTIVE);
            DATASOURCE = ds;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 从线程池获取连接
     */
    public static Connection getJDBConnect() {
        if(DATASOURCE == null) {
            initPool();
        }
        if (DATASOURCE != null) {
            try {
                return DATASOURCE.getConnection();
            } catch (SQLException e) {
                logger.error("SQL get Connection error:", e);
            }
        }
        return null;
    }


    /**
     * 原始的获取连接的方式
     * @return
     */
    public static Connection getJDBConnectOrigin() {
        try {
            if(conn == null || conn.isClosed()) {
                synchronized (DatabaseUtil.class) {
                    if(conn == null || conn.isClosed()) {
                        Class.forName(JDBC_DRIVER);
                        conn = DriverManager.getConnection(DB_URL, USER, PASS);
                    }
                }
            }
        }catch (ClassNotFoundException e) {
            logger.error("ClassNotFoundException error:", e);
        } catch (SQLException e) {
            logger.error("SQL error:", e);
        }
        return  conn;
    }

    /**
     * 执行sql语句
     * @param sql
     * @return
     */
    public static int executeSQL(String sql ) {
        Connection conn = getJDBConnect();
        int i = 0;
        PreparedStatement pstmt;
        if(conn == null) {return i;}
        try {
            pstmt =  conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            logger.info("executeSQL resutl: " + i);
            pstmt.close();
        } catch (SQLException e) {
            logger.error("SQL error:", e);
        }finally {
            close(conn);
        }
        return i;
    }


    /**
     * 查找sql返回的数量
     * @param sql
     * @return
     */
    public static int selectCount(String sql) {
        int count = -1;
        Connection conn = getJDBConnect();
        if(conn == null) {return count;}
        try {
            ResultSet rs = conn.prepareStatement(sql).executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        }catch(SQLException e){
            e.printStackTrace();
        } finally {
            close(conn);
        }
        return count;
    }


    /**
     *  把查询结果封装成JSONObject对象
     * @param sql 查询sql
     * @return
     */
    public static List<JSONObject> selectResultToList(String sql) {
        List<JSONObject>  list = new ArrayList<>();
        try {
          list =  (List<JSONObject>) selectResultToList(sql, JSONObject.class);
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException error:", e);
        } catch (InstantiationException e) {
            logger.error("InstantiationException error:", e);
        }
        return  list;
    }

    /**
     *  把查询结果封装成Java对象
     * @param sql 查询sql
     * @param clazz 要返回的类型
     * @return list
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static List<?> selectResultToList(String sql, Class clazz) throws IllegalAccessException, InstantiationException {
        Connection conn = getJDBConnect();
        List list = new ArrayList<>();
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCountcount = rsmd.getColumnCount();
            while (rs.next()) {
                JSONObject jo = new JSONObject();
                for (int i = 0; i < columnCountcount; i++) {
                    Object o;
                    if(rsmd.getColumnName(i+1).equalsIgnoreCase("DATE") || rsmd.getColumnName(i+1).equalsIgnoreCase("DATETIME")) {
                        jo.put(rsmd.getColumnLabel(i+1),rs.getDate(i+1));
                    }else {
                         o  = rs.getObject(i+1);
                        jo.put(rsmd.getColumnLabel(i+1), o);
                    }
                }
                if(clazz == null || clazz.equals(JSONObject.class)){
                    list.add(jo);
                }else {
                    list.add(jo.toJavaObject(clazz));
                }
            }
        } catch (SQLException e) {
            logger.error("SQL error:", e);
        } finally {
            close(conn);
        }
        return list;
    }


    /**
     * 数据库更新
     * @param jsonObject json对象
     * @param jsonFields json字段
     * @param table 数据表
     * @param tableFields 对应的表字段（需要与json字段的顺序对应）
     * @param whereSql 更新的条件
     */
    public static int update(JSONObject jsonObject, String[] jsonFields, String table, String[] tableFields, String whereSql) {
        int row = -1;
        Connection con = getJDBConnect();
        if (null == con ) { logger.error("数据库连接失败！！"); return row;}
        PreparedStatement pstmt ;
        try {
          //  logger.info("update sql: "+ getUpdateSql(tableFields, table) + "\t where \t"  + whereSql);
            pstmt = con.prepareStatement(getUpdateSql(tableFields, table) + "\t where \t"  + whereSql);

            for (int i = 0; i < jsonFields.length; i++) {
                Object fieldObject = jsonObject.get(jsonFields[i]);
                if (fieldObject != null) {
                    if( fieldObject.getClass().getSimpleName().equalsIgnoreCase("date")
                            || fieldObject.getClass().getSimpleName().equalsIgnoreCase("datetime")) {
                        pstmt.setTimestamp(i+1,  new Timestamp( jsonObject.getDate(jsonFields[i]).getTime() ) );
                    }else{
                        pstmt.setObject(i+1, fieldObject);
                    }
                }else {
                    pstmt.setObject(i+1, fieldObject);
                }
            }
            row = pstmt.executeUpdate();
            //logger.info("update row: " + row);
        }catch (SQLException e) {
            //数据库连接失败异常处理
            logger.error("update error!!", e);
        } finally {
            close(conn);
        }
        return row;
    }

    /**
     * 获取更新的语句
     * @param tableFields
     * @param table
     * @return
     */
    public static String getUpdateSql(String[] tableFields, String table) {
        StringBuilder sbField = new StringBuilder();
        if (tableFields != null && tableFields.length >0) {
            for (String tableField : tableFields) {
                if(sbField.length() != 0) {sbField.append(", "); }
                sbField.append(tableField);
                sbField.append(" = ? ");
            }
        }
        return " update \t" + table + "\t set \t" + sbField.toString() ;
    }



    /**
     *
     * @param list 要插入的数据
     * @param jsonFields 要插入的json字段，请保持tableFields 对应顺序一致
     * @param tableFields 要插入的表字段，请保持jsonFields 对应顺序一致
     * @param table 需要插入的表
     */
    public static void insert(List<JSONObject> list, String[] jsonFields, String[] tableFields, String table) {
        //最大批量是插入数量
        int maxbatchSize = 1000;
        Connection con = getJDBConnect();
        try {

            if(con != null && !con.isClosed()) {
                //logger.info("Succeeded connecting to the Database!");
            }else {
                logger.error("SQL error: 数据库数据失败获取！！");
                return ;
            }
            logger.info("准备插入：" + list.size());
            //要执行的SQL语句
            PreparedStatement psql;
            //预处理添加数据，其中有两个参数--“？”
            String sql = getInsertSql(tableFields, table);
            logger.info("insert sql:" + sql);
            psql = con.prepareStatement(sql);
            int insertCount = 0;
            for (JSONObject obj : list) {
                for (int i = 0; i < tableFields.length; i++) {
                    Object fieldObject = obj.get(jsonFields[i]);
                    if (fieldObject != null) {
                        if( fieldObject.getClass().getSimpleName().equalsIgnoreCase("date")
                                || fieldObject.getClass().getSimpleName().equalsIgnoreCase("datetime")) {
                            psql.setObject(i+1, obj.getDate(jsonFields[i]));
                        }else {
                            psql.setObject(i+1, fieldObject);
                        }
                    }else {
                        psql.setObject(i+1, fieldObject);
                    }
                }
                psql.addBatch(); // 加入批量更新
                if(++insertCount >= maxbatchSize) {
                    psql.executeBatch();
                    psql.clearBatch();
                }
            }
            psql.executeBatch();
            psql.close();
        }  catch(SQLException e) {
            //数据库连接失败异常处理
            logger.error("insert error!!", e);
//            e.printStackTrace();
        }catch (Exception e) {
            // TODO: handle exception
            logger.error("insert error!!", e);
//            e.printStackTrace();
        }finally{
            close(conn);
        }
    }

    /**
     * 构造批量插入的sql
     * @param tableFields 需要插入的字段
     * @param table 表
     * @return 返回sql
     */
    public static String getInsertSql(String[] tableFields, String table) {
        StringBuilder sbField = new StringBuilder();
        StringBuilder sbMark  = new StringBuilder();
        if (tableFields != null && tableFields.length >0) {
            for (String tableField : tableFields) {
                if(sbField.length() != 0) {sbField.append(", "); sbMark.append(",");}
                sbField.append(tableField);
                sbMark.append("?");
            }

        }
        return "insert into "+ table + " ( " + sbField.toString() + ") values( " + sbMark.toString() + ")";
    }



    public static void insertEntity(Class c, Object obj, String table) {
        if (obj == null || c.getSimpleName().equals(obj.getClass().getName())) {return;}
        Field[] fields = obj.getClass().getDeclaredFields();
        int fieldSize = fields.length;
        String tableName = table; // c.getSimpleName().toLowerCase();// person
        String autoIncKet = "id";
        StringBuffer cloName = new StringBuffer();
        cloName.append("(");
        for (Field field : fields) {
            if(!field.getName().equals(autoIncKet)){
                cloName.append(field.getName() + ",");
            }
        }
        cloName.deleteCharAt(cloName.length() - 1 );
        cloName.append(")");
        StringBuffer sql = new StringBuffer("insert into " + tableName + cloName.toString()
                + " values(");


        for (int i = 0; i < fieldSize; i++) {
           if(!fields[i].getName().equals(autoIncKet)){
               sql.append("?,");
           }
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");

        logger.info(sql.toString());
        PreparedStatement ps = null;
        Connection conn = getJDBConnect();
        try {

            ps = conn.prepareStatement(sql.toString());
            int keyFlag = 0;
            for (int j = 0; j < fieldSize; j++) {
                if(fields[j].getName().equals(autoIncKet)){
                    keyFlag++;
                    continue;
                }
                fields[j].setAccessible(true);
                        if (fields[j].get(obj) != null
                                && !"".equals(fields[j].get(obj))
                                && !"null".equals(fields[j].get(obj))) {
                            logger.info(fields[j].get(obj) + " ");
                            ps.setObject(j + 1 - keyFlag, fields[j].get(obj));
                        } else {
                            logger.info(fields[j].get(obj) + " ");
                            ps.setObject(j + 1 - keyFlag, null);
                        }
            }
            logger.info(ps.toString());
            ps.execute();
           // conn.commit();
            ps.close();
        } catch (Exception e1) {
            logger.error("SQL error:", e1);
        } finally {
            close(conn);
        }
    }


    /**
     * 返回表名的表相关信息
     * @param database 数据库
     * @param table 数据表
     */
    public static String getTableInfo(String database, String table) {
        Connection conn = DatabaseUtil.getJDBConnect();
        Statement stmt ;
        StringBuilder sb  = new StringBuilder();
        try {
            stmt = conn.createStatement();
            String sql = "select column_name, data_type, is_nullable, column_default,column_comment " +
                    "from information_schema.`COLUMNS`  where table_name = '"+ table
                    +"' and table_schema = '"+ database +"';";
            ResultSet rs = stmt.executeQuery(sql);
            //Extract data from result set
            sb.append("字段名,类型,必填,默认,备注\n");
            String yes = "是";
            while(rs.next()){
                //Retrieve by column name
                String column_name = rs.getString("column_name"); //字段名字
                String data_type = rs.getString("data_type"); //字段注释
                String is_nullable = rs.getString("is_nullable"); //字段名字
                String column_default = rs.getString("column_default"); //字段名字
                String column_comment = rs.getString("column_comment"); //字段注释
                sb.append(column_name+","+data_type + ",");
                sb.append(is_nullable.equals("NO")?  yes : "");
                sb.append(","+ ((column_default == null || column_default.equals("null"))? "":column_default)  );
                sb.append("," + column_comment + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(conn);
        }
        logger.info(sb.toString());
        return sb.toString();
    }

    public static  void outputFile (String path, String fileName, String content) {
        if(!new File(path).exists()) {
            logger.info("目录不存在");return;
        }
        File writeMame = new File(path+ fileName); // 相对路径，如果没有则要建立一个新的output。txt文件
        try {
            OutputStreamWriter write = new OutputStreamWriter(
                    new FileOutputStream(writeMame), "gbk");
            BufferedWriter writer = new BufferedWriter(write);
            writer.write(content);
            writer.close();
        } catch (Exception e) {
            logger.error("Exception error:", e);
        }
    }

    /**
     * 返回表名的表字段和对应的注释
     * @param database 数据库
     * @param table 数据表
     */
    public static String[][] getTableFiels(String database, String table) {
        Connection conn = getJDBConnect();
        Statement stmt ;
        String re[][] = new String[2][];
        try {
            stmt = conn.createStatement();
            String sql = "select column_name,column_comment " +
                    "from information_schema.`COLUMNS`  where table_name = '"+ table
                    +"' and table_schema = '"+ database +"';";
            ResultSet rs = stmt.executeQuery(sql);
            //Extract data from result set
            StringBuilder sbName  = new StringBuilder();
            sbName.append("{");
            StringBuilder sbComment  = new StringBuilder();
            sbComment.append("{");
            List<String> listname = new ArrayList<String>(30);
            List<String> listComment = new ArrayList(30);
            while(rs.next()){
                //Retrieve by column name
                String column_name = rs.getString("column_name"); //字段名字
                String column_comment = rs.getString("column_comment"); //字段注释
                ex(sbName, listname, column_name);
                ex(sbComment, listComment, column_comment);
            }
            sbName.append("}");
            sbComment.append("}");
            logger.info(sbName.toString() + "\n");
            logger.info(sbComment.toString() + "\n");
            rs.close();
            re[0] = listname.toArray(new String[listname.size()]);
            re[1] = listComment.toArray(new String[listComment.size()]);
        } catch (SQLException e) {
            logger.error("SQL error:", e);
        } finally {
            close(conn);
        }
        return re;
    }

    private static void ex(StringBuilder sbName, List<String> listname, String column_name) {
        if(column_name!=null ){
            if(sbName.length() >1 )	sbName.append(", ");
            sbName.append("\"" + column_name + "\"");
            listname.add(column_name);
        }
    }

}
