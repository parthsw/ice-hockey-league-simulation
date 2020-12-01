package com;

import com.IO.IIOFactory;
import com.IceHockeyLeague.LeagueFileHandler.ILeagueFileHandlerFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.SerializeDeserializeLeagueObject.ISerializeDeserializeLeagueObjectFactory;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;
import com.Persistence.IPersistenceFactory;
import com.TrophySystem.ITrophySystemFactory;
import com.IceHockeyLeague.Trading.ITradingFactory;

public abstract class AbstractAppFactory {
    private static AbstractAppFactory appFactory;
    private static ILeagueFileHandlerFactory leagueFileHandlerFactory;
    private static IPersistenceFactory persistenceFactory;
    private static IIOFactory ioFactory;
    private static ILeagueManagerFactory leagueManagerFactory;
    private static ISerializeDeserializeLeagueObjectFactory leagueSerializationFactory;
    private static ITradingFactory tradingFactory;
    private static IStateMachineFactory stateMachineFactory;
    private static ITrophySystemFactory trophySystemFactory;

    public abstract ILeagueFileHandlerFactory createLeagueFileHandlerFactory();

    public abstract IIOFactory createIOFactory();

    public abstract ILeagueManagerFactory createLeagueManagerFactory();

    public abstract ISerializeDeserializeLeagueObjectFactory createSerializeDeserializeLeagueObjectFactory();

    public abstract ITradingFactory createTradingFactory();

    public abstract IPersistenceFactory createPersistenceFactory();

    public abstract ITrophySystemFactory createTrophySystemFactory();

    public abstract IStateMachineFactory createStateMachineFactory();

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

    public static ISerializeDeserializeLeagueObjectFactory getSerializeDeserializeLeagueObjectFactory() {
        return leagueSerializationFactory;
    }

    public static void setSerializeDeserializeLeagueObjectFactory(ISerializeDeserializeLeagueObjectFactory leagueSerializationFactory) {
        AbstractAppFactory.leagueSerializationFactory = leagueSerializationFactory;
    }

    public static ITradingFactory getTradingFactory() {
        return tradingFactory;
    }

    public static void setTradingFactory(ITradingFactory tradingFactory) {
        AbstractAppFactory.tradingFactory = tradingFactory;
    }

    public static IStateMachineFactory getStateMachineFactory() {
        return stateMachineFactory;
    }

    public static void setStateMachineFactory(IStateMachineFactory stateMachineFactory) {
        AbstractAppFactory.stateMachineFactory = stateMachineFactory;
    }

    public static void setTrophySystemFactory(ITrophySystemFactory trophySystemFactory) {
        AbstractAppFactory.trophySystemFactory = trophySystemFactory;
    }

    public static ITrophySystemFactory getTrophySystemFactory() {
        return trophySystemFactory;
    }

    public static IPersistenceFactory getPersistenceFactory() {
        return persistenceFactory;
    }

    public static void setPersistenceFactory(IPersistenceFactory persistenceFactory) {
        AbstractAppFactory.persistenceFactory = persistenceFactory;
    }

}
