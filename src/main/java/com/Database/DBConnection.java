package com.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection implements IDBConnection {

    private static DBConnection dbConnectionInstance = null;

    String[] properties;
    public static String Url;
    public static String Username;
    public static String Password;
    public static String Driver;
    public Connection connection;

     DBConnection() {
//        PropertiesLoader pl = new PropertiesLoader();
//        properties = pl.getProperties();
//        Url = properties[0];
//        Username = properties[1];
//        Password = properties[2];
//        Driver = properties[3];
    }

    public static DBConnection instance() {
         if (dbConnectionInstance == null) {
             dbConnectionInstance = new DBConnection();
         }
         return dbConnectionInstance;
    }


    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection =
                    DriverManager
                            .getConnection("jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_6_DEVINT?serverTimezone=UTC", "CSCI5308_6_DEVINT_USER", "ZD8gnU7BsJ");
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

