package com.aijob;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/career_db",
            "root",
            "root"
        );

        System.out.println("MYSQL CONNECTED SUCCESSFULLY");

        return con;
    }
}