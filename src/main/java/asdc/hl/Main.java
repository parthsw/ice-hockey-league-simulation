package asdc.hl;

import asdc.hl.statemachine.StateMachine;
import asdc.hl.statemachine.states.StateUtils;

public class Main {
    public static void main(String[] args) {
        Main.printWelcomeMessage();
        StateMachine leagueStateMachine = new StateMachine();
        leagueStateMachine.run();
    }

    private static void printWelcomeMessage() {
        String welcomeMessage = "*********************************************\n" +
                "***Welcome to the Hockey league Simulation***\n" +
                "*********************************************\n";
        StateUtils.printMessage(welcomeMessage);
    }
}
