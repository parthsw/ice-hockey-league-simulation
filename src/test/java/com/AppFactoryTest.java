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
import com.IceHockeyLeagueTest.LeagueManagerTest.LeagueManagerFactoryTest;
import com.Persistence.IPersistenceFactory;
import com.Persistence.PersistenceFactory;
import com.PersistenceTest.PersistenceFactoryTest;
import com.Trading.ITradingFactory;
import com.Trading.TradingFactory;

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

    public static AbstractAppFactory createAppFactory() {
        return new AppFactoryTest();
    }

    public static PersistenceFactoryTest createPersistenceFactory() {
        return new PersistenceFactoryTest();
    }
}
