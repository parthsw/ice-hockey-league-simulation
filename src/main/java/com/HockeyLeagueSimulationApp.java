package com;

import com.IceHockeyLeague.StateMachine.IStateMachine;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;
import com.IceHockeyLeague.StateMachine.States.AbstractState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HockeyLeagueSimulationApp {

    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(HockeyLeagueSimulationApp.class);
        logger.info("Starting the app");
        initializeFactories();
        runApp();
    }

    private static void initializeFactories() {
        AbstractAppFactory.setAppFactory(AppFactory.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();

        AbstractAppFactory.setLeagueFileHandlerFactory(appFactory.createLeagueFileHandlerFactory());
        AbstractAppFactory.setDatabaseFactory(appFactory.createDatabaseFactory());
        AbstractAppFactory.setIOFactory(appFactory.createIOFactory());
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        AbstractAppFactory.setStateMachineFactory(appFactory.createStateMachineFactory());
        AbstractAppFactory.setTradingFactory(appFactory.createTradingFactory());
    }

    private static void runApp() {
        IStateMachineFactory stateMachineFactory = AbstractAppFactory.getStateMachineFactory();
        AbstractState importState = stateMachineFactory.createImportState();

        IStateMachine stateMachine = stateMachineFactory.createStateMachine(importState);
        stateMachine.onExecution();
    }

}
