package com.statemachine.states;

import java.util.Scanner;

public class StateUtils {

    public static String promptForUserInput() throws NullPointerException {
        Scanner scanner = new Scanner((System.in));
        String input = scanner.nextLine();
        return input;
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println("\033[0;31m" + errorMessage + "\033[0m");
    }
}
