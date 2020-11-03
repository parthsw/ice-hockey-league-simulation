package com.IO;

import java.util.Scanner;

public class CommandLineInput implements IAppInput {
    @Override
    public String getInput() {
        Scanner scanner = new Scanner((System.in));
        return scanner.nextLine();
    }
}
