package com.IceHockeyLeagueTest.StateMachineTest.StatesTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;
import com.IceHockeyLeague.StateMachine.States.AbstractState;
import com.IceHockeyLeague.StateMachine.States.PersistState;
import com.IceHockeyLeague.StateMachine.States.TrophyState;
import com.Persistence.ILeaguePersistence;
import com.PersistenceTest.PersistenceFactoryTest;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

public class AgingStateTest {
    private static IStateMachineFactory stateMachineFactory;
    private static PersistenceFactoryTest persistenceFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory appFactory = AppFactoryTest.createAppFactory();
        AbstractAppFactory.setStateMachineFactory(appFactory.createStateMachineFactory());
        AbstractAppFactory.setTrophySystemFactory(appFactory.createTrophySystemFactory());
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        stateMachineFactory = AbstractAppFactory.getStateMachineFactory();
        persistenceFactory = AppFactoryTest.createPersistenceFactoryTest();
    }

    @Test
    public void onRunTest() {
        ILeaguePersistence leaguePersistenceMock = persistenceFactory.createLeaguePersistence();
        ILeague league = leaguePersistenceMock.loadLeague("");
        league.setLeagueDate(LocalDate.of(2020, Month.NOVEMBER, 17));

        AbstractState agingState = stateMachineFactory.createAgingState();
        agingState.setLeague(league);

        Assert.assertTrue(agingState.onRun() instanceof TrophyState);
        Assert.assertEquals(19, league.getConferences().get(0).getDivisions().get(0).getTeams().get(0).getPlayers().get(0).getPlayerAgeInfo().getAgeInYears());
        Assert.assertEquals(333, league.getFreeAgents().get(0).getPlayerAgeInfo().getElapsedDaysFromLastBDate());
    }

    @Test
    public void onRunAlternateTest() {
        ILeaguePersistence leaguePersistenceMock = persistenceFactory.createLeaguePersistence();
        ILeague league = leaguePersistenceMock.loadLeague("");
        league.setLeagueDate(LocalDate.of(2020, Month.NOVEMBER, 17));
        league.getScheduleSystem().setPlayoffSchedule(null);
        AbstractState agingState = stateMachineFactory.createAgingState();
        agingState.setLeague(league);

        Assert.assertTrue(agingState.onRun() instanceof PersistState);
        Assert.assertEquals(19, league.getConferences().get(0).getDivisions().get(0).getTeams().get(0).getPlayers().get(0).getPlayerAgeInfo().getAgeInYears());
        Assert.assertEquals(333, league.getFreeAgents().get(0).getPlayerAgeInfo().getElapsedDaysFromLastBDate());
    }

}
