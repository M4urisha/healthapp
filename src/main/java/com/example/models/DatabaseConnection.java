package com.example.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() {
        Connection connection = null;

        try {
            // JDBC URL for SQL Server using Windows Authentication
            String connectionUrl = "jdbc:sqlserver://localhost;databaseName=fitness_app;integratedSecurity=true;encrypt=false";

            // Load the SQL Server JDBC driver (optional for newer versions)
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Establish the connection
            connection = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected to the database successfully!");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
