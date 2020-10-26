package com.IOTest;

import com.IO.AbstractIOFactory;
import com.IO.IAppInput;
import com.IO.IOFactory;
import org.junit.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class CommandLineInputTest {
    private final InputStream systemIn = System.in;

    @BeforeClass
    public static void setup() {
        AbstractIOFactory.setFactory(new IOFactory());
    }

    private void commandLineInput(String data) {
        ByteArrayInputStream testInput = new ByteArrayInputStream(data.getBytes());
        System.setIn(testInput);
    }

    @After
    public void resetSystemInput() {
        System.setIn(systemIn);
    }

    @Test
    public void getInputTest() {
        commandLineInput("Hockey League Simulation");
        IAppInput appInput = AbstractIOFactory.getFactory().getCommandLineInput();
        Assert.assertEquals("Hockey League Simulation", appInput.getInput());
    }
}
