package asdc.hl;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class PropertiesLoader<propsMap> {

    public HashMap<String, String> getPropValues() throws IOException {

        Properties property = new Properties();
        URL url = null;
        InputStream inputStream = null;
        HashMap<String, String> propsMap = new HashMap<String, String>();
        try {
            Path currPath = Paths.get("");
            //url = new URL("https://wordpress.org/plugins/about/readme.txt");
            //String text = new Scanner(url.openStream()).useDelimiter("\\A").next();
            String path = currPath.toAbsolutePath().toString();
            // /users/grad/vig/CSCI5308builds/build/libs/
            path = path.substring(0, path.lastIndexOf("build"));
            System.out.println("Relative path : " + path);
            String fileName = "application.properties";
            //inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
            try {
                inputStream = new FileInputStream(path + fileName);   //server
                if (inputStream != null) {
                    property.load(inputStream);
                }
            }
            catch(Exception e) {
                inputStream = getClass().getClassLoader().getResourceAsStream(fileName); //local
                property.load(inputStream);
            }

            propsMap.put("Driver", property.getProperty("Driver"));
            propsMap.put("Url", property.getProperty("Url"));
            propsMap.put("Password", property.getProperty("Password"));
            propsMap.put("Username", property.getProperty("Username"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return propsMap;
    }
}









/*package dhl.database;

        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
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
        if (fileInputStream != null) {
            Properties dbProperties = new Properties();

            dbProperties.load(fileInputStream);
            Url = dbProperties.getProperty("Url");
            Username = dbProperties.getProperty("Username");
            Password = dbProperties.getProperty("Password");
            Driver = dbProperties.getProperty("Driver");
        } else {
            throw new IOException("Error connecting to database.");
        }
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
}*/