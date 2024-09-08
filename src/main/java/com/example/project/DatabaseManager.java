package com.example.project;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Manages the database connection for the application.
 * This class uses the Singleton pattern to ensure only one instance is created and provides access to the database connection.
 */
public class DatabaseManager {
    private static DatabaseManager instance;
    private Connection connection;

    /**
     * Private constructor to prevent direct instantiation.
     * Initializes the JDBC driver and establishes a connection to the database.
     * The connection details should be securely managed and not hardcoded in production code.
     */
    private DatabaseManager() {
        try {
            // Loading JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //TODO: Step 1 Provide the URL to the database with your own username and password
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cruise_booking", "root", "Jka83788#");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
//            exception.printStackTrace();
        }
    }

    /**
     * Gets the singleton instance of DatabaseManager.
     * If the instance does not exist, it is created. This method ensures that only one instance
     * of DatabaseManager is used throughout the application.
     *
     * @return The single instance of DatabaseManager.
     */
    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    /**
     * Retrieves the database connection.
     *
     * @return The connection object used to interact with the database.
     */
    public Connection getConnection() {
        return connection;
    }
}
