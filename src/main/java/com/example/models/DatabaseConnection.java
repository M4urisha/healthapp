package com.example.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection connect() throws SQLException {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=fitness_app;encrypt=false;trustServerCertificate=true;integratedSecurity=true;";
        return DriverManager.getConnection(url, "<your-username>", "<your-password>");
    }
}
