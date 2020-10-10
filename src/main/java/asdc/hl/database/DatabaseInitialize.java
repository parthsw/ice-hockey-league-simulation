package asdc.hl.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseInitialize {

    public static String Url;
    public static String Username;
    public static String Password;
    public static String Driver;

    private void loadDBProperties() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("../../application.properties");
        Properties dbProperties = new Properties();

        dbProperties.load(fileInputStream);
        Url = dbProperties.getProperty("Url");
        Username = dbProperties.getProperty("Username");
        Password = dbProperties.getProperty("Password");
        Driver = dbProperties.getProperty("Driver");
    }

    public Connection getConnection() {
        try {
            loadDBProperties();
            Class.forName(Driver);

            Connection databaseConnection = DriverManager.getConnection(Url, Username, Password);
            return databaseConnection;

        } catch (Exception e) {
            System.out.println("Error connecting to database.");
        }

        return null;
    }
}
