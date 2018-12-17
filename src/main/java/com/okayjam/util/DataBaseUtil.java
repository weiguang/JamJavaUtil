package com.okayjam.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author: Chen weiguang <chen2621978@163.com.com>
 * @create: 2018/09/06 11:22
 **/
public class DataBaseUtil {

    // JDBC driver name and database URL
    //static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; //8.0以下版本
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; //8.0以上版本
    static final String DB_URL = ResourceBundle.getBundle("jdbc").getString("jdbcUrl");

    //  Database credentials
    static final String USER = ResourceBundle.getBundle("jdbc").getString("user");
    static final String PASS = ResourceBundle.getBundle("jdbc").getString("password");

    public static void main(String[] args) {
        String database = "ops_database";
        String table = "rpt_line_abc_estimate_amt_sum";

        String nameComment[][] = getTableFiels(database,table);//获取和打印数据表的字段和注释

        //输出表信息，保存到E盘output 目录 csv格式
        //请先在E盘创建output 目录
        try {
            String[] tables = {"dim_city_county_rel_cfg"};
            for (String tab : tables) {
                String content = getTableInfo(database,tab);
                outputFile("E:\\", tab + ".csv", content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Connection getJDBConnect() {
        Connection conn = null;
        Statement stmt = null;

            //STEP 2: Register JDBC driver
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //STEP 3: Open a connection
           // System.out.println("Connecting to a selected database...");
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //System.out.println("Connected database successfully...");
        return  conn;
    }

    /**
     * 返回表名的表相关信息
     * @param database 数据库
     * @param table 数据表
     */
    public static String getTableInfo(String database, String table) {
        Connection conn = DataBaseUtil.getJDBConnect();
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
        Connection conn = new DataBaseUtil().getJDBConnect();
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
