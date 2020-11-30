package com.IOTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IO.IIOFactory;
import com.IO.IAppInput;
import org.junit.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class CommandLineInputTest {
    private final InputStream SYSTEM_IN = System.in;
    private static IIOFactory ioFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setIOFactory(appFactory.createIOFactory());
        ioFactory = AbstractAppFactory.getIOFactory();
    }

    private void commandLineInput(String data) {
        ByteArrayInputStream testInput = new ByteArrayInputStream(data.getBytes());
        System.setIn(testInput);
    }

    @After
    public void resetSystemInput() {
        System.setIn(SYSTEM_IN);
    }

    @Test
    public void getInputTest() {
        IAppInput appInput = ioFactory.createCommandLineInput();
        commandLineInput("Hockey League Simulation");
        Assert.assertEquals("Hockey League Simulation", appInput.getInput());
    }

    @Test
    public void getInputAlternativeTest() {
        IAppInput appInput = ioFactory.createCommandLineInput();
        commandLineInput("1234");
        Assert.assertEquals("1234", appInput.getInput());
    }

}
