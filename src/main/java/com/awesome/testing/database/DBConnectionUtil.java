package com.awesome.testing.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/testdb";
    private static final String USER = "postgres";
    private static final String PASS = "postgres";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
} 