package com;

import com.statemachine.StateMachine;
import com.statemachine.states.StateUtils;

import java.sql.SQLException;


public class HockeyLeagueSimulationApp {
    public static void main(String[] args) throws SQLException {
        HockeyLeagueSimulationApp.printWelcomeMessage();
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
