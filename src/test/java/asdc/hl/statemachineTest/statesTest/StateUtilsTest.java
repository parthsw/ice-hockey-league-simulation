package asdc.hl.statemachineTest.statesTest;

import asdc.hl.statemachine.states.StateUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class StateUtilsTest {
    private static final InputStream originalInputStream = System.in;
    private static final PrintStream originalOutputStream = System.out;

    private static final ByteArrayInputStream inputStream = new ByteArrayInputStream("Sample string prompt".getBytes());
    private static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Before
    public void setup() {
        System.setIn(inputStream);
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void promptForUserInputTest() {
        String expectedOutput = "Sample string prompt";
        assertEquals(expectedOutput, StateUtils.promptForUserInput());
    }

    @Test
    public void printMessageTest() {
        String expectedOutput = "The normal message to be printed.\r\n";
        StateUtils.printMessage("The normal message to be printed.");
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void printErrorMessageTest() {
        String expectedOutput = "\033[0;31m" + "The error message to be printed." + "\033[0m" + "\r\n";
        StateUtils.printErrorMessage("The error message to be printed.");
        assertEquals(expectedOutput, outputStream.toString());
    }

    @After
    public void afterEach() {
        outputStream.reset();
        System.setIn(originalInputStream);
        System.setOut(originalOutputStream);
    }
}
