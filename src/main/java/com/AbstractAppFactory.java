package com;

import com.IO.IIOFactory;
import com.Database.IDatabaseFactory;
import com.IceHockeyLeague.LeagueFileHandler.ILeagueFileHandlerFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;

public abstract class AbstractAppFactory {

    private static AbstractAppFactory appFactory;
    private static ILeagueFileHandlerFactory leagueFileHandlerFactory;
    private static IDatabaseFactory databaseFactory;
    private static IIOFactory ioFactory;
    private static ILeagueManagerFactory leagueManagerFactory;
    private static IStateMachineFactory stateMachineFactory;

    public abstract ILeagueFileHandlerFactory createLeagueFileHandlerFactory();
    public abstract IDatabaseFactory createDatabaseFactory();
    public abstract IIOFactory createIOFactory();
    public abstract ILeagueManagerFactory createLeagueManagerFactory();
    public abstract IStateMachineFactory createStateMachineFactory();

    public static AbstractAppFactory createAppFactory() {
        return new AppFactory();
    }

    public static AbstractAppFactory getAppFactory() {
        return appFactory;
    }

    public static void setAppFactory(AbstractAppFactory appFactory) {
        AbstractAppFactory.appFactory = appFactory;
    }

    public static ILeagueFileHandlerFactory getLeagueFileHandlerFactory() {
        return leagueFileHandlerFactory;
    }

    public static void setLeagueFileHandlerFactory(ILeagueFileHandlerFactory leagueFileHandlerFactory) {
        AbstractAppFactory.leagueFileHandlerFactory = leagueFileHandlerFactory;
    }

    public static IDatabaseFactory getDatabaseFactory() {
        return databaseFactory;
    }

    public static void setDatabaseFactory(IDatabaseFactory databaseFactory) {
        AbstractAppFactory.databaseFactory = databaseFactory;
    }

    public static IIOFactory getIOFactory() {
        return ioFactory;
    }

    public static void setIOFactory(IIOFactory ioFactory) {
        AbstractAppFactory.ioFactory = ioFactory;
    }

    public static ILeagueManagerFactory getLeagueManagerFactory() {
        return leagueManagerFactory;
    }

    public static void setLeagueManagerFactory(ILeagueManagerFactory leagueManagerFactory) {
        AbstractAppFactory.leagueManagerFactory = leagueManagerFactory;
    }

    public static IStateMachineFactory getStateMachineFactory() {
        return stateMachineFactory;
    }

    public static void setStateMachineFactory(IStateMachineFactory stateMachineFactory) {
        AbstractAppFactory.stateMachineFactory = stateMachineFactory;
    }

}
