package asdc.hl;

import java.util.HashMap;

public class HelloWorld {
    public static void main(String args[]) {
        System.out.println("Welcome to the main class");
    }
    public String Hello (String str){
        System.out.println("Hello " + str);
        PropertiesLoader pl = new PropertiesLoader();
        HashMap <String,String> propsMap = new HashMap<String,String>();
        try{
            propsMap = pl.getPropValues();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return str;
    }
}
