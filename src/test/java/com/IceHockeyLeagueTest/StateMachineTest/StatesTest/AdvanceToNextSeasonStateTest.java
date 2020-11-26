package com.IceHockeyLeagueTest.StateMachineTest.StatesTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.Database.IDatabaseFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.ILeaguePersistence;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;
import com.IceHockeyLeague.StateMachine.States.AbstractState;
import com.IceHockeyLeague.StateMachine.States.PersistState;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

public class AdvanceToNextSeasonStateTest {
    private static IStateMachineFactory stateMachineFactory;
    private static ILeagueManagerFactory leagueManagerFactory;
    private static IDatabaseFactory databaseFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory appFactory = AppFactoryTest.createAppFactory();
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        AbstractAppFactory.setStateMachineFactory(appFactory.createStateMachineFactory());
        stateMachineFactory = AbstractAppFactory.getStateMachineFactory();
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        databaseFactory = appFactory.createDatabaseFactory();
    }

    @Test
    public void onRunTest() {
        ILeague league = leagueManagerFactory.createLeague();
        ILeaguePersistence leaguePersistence = databaseFactory.createLeaguePersistence();
        leaguePersistence.loadLeague(1, league);
        AbstractState advanceToNextSeasonState = stateMachineFactory.createAdvanceToNextSeasonState();
        advanceToNextSeasonState.setLeague(league);

        Assert.assertTrue(advanceToNextSeasonState.onRun() instanceof PersistState);
    }

    @Test
    public void onRunNextSeasonVerificationTest() {
        ILeague league = leagueManagerFactory.createLeague();
        ILeaguePersistence leaguePersistence = databaseFactory.createLeaguePersistence();
        AbstractState advanceToNextSeasonState = stateMachineFactory.createAdvanceToNextSeasonState();
        LocalDate newSeasonDate = LocalDate.of(2021, Month.SEPTEMBER, 29);
        leaguePersistence.loadLeague(1, league);
        advanceToNextSeasonState.setLeague(league);
        advanceToNextSeasonState.onRun();

        Assert.assertEquals(newSeasonDate, league.getLeagueDate());
    }

}
