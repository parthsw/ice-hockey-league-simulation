package com.IOTest;

import com.IO.AbstractIOFactory;
import com.IO.IAppOutput;
import com.IO.IOFactory;
import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CommandLineOutputTest {
    private final PrintStream systemOut = System.out;

    private ByteArrayOutputStream testOutput;

    @BeforeClass
    public static void setup() {
        AbstractIOFactory.setFactory(new IOFactory());
    }

    @Before
    public void setupSystemOutput() {
        testOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOutput));
    }

    private String getOutput() {
        return testOutput.toString();
    }

    @After
    public void resetSystemOutput() {
        System.setOut(systemOut);
    }

    @Test
    public void displayTest() {
        IAppOutput appOutput = AbstractIOFactory.getFactory().getCommandLineOutput();
        appOutput.display("Hockey Simulation League");

        Assert.assertTrue(getOutput().contains("Hockey Simulation League"));
    }

    @Test
    public void displayErrorTest() {
        IAppOutput appOutput = AbstractIOFactory.getFactory().getCommandLineOutput();
        appOutput.displayError("Error in hockey league simulation");
        String expectedErrorOutput = "\033[0;31m" + "Error in hockey league simulation" + "\033[0m" + "\r\n";

        Assert.assertTrue(getOutput().contains(expectedErrorOutput));
    }
}
