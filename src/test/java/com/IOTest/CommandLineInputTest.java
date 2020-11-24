package com.IOTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IO.IIOFactory;
import com.IO.IAppInput;
import org.junit.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class CommandLineInputTest {
    private final InputStream systemIn = System.in;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setIOFactory(appFactory.createIOFactory());
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
        IIOFactory ioFactory = AbstractAppFactory.getIOFactory();
        IAppInput appInput = ioFactory.createCommandLineInput();

        commandLineInput("Hockey League Simulation");
        Assert.assertEquals("Hockey League Simulation", appInput.getInput());
    }

}
