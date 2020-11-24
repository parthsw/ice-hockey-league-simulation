package com.IceHockeyLeagueTest.StateMachineTest.StatesTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.Database.IDatabaseFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.ILeaguePersistence;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;
import com.IceHockeyLeague.StateMachine.States.AbstractState;
import com.IceHockeyLeague.StateMachine.States.AdvanceToNextSeasonState;
import com.IceHockeyLeague.StateMachine.States.PersistState;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

public class AgingStateTest {
    private static IStateMachineFactory stateMachineFactory;
    private static ILeagueManagerFactory leagueManagerFactory;
    private static IDatabaseFactory databaseFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory appFactory = AppFactoryTest.createAppFactory();
        AbstractAppFactory.setStateMachineFactory(appFactory.createStateMachineFactory());
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        AbstractAppFactory.setDatabaseFactory(appFactory.createDatabaseFactory());
        stateMachineFactory = AbstractAppFactory.getStateMachineFactory();
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        databaseFactory = AbstractAppFactory.getDatabaseFactory();
    }

    @Test
    public void onRunTest() {
        ILeague league = leagueManagerFactory.createLeague();
        ILeaguePersistence leagueDB = databaseFactory.createLeaguePersistence();
        leagueDB.loadLeague(1, league);
        league.setLeagueDate(LocalDate.of(2020, Month.NOVEMBER, 17));

        AbstractState agingState = stateMachineFactory.createAgingState();
        agingState.setLeague(league);

        Assert.assertTrue(agingState.onRun() instanceof AdvanceToNextSeasonState);
        Assert.assertEquals(19, league.getConferences().get(0).getDivisions().get(0).getTeams().get(0).getPlayers().get(0).getPlayerAgeInfo().getAgeInYears());
        Assert.assertEquals(331, league.getFreeAgents().get(0).getPlayerAgeInfo().getElapsedDaysFromLastBDay());
    }

    @Test
    public void onRunAlternateTest() {
        ILeague league = leagueManagerFactory.createLeague();
        ILeaguePersistence leagueDB = databaseFactory.createLeaguePersistence();
        leagueDB.loadLeague(1, league);
        league.setLeagueDate(LocalDate.of(2020, Month.NOVEMBER, 17));
        league.getScheduleSystem().setPlayoffSchedule(null);
        AbstractState agingState = stateMachineFactory.createAgingState();
        agingState.setLeague(league);

        Assert.assertTrue(agingState.onRun() instanceof PersistState);
        Assert.assertEquals(19, league.getConferences().get(0).getDivisions().get(0).getTeams().get(0).getPlayers().get(0).getPlayerAgeInfo().getAgeInYears());
        Assert.assertEquals(331, league.getFreeAgents().get(0).getPlayerAgeInfo().getElapsedDaysFromLastBDay());
    }
}
