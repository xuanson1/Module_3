package com.example.case_module_3.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class DatabaseConnect {
        private static final String URL = "jdbc:mysql://localhost:3306/phongtro?useSSL=false";
        private static final String USERNAME = "root";
        private static final String PASSWORD = "son123";

        public DatabaseConnect() {}

        public static Connection getConnection() {
            Connection conn = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Connect database successfully");
            }catch (SQLException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
            return conn;
        }
    }
