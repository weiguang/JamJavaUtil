package com.util;

import com.alibaba.fastjson.JSONObject;
import com.test.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author: Chen weiguang <chen2621978@163.com.com>
 * @create: 2018/09/06 11:22
 **/
public class DatabaseUtil {
    private static final Logger logger =  LoggerFactory.getLogger(DatabaseUtil.class);

    // JDBC driver name and database URL
    //static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; //8.0以下版本
//    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; //8.0以上版本
    static final String JDBC_DRIVER = "org.sqlite.JDBC"; //sqlite3
    static final String DB_URL = ResourceBundle.getBundle("jdbc").getString("jdbcUrl");

    //  Database credentials
    static final String USER = ResourceBundle.getBundle("jdbc").getString("user" );
    static final String PASS = ResourceBundle.getBundle("jdbc").getString("password");

    public static void main(String[] args) {
        User user = new User();
        user.setName("李四1");
        user.setBirthday(new Date());
        insertEntity(User.class,user,"user");

        String sql = "select * from  user";
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

    public static Connection getJDBConnect() {
        Connection conn = null;
            //STEP 2: Register JDBC driver
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connected database successfully...");
        return  conn;
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
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return list;
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
        try {
            Connection con = getJDBConnect();
            if(con != null && !con.isClosed()) {
                System.out.println("Succeeded connecting to the Database!");
            }else {
                logger.info("数据库数据失败获取！！");
                return ;
            }
            System.out.println("准备插入：" + list.size());
            //要执行的SQL语句
            PreparedStatement psql;
            //预处理添加数据，其中有两个参数--“？”
            String sql = getInsertSql(tableFields, table);
            System.out.println("insert sql:" + sql);
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
            con.close();
        }  catch(SQLException e) {
            //数据库连接失败异常处理
            logger.error("insert error!!", e);
            e.printStackTrace();
        }catch (Exception e) {
            // TODO: handle exception
            logger.error("insert error!!", e);
            e.printStackTrace();
        }finally{
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

        System.out.println(sql);
        PreparedStatement ps = null;
        try {
            Connection conn = getJDBConnect();
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
                            System.out.println(fields[j].get(obj) + " ");
                            ps.setObject(j + 1 - keyFlag, fields[j].get(obj));
                        } else {
                            System.out.println(fields[j].get(obj) + " ");
                            ps.setObject(j + 1 - keyFlag, null);
                        }
            }
            System.out.println(ps.toString());
            ps.execute();
           // conn.commit();
            ps.close();
        } catch (Exception e1) {
            e1.printStackTrace();
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
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    public static  void outputFile (String path, String fileName, String content) {
        if(!new File(path).exists()) {
            System.out.println("目录不存在");return;
        }
        File writeMame = new File(path+ fileName); // 相对路径，如果没有则要建立一个新的output。txt文件
        try {
            OutputStreamWriter write = new OutputStreamWriter(
                    new FileOutputStream(writeMame), "gbk");
            BufferedWriter writer = new BufferedWriter(write);
            writer.write(content);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
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
            System.out.println(sbName.toString() + "\n");
            System.out.println(sbComment.toString() + "\n");
            rs.close();
            re[0] = listname.toArray(new String[listname.size()]);
            re[1] = listComment.toArray(new String[listComment.size()]);
        } catch (SQLException e) {
            e.printStackTrace();
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
