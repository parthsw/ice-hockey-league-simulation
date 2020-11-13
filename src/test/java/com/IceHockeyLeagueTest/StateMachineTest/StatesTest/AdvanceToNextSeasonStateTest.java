package com.IceHockeyLeagueTest.StateMachineTest.StatesTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.Database.IDatabaseFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IGamePlayConfig;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IGamePlayConfigPersistence;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Player.*;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueScheduler.ILeagueSchedulerFactory;
import com.IceHockeyLeague.LeagueScheduler.ISchedule;
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
    private static ILeagueSchedulerFactory leagueSchedulerFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory appFactory = AppFactoryTest.createAppFactory();
        AbstractAppFactory.setStateMachineFactory(appFactory.createStateMachineFactory());
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        stateMachineFactory = AbstractAppFactory.getStateMachineFactory();
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        databaseFactory = appFactory.createDatabaseFactory();
        AbstractAppFactory.setLeagueSchedulerFactory(appFactory.createLeagueSchedulerFactory());
        AbstractAppFactory.setLeagueStandingsFactory(appFactory.createLeagueStandingsFactory());
        leagueSchedulerFactory = AbstractAppFactory.getLeagueSchedulerFactory();
    }

    private ILeague createDummyLeague() {
        ILeague league = leagueManagerFactory.createLeague();
        league.setConferences(new ArrayList<>());
        IConference conference = leagueManagerFactory.createConference();
        conference.setDivisions(new ArrayList<>());
        IDivision division = leagueManagerFactory.createDivision();
        division.setTeams(new ArrayList<>());
        ITeam team = leagueManagerFactory.createTeam();
        team.setPlayers(new ArrayList<>());
        ITeamPlayer player = leagueManagerFactory.createTeamPlayer();
        player.setPlayerAge(100);
        player.setElapsedDaysFromLastBDay(364);
        league.setFreeAgents(new ArrayList<>());
        IFreeAgent freeAgent = leagueManagerFactory.createFreeAgent();
        freeAgent.setPlayerAge(50);

        league.addConference(conference);
        conference.addDivision(division);
        division.addTeam(team);
        team.addPlayer(player);
        league.addFreeAgent(freeAgent);

        IGamePlayConfigPersistence gamePlayConfigDB = databaseFactory.createGamePlayConfigPersistence();
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        gamePlayConfigDB.loadGamePlayConfig(1, gamePlayConfig);
        league.setGamePlayConfig(gamePlayConfig);

        return league;
    }

    @Test
    public void onRunTest() {
        ILeague league = createDummyLeague();
        league.setLeagueDate(LocalDate.of(Year.now().getValue() + 1, Month.SEPTEMBER, 27));
        league.getScheduleSystem().setRegularSeasonStartDate(LocalDate.now());
        ISchedule schedule = leagueSchedulerFactory.createSchedule();
        schedule.setWinningTeam(leagueManagerFactory.createTeam());
        List<ISchedule> playoffList = new ArrayList<>();
        playoffList.add(schedule);
        league.getScheduleSystem().setPlayoffSchedule(playoffList);

        AbstractState advanceToNextSeasonState = stateMachineFactory.createAdvanceToNextSeasonState();
        advanceToNextSeasonState.setLeague(league);

        Assert.assertTrue(advanceToNextSeasonState.onRun() instanceof PersistState);
        Assert.assertEquals(101, league.getConferences().get(0).getDivisions().get(0).getTeams().get(0).getPlayers().get(0).getPlayerAge());
        Assert.assertEquals(2, league.getFreeAgents().get(0).getElapsedDaysFromLastBDay());
    }
}
