package com.example.tests;

import com.example.models.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class TestDatabaseConnection {

    public static void main(String[] args) {
        // Get a connection using the DatabaseConnection class
        Connection connection = DatabaseConnection.getConnection();

        // Check if the connection is null or not
        if (connection != null) {
            System.out.println("Connection established successfully!");
            try {
                // Optionally, you can close the connection after the test
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed to establish a connection.");
        }
    }
}

