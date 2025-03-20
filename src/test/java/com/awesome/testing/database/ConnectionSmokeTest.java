package com.awesome.testing.database;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.assertj.core.api.Assertions.assertThat;

public class ConnectionSmokeTest {

    @Test
    public void testConnection() throws SQLException {
        // given
        try (Connection conn = DBConnectionUtil.getConnection()) {
            // when - getting connection info
            
            // then - connection should be established
            assertThat(conn).isNotNull();
            assertThat(conn.isClosed()).isFalse();
            System.out.println("Database connection successfully established!");
        }
    }
    
    @Test
    public void testSimpleQuery() throws SQLException {
        // given
        String sql = "SELECT NOW()";
        
        // when
        try (Connection conn = DBConnectionUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            // then
            assertThat(rs.next()).isTrue();
            String currentTime = rs.getString(1);
            System.out.println("Current database time: " + currentTime);
            assertThat(currentTime).isNotNull();
        }
    }
    
    @Test
    public void testTableQuery() throws SQLException {
        // given
        String sql = "SELECT COUNT(*) FROM app_user";
        
        // when
        try (Connection conn = DBConnectionUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            // then
            assertThat(rs.next()).isTrue();
            int userCount = rs.getInt(1);
            System.out.println("Number of users in app_user table: " + userCount);
        }
    }
} 