package asdc.hl;

public class HelloWorld {
    public static void main(String args[]) {
        System.out.println("Welcome to the main class");
    }
    public String Hello (String str){
        System.out.println("Hello " + str);
        PropertiesLoader pl = new PropertiesLoader();
        pl.getPropValues();
        return str;
    }
}
