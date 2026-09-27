package com.student.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class to provide reusable JDBC Connection instances to student_db.
 */
public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD_ENV_VAR = "MYSQL_PASSWORD";

    static {
        try {
            // Explicitly load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found on the classpath.", e);
        }
    }

    private DBConnection() {
        // Prevent direct instantiation
    }

    /**
     * Obtains a connection to the student_db database.
     * The password is read from the MYSQL_PASSWORD environment variable.
     *
     * @return a new java.sql.Connection instance
     * @throws SQLException if connection fails or if MYSQL_PASSWORD environment variable is missing
     */
    public static Connection getConnection() throws SQLException {
        String password = System.getenv(PASSWORD_ENV_VAR);
        if (password == null) {
            throw new SQLException("Environment variable '" + PASSWORD_ENV_VAR
                    + "' is not set. Please configure the " + PASSWORD_ENV_VAR + " environment variable.");
        }
        return DriverManager.getConnection(URL, USERNAME, password);
    }
}
