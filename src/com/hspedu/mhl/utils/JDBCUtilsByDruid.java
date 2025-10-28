package com.hspedu.mhl.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtilsByDruid {
    private static DataSource ds;

 /*   static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("D:\\code\\mhl\\src\\com\\hspedu\\mhl\\utils\\druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
*/
 static {
     Properties properties = new Properties();
     try {
         // 从类路径加载配置文件 (自动去 src 根目录下找)
         properties.load(JDBCUtilsByDruid.class.getClassLoader().getResourceAsStream("druid.properties"));
         ds = DruidDataSourceFactory.createDataSource(properties);
     } catch (Exception e) {
         throw new RuntimeException(e);
     }
 }


    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (resultSet !=null){
                resultSet.close();
            }
            if (statement != null){
                statement.close();
            }
            if (connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
