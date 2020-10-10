package asdc.hl;
import org.junit.Test;
import static org.junit.Assert.*;

public class HelloWorldTest {
    @Test
    public void helloWorldTest(){
        HelloWorld h = new HelloWorld();
        String str = "World";
        assertEquals(str,h.Hello(str));
    }
}
