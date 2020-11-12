package com.IceHockeyLeagueTest.StateMachineTest.StatesTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.Conference.Conference;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.Division;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.League;
import com.IceHockeyLeague.LeagueManager.Player.FreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Player.TeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Team.Team;
import com.IceHockeyLeague.LeagueScheduler.ISchedule;
import com.IceHockeyLeague.LeagueScheduler.Schedule;
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

    @BeforeClass
    public static void setup() {
        AbstractAppFactory appFactory = AppFactoryTest.createAppFactoryTest();
        stateMachineFactory = appFactory.createStateMachineFactory();
    }


    private ILeague createDummyLeague() {
        ILeague league = new League();
        league.setConferences(new ArrayList<>());
        IConference conference = new Conference();
        conference.setDivisions(new ArrayList<>());
        IDivision division = new Division();
        division.setTeams(new ArrayList<>());
        ITeam team = new Team();
        team.setPlayers(new ArrayList<>());
        ITeamPlayer player = new TeamPlayer();
        player.setPlayerAge(20);
        player.setElapsedDaysFromLastBDay(364);
        league.setFreeAgents(new ArrayList<>());
        IFreeAgent freeAgent = new FreeAgent();
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
        ISchedule schedule = new Schedule();
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
