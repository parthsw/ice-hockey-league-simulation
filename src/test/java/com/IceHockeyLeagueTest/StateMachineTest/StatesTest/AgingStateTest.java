package com.IceHockeyLeagueTest.StateMachineTest.StatesTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Player.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueScheduler.ILeagueSchedulerFactory;
import com.IceHockeyLeague.LeagueScheduler.ISchedule;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;
import com.IceHockeyLeague.StateMachine.States.AbstractState;
import com.IceHockeyLeague.StateMachine.States.AdvanceToNextSeasonState;
import com.IceHockeyLeague.StateMachine.States.PersistState;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AgingStateTest {
    private static IStateMachineFactory stateMachineFactory;
    private static ILeagueManagerFactory leagueManagerFactory;
    private static ILeagueSchedulerFactory leagueSchedulerFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory appFactory = AppFactoryTest.createAppFactory();
        AbstractAppFactory.setStateMachineFactory(appFactory.createStateMachineFactory());
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        stateMachineFactory = AbstractAppFactory.getStateMachineFactory();
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
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
        player.setPlayerAge(20);
        player.setElapsedDaysFromLastBDay(364);
        league.setFreeAgents(new ArrayList<>());
        IFreeAgent freeAgent = leagueManagerFactory.createFreeAgent();
        freeAgent.setPlayerAge(50);

        league.addConference(conference);
        conference.addDivision(division);
        division.addTeam(team);
        team.addPlayer(player);
        league.addFreeAgent(freeAgent);

        return league;
    }

    @Test
    public void onRunTest() {
        ILeague league = createDummyLeague();

        AbstractState agingState = stateMachineFactory.createAgingState();
        agingState.setLeague(league);

        Assert.assertTrue(agingState.onRun() instanceof PersistState);
        Assert.assertEquals(21, league.getConferences().get(0).getDivisions().get(0).getTeams().get(0).getPlayers().get(0).getPlayerAge());
        Assert.assertEquals(1, league.getFreeAgents().get(0).getElapsedDaysFromLastBDay());
    }

    @Test
    public void onRunAlternateTest() {
        ILeague league = createDummyLeague();
        List<ISchedule> playoffScheduleList = new ArrayList<>();
        ISchedule schedule = leagueSchedulerFactory.createSchedule();
        schedule.setIsGamePlayed(true);
        playoffScheduleList.add(schedule);
        league.getScheduleSystem().setPlayoffSchedule(playoffScheduleList);

        AbstractState agingState = stateMachineFactory.createAgingState();
        agingState.setLeague(league);

        Assert.assertTrue(agingState.onRun() instanceof AdvanceToNextSeasonState);
        Assert.assertEquals(21, league.getConferences().get(0).getDivisions().get(0).getTeams().get(0).getPlayers().get(0).getPlayerAge());
        Assert.assertEquals(1, league.getFreeAgents().get(0).getElapsedDaysFromLastBDay());
    }
}
