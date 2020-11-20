package com.IceHockeyLeagueTest.StateMachineTest.StatesTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.Database.IDatabaseFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.ILeaguePersistence;
import com.IceHockeyLeague.LeagueManager.Scheduler.ISchedule;
import com.IceHockeyLeague.LeagueManager.Standings.IStanding;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;
import com.IceHockeyLeague.StateMachine.States.AbstractState;
import com.IceHockeyLeague.StateMachine.States.PersistState;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class AdvanceToNextSeasonStateTest {
    private static IStateMachineFactory stateMachineFactory;
    private static ILeagueManagerFactory leagueManagerFactory;
    private static IDatabaseFactory databaseFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory appFactory = AppFactoryTest.createAppFactory();
        AbstractAppFactory.setStateMachineFactory(appFactory.createStateMachineFactory());
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        stateMachineFactory = AbstractAppFactory.getStateMachineFactory();
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        databaseFactory = appFactory.createDatabaseFactory();
    }

    @Test
    public void onRunTest() {
        ILeague league = leagueManagerFactory.createLeague();
        ILeaguePersistence leagueDB = databaseFactory.createLeaguePersistence();
        List<IStanding> standings = new ArrayList<>();
        ISchedule schedule = leagueManagerFactory.createSchedule();
        schedule.setFirstTeam(leagueManagerFactory.createTeam());
        schedule.setSecondTeam(leagueManagerFactory.createTeam());
        leagueDB.loadLeague(1, league);

        league.getStandingSystem().setStandings(standings);
        league.setLeagueDate(LocalDate.of(Year.now().getValue() + 1, Month.SEPTEMBER, 27));
        league.getScheduleSystem().setRegularSeasonStartDate(LocalDate.now());

        schedule.setWinningTeam(leagueManagerFactory.createTeam());
        List<ISchedule> playoffList = new ArrayList<>();
        playoffList.add(schedule);
        league.getScheduleSystem().setPlayoffSchedule(playoffList);

        AbstractState advanceToNextSeasonState = stateMachineFactory.createAdvanceToNextSeasonState();
        advanceToNextSeasonState.setLeague(league);

        Assert.assertTrue(advanceToNextSeasonState.onRun() instanceof PersistState);
    }
}
