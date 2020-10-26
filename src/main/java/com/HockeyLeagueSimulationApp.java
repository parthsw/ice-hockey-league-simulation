package com;

import com.IO.AbstractIOFactory;
import com.IO.IOFactory;
import com.IceHockeyLeague.LeagueFileHandler.AbstractLeagueFileHandlerFactory;
import com.IceHockeyLeague.LeagueFileHandler.LeagueFileHandlerFactory;
import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.LeagueManagerFactory;
import com.IceHockeyLeague.StateMachine.AbstractStateMachineFactory;
import com.IceHockeyLeague.StateMachine.StateMachineFactory;
import com.IceHockeyLeague.StateMachine.States.AbstractState;

public class HockeyLeagueSimulationApp {
    public static void main(String[] args) {
        initializeFactories();
        runApp();
    }

    private static void initializeFactories() {
        AbstractLeagueFileHandlerFactory.setFactory(new LeagueFileHandlerFactory());
        AbstractIOFactory.setFactory(new IOFactory());
        AbstractLeagueManagerFactory.setFactory(new LeagueManagerFactory());

        AbstractStateMachineFactory.setFactory(
                new StateMachineFactory(
                        AbstractIOFactory.getFactory().getCommandLineInput(),
                        AbstractIOFactory.getFactory().getCommandLineOutput(),
                        LeagueFileHandlerFactory.getFactory().getLeagueFileReader(),
                        LeagueFileHandlerFactory.getFactory().getJsonParser(),
                        LeagueFileHandlerFactory.getFactory().getLeagueFileValidator()
                )
        );
    }

    private static void runApp() {
        AbstractState importState = AbstractStateMachineFactory.getFactory().getImportState();
        AbstractStateMachineFactory.getFactory().getStateMachine(importState).onExecution();
    }
}
