package com.example;

import com.example.models.DatabaseConnection;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseConnectionTest {

    @Test
    public void testGetConnection() {
        Connection connection = DatabaseConnection.getConnection();

        assertNotNull(connection, "The database connection should not be null.");

        // Optionally close the connection
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            fail("An exception occurred while closing the connection: " + e.getMessage());
        }
    }
}
