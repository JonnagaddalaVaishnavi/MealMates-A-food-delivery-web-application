package com.myfoods.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/mealmates";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static Connection Connection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL Driver not found. Add the MySQL Connector JAR to the classpath.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Failed to establish a database connection. Check credentials and database URL.");
            e.printStackTrace();
        }
        return connection;
    }
}
