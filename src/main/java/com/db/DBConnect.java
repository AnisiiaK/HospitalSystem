package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private static Connection conn;

    public static Connection getConn(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_2", "root", "root");

        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        System.out.println("getConn func " + conn );
        return conn;
    }

}
