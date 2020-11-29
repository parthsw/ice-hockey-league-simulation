package com;

import com.IceHockeyLeague.StateMachine.IStateMachine;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;
import com.IceHockeyLeague.StateMachine.States.AbstractState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HockeyLeagueSimulationApp {
    private static final Logger LOGGER = LogManager.getLogger(HockeyLeagueSimulationApp.class);

    public static void main(String[] args) {
        GlobalHandler handler = new GlobalHandler();
        Thread.setDefaultUncaughtExceptionHandler(handler);

        LOGGER.info("Starting the Ice Hockey League simulation app.");

        initializeFactories();
        runApp();
    }

    private static void initializeFactories() {
        LOGGER.info("Assigning the package level factories.");
        AbstractAppFactory.setAppFactory(AppFactory.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();

        AbstractAppFactory.setLeagueFileHandlerFactory(appFactory.createLeagueFileHandlerFactory());
        AbstractAppFactory.setDatabaseFactory(appFactory.createDatabaseFactory());
        AbstractAppFactory.setIOFactory(appFactory.createIOFactory());
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        AbstractAppFactory.setStateMachineFactory(appFactory.createStateMachineFactory());
        AbstractAppFactory.setTradingFactory(appFactory.createTradingFactory());
        LOGGER.info("Successfully assigned the package level factories.");
    }

    private static void runApp() {
        IStateMachineFactory stateMachineFactory = AbstractAppFactory.getStateMachineFactory();
        AbstractState importState = stateMachineFactory.createImportState();

        IStateMachine stateMachine = stateMachineFactory.createStateMachine(importState);
        LOGGER.info("Starting the outermost state machine.");
        stateMachine.onExecution();
    }

}
