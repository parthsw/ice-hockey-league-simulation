package asdc.hl;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class MainTest {

    private static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Rule
    public final TextFromStandardInputStream inputMock = emptyStandardInputStream();

    @Before
    public void setup() {
        System.setOut(new PrintStream(outputStream));
        inputMock.provideLines("invalidPath", "teamName", "1");
    }

    @Test
    public void printWelcomeMessageTest() throws SQLException {
        Main.main(null);
        assertThat(outputStream.toString().trim(), containsString("*********************************************\n"));
        assertThat(outputStream.toString().trim(), containsString("***Welcome to the Hockey league Simulation***\n"));
    }

    @After
    public void tearDown() {
        outputStream.reset();
    }
}

