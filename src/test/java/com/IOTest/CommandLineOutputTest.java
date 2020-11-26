package com.IOTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IO.IIOFactory;
import com.IO.IAppOutput;
import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CommandLineOutputTest {
    private final PrintStream systemOut = System.out;
    private static IIOFactory ioFactory;

    private ByteArrayOutputStream testOutput;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setIOFactory(appFactory.createIOFactory());
        ioFactory = AbstractAppFactory.getIOFactory();
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
        IAppOutput appOutput = ioFactory.createCommandLineOutput();
        appOutput.display("Hockey Simulation League");
        Assert.assertTrue(getOutput().contains("Hockey Simulation League"));
    }

    @Test
    public void displayErrorTest() {
        IAppOutput appOutput = ioFactory.createCommandLineOutput();
        appOutput.displayError("Error in hockey league simulation");
        String expectedErrorOutput = "\033[0;31m" + "Error in hockey league simulation" + "\033[0m";
        Assert.assertTrue(getOutput().contains(expectedErrorOutput));
    }

}
