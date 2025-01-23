package com.initial;

package com.yourpackage.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class DBConnection {
    private static DBConnection instance;
    private Connection connection;

    // Private constructor to prevent instantiation
    private DBConnection() {
        try {
            // Load properties from db.properties file
            Properties properties = new Properties();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("db.properties");
            if (inputStream != null) {
                properties.load(inputStream);
            }

            // Establish the connection to the database
            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
            String driverClassName = properties.getProperty("db.driverClassName");

            // Register the JDBC driver
            Class.forName(driverClassName);

            // Create connection
            this.connection = DriverManager.getConnection(url, username, password);

        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Singleton getInstance method
    public static DBConnection getInstance() {
        if (instance == null) {
            synchronized (DBConnection.class) {
                if (instance == null) {
                    instance = new DBConnection();
                }
            }
        }
        return instance;
    }

    // Method to get connection
    public Connection getConnection() {
        return this.connection;
    }

    // Method to close connection (optional)
    public void closeConnection() {
        try {
            if (this.connection != null) {
                this.connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
