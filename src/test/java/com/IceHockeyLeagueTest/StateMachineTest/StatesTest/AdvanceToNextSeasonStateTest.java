package com.IceHockeyLeagueTest.StateMachineTest.StatesTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Player.IRandomChance;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;
import com.IceHockeyLeague.StateMachine.States.AbstractState;
import com.Persistence.ILeaguePersistence;
import com.PersistenceTest.PersistenceFactoryTest;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.mockito.Mockito.when;

public class AdvanceToNextSeasonStateTest {
    private static final String PERSIST_STATE = "PersistState";

    private static IStateMachineFactory stateMachineFactory;
    private static ILeagueManagerFactory leagueManagerFactory;
    private static IRandomChance randomChanceMock;
    private static PersistenceFactoryTest persistenceFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory appFactory = AppFactoryTest.createAppFactory();
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        AbstractAppFactory.setStateMachineFactory(appFactory.createStateMachineFactory());
        stateMachineFactory = AbstractAppFactory.getStateMachineFactory();
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        randomChanceMock = leagueManagerFactory.createRandomChance();
        persistenceFactory = AppFactoryTest.createPersistenceFactoryTest();
    }

    @Test
    public void onRunTest() {
        ILeaguePersistence leaguePersistence = persistenceFactory.createLeaguePersistence();
        ILeague league = leaguePersistence.loadLeague("");
        AbstractState advanceToNextSeasonState = stateMachineFactory.createAdvanceToNextSeasonState();
        AbstractState nextState;


        advanceToNextSeasonState.setLeague(league);
        when(randomChanceMock.getRandomFloatNumber(0, 50)).thenReturn(0.5f);
        nextState = advanceToNextSeasonState.onRun();

        Assert.assertEquals(nextState.getClass().getSimpleName(), PERSIST_STATE);
    }

    @Test
    public void onRunNextSeasonVerificationTest() {
        ILeaguePersistence leaguePersistence = persistenceFactory.createLeaguePersistence();
        AbstractState advanceToNextSeasonState = stateMachineFactory.createAdvanceToNextSeasonState();
        ILeague league = leaguePersistence.loadLeague("");
        LocalDate newSeasonDate = LocalDate.of(2021, Month.SEPTEMBER, 29);

        advanceToNextSeasonState.setLeague(league);
        when(randomChanceMock.getRandomFloatNumber(0, 50)).thenReturn(0.5f);
        advanceToNextSeasonState.onRun();

        Assert.assertEquals(newSeasonDate, league.getLeagueDate());
    }

}
