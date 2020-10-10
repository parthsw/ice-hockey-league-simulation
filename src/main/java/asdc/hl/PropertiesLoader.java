package asdc.hl;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class PropertiesLoader {

    public void getPropValues() {
            Properties property = new Properties();
        URL url = null;
        try {
            url = new URL("https://wordpress.org/plugins/about/readme.txt");
            String text = new Scanner(url.openStream()).useDelimiter("\\A").next();
            System.out.println(text);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}