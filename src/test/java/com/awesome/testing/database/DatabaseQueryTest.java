package com.awesome.testing.database;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DatabaseQueryTest {

    @Test
    public void testFetchProducts() throws SQLException {
        // given
        String sql = "SELECT * FROM products LIMIT 5";
        List<String> productNames = new ArrayList<>();
        
        // when
        try (Connection conn = DBConnectionUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            // then
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                
                productNames.add(name);
                System.out.println("Product: " + id + " - " + name + " ($" + price + ")");
            }
            
            assertThat(productNames).isNotEmpty();
        }
    }
    
    @Test
    public void testListTables() throws SQLException {
        // given
        String sql = "SELECT table_name FROM information_schema.tables WHERE table_schema = 'public'";
        
        // when
        try (Connection conn = DBConnectionUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            // then
            System.out.println("Tables in the database:");
            while (rs.next()) {
                String tableName = rs.getString("table_name");
                System.out.println("- " + tableName);
            }
        }
    }
    
    @Test
    public void testDescribeTable() throws SQLException {
        // given
        String tableName = "cart_items";
        String sql = "SELECT column_name, data_type FROM information_schema.columns " +
                     "WHERE table_schema = 'public' AND table_name = '" + tableName + "'";
        
        // when
        try (Connection conn = DBConnectionUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            // then
            System.out.println("Columns in " + tableName + " table:");
            while (rs.next()) {
                String columnName = rs.getString("column_name");
                String dataType = rs.getString("data_type");
                System.out.println("- " + columnName + " (" + dataType + ")");
            }
        }
    }
    
    @Test
    public void testUserCartItems() throws SQLException {
        // given
        String sql = "SELECT ci.username, p.name as product_name, ci.quantity " +
                     "FROM cart_items ci " +
                     "JOIN products p ON ci.product_id = p.id " +
                     "LIMIT 10";
        
        // when
        try (Connection conn = DBConnectionUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            // then
            int count = 0;
            while (rs.next()) {
                String username = rs.getString("username");
                String productName = rs.getString("product_name");
                int quantity = rs.getInt("quantity");
                
                System.out.println("User: " + username + " has " + quantity + " of " + productName + " in cart");
                count++;
            }
            
            System.out.println("Total cart items found: " + count);
        }
    }
} 