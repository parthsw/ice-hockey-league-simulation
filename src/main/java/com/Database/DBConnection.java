package com.Database;

import com.AbstractAppFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection implements IDBConnection {
    String[] properties;
    public static String url;
    public static String userName;
    public static String password;
    public static String driver;
    public Connection connection;

    public DBConnection() {
        IDatabaseFactory databaseFactory = AbstractAppFactory.getDatabaseFactory();
        PropertiesLoader pl = databaseFactory.createPropertiesLoader();
        properties = pl.getProperties();
        url = properties[0];
        userName = properties[1];
        password = properties[2];
        driver = properties[3];
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

