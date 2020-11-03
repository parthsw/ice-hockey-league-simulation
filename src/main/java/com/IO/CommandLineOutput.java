package com.IO;

public class CommandLineOutput implements IAppOutput {
    @Override
    public void display(String message) {
        System.out.println(message);
    }

    @Override
    public void displayError(String errorMessage) {
        System.out.println("\033[0;31m" + errorMessage + "\033[0m");
    }
}
