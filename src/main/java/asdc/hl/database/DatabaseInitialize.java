package asdc.hl.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseInitialize implements DBConnection {

    FileInputStream fileInputStream;
    public static String Url;
    public static String Username;
    public static String Password;
    public static String Driver;
    public Connection connection;

    DatabaseInitialize() {
        try {
            this.fileInputStream = new FileInputStream("../../application.properties");
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        Properties dbProperties = new Properties();
        try {
            dbProperties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Url = dbProperties.getProperty("Url");
        Username = dbProperties.getProperty("Username");
        Password = dbProperties.getProperty("Password");
        Driver = dbProperties.getProperty("Driver");
    }


    public Connection getConnection() {
        try {
            Class.forName(Driver);
            this.connection =
                    DriverManager
                            .getConnection(Url, Username, Password);
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

