package com.IOTest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class IOMock {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private static IOMock ioMockInstance = null;
    private ByteArrayOutputStream testOutput;

    public static IOMock instance() {
        if(ioMockInstance == null) {
            return new IOMock();
        }
        return ioMockInstance;
    }

    public void setupSystemOutput() {
        testOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOutput));
    }

    public String getOutput() {
        return testOutput.toString();
    }

    public void resetSystemOutput() {
        System.setOut(systemOut);
    }

    public void commandLineInput(String data) {
        ByteArrayInputStream testInput = new ByteArrayInputStream(data.getBytes());
        System.setIn(testInput);
    }

    public void resetSystemInput() {
        System.setIn(systemIn);
    }
}
