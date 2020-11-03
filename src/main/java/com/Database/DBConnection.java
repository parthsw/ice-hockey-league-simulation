package com.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection implements IDBConnection {

    private static DBConnection dbConnectionInstance = null;

    String[] properties;
    public static String url;
    public static String userName;
    public static String password;
    public static String driver;
    public Connection connection;

     DBConnection() {
        PropertiesLoader pl = new PropertiesLoader();
        properties = pl.getProperties();
        url = properties[0];
        userName = properties[1];
        password = properties[2];
        driver = properties[3];
    }

    public static DBConnection instance() {
         if (dbConnectionInstance == null) {
             dbConnectionInstance = new DBConnection();
         }
         return dbConnectionInstance;
    }


    public Connection getConnection() {
        try {
            Class.forName(driver);
            this.connection =
                    DriverManager
                            .getConnection(url, userName, password);
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error connecting to database.");
        }
        return connection;
    }

    @Override
    public boolean terminateConnection() {
        try {
            if(connection.isClosed() == false) {
                this.connection.close();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }
}

