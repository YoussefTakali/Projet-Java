package com.initial.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance;
    private Connection connection;

    // Private constructor to prevent instantiation
    private DBConnection() {
        try {
            // Use absolute path to load db.properties
            String propertiesFilePath = "Projet-Java/demo/src/main/ressources/db.properties";
            FileInputStream inputStream = new FileInputStream(propertiesFilePath);

            Properties properties = new Properties();
            properties.load(inputStream);

            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
            String driverClassName = properties.getProperty("db.driverClassName");

            if (url == null || username == null || password == null || driverClassName == null) {
                throw new RuntimeException("Missing database properties in db.properties.");
            }

            // Register the JDBC driver
            Class.forName(driverClassName);

            // Establish the connection
            this.connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established successfully.");
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize DBConnection.", e);
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
}
