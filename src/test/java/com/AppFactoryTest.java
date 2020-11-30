package com;

import com.IO.IIOFactory;
import com.IO.IOFactory;
import com.IceHockeyLeague.LeagueFileHandler.ILeagueFileHandlerFactory;
import com.IceHockeyLeague.LeagueFileHandler.LeagueFileHandlerFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.SerializeDeserializeLeagueObject.ISerializeDeserializeLeagueObjectFactory;
import com.IceHockeyLeague.SerializeDeserializeLeagueObject.SerializeDeserializeLeagueObjectFactory;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;
import com.IceHockeyLeague.StateMachine.StateMachineFactory;
import com.IceHockeyLeague.Trading.ITradingFactory;
import com.IceHockeyLeague.Trading.TradingFactory;
import com.IceHockeyLeagueTest.LeagueManagerTest.LeagueManagerFactoryTest;
import com.Persistence.IPersistenceFactory;
import com.PersistenceTest.PersistenceFactoryTest;
import com.Trading.ITradingFactory;
import com.Trading.TradingFactory;
import com.TrophySystem.ITrophySystemFactory;
import com.TrophySystem.TrophySystemFactory;

public class AppFactoryTest extends AbstractAppFactory {


    @Override
    public ILeagueFileHandlerFactory createLeagueFileHandlerFactory() {
        return new LeagueFileHandlerFactory();
    }

    @Override
    public IIOFactory createIOFactory() {
        return new IOFactory();
    }

    @Override
    public ILeagueManagerFactory createLeagueManagerFactory() {
        return new LeagueManagerFactoryTest();
    }

    @Override
    public ISerializeDeserializeLeagueObjectFactory createSerializeDeserializeLeagueObjectFactory() {
        return new SerializeDeserializeLeagueObjectFactory();
    }

    @Override
    public ITradingFactory createTradingFactory() {
        return new TradingFactory();
    }

    @Override
    public IPersistenceFactory createPersistenceFactory() {
        return new PersistenceFactoryTest();
    }

    @Override
    public IStateMachineFactory createStateMachineFactory() {
        IIOFactory ioFactory = createIOFactory();
        ILeagueFileHandlerFactory leagueFileHandlerFactory = createLeagueFileHandlerFactory();
        return new StateMachineFactory(
                ioFactory.createCommandLineInput(),
                ioFactory.createCommandLineOutput(),
                leagueFileHandlerFactory.createLeagueFileReader(),
                leagueFileHandlerFactory.createJsonParser(),
                leagueFileHandlerFactory.createLeagueFileValidator());
    }

    @Override
    public ITrophySystemFactory createTrophySystemFactory() {
        return new TrophySystemFactory();
    }

    public static PersistenceFactoryTest createPersistenceFactoryTest() {
        return new PersistenceFactoryTest();
    }

    public static AbstractAppFactory createAppFactory() {
        return new AppFactoryTest();
    }

}
