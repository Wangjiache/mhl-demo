package com.hspedu.mhl.utils;

import java.sql.Connection;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {
        //测试工具类
        System.out.println("输入");
        int i = Utility.readInt();
        System.out.println("i=" + i);

        System.out.println("输入");
        char c = Utility.readChar();
        System.out.println("c=" + c);


        //测试druid
        Connection connection = JDBCUtilsByDruid.getConnection();
        System.out.println(connection);

    }
}
