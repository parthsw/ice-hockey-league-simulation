package com.Database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {
    FileInputStream fileInputStream;
    public static String Url;
    public static String Username;
    public static String Password;
    public static String Driver;

    public String[] getProperties(){
        String[] properties = new String[4];
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

        properties[0] = Url;
        properties[1] = Username;
        properties[2] = Password;
        properties[3] = Driver;
        return properties;
    }
}
