package com.IceHockeyLeagueTest.StateMachineTest.StatesTest;

import com.IO.AbstractIOFactory;
import com.IO.IOFactory;
import com.IceHockeyLeague.LeagueFileHandler.AbstractLeagueFileHandlerFactory;
import com.IceHockeyLeague.LeagueFileHandler.LeagueFileHandlerFactory;
import com.IceHockeyLeague.LeagueManager.Conference.Conference;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.Division;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.League;
import com.IceHockeyLeague.LeagueManager.Player.*;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Team.Team;
import com.IceHockeyLeague.StateMachine.AbstractStateMachineFactory;
import com.IceHockeyLeague.StateMachine.StateMachineFactory;
import com.IceHockeyLeague.StateMachine.States.AbstractState;
import com.IceHockeyLeague.StateMachine.States.PersistState;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;

public class AdvanceToNextSeasonStateTest {

    @BeforeClass
    public static void setup() {
        AbstractIOFactory.setFactory(new IOFactory());
        AbstractLeagueFileHandlerFactory.setFactory(new LeagueFileHandlerFactory());
        AbstractStateMachineFactory.setFactory(
                new StateMachineFactory(
                        AbstractIOFactory.getFactory().getCommandLineInput(),
                        AbstractIOFactory.getFactory().getCommandLineOutput(),
                        LeagueFileHandlerFactory.getFactory().getLeagueFileReader(),
                        LeagueFileHandlerFactory.getFactory().getJsonParser(),
                        LeagueFileHandlerFactory.getFactory().getLeagueFileValidator()
                )
        );
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
        player.setPlayerAge(100);
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
        league.setLeagueDate(LocalDate.of(Year.now().getValue() + 1, Month.SEPTEMBER, 27));
        league.getScheduleSystem().setRegularSeasonStartDate(LocalDate.now());

        AbstractState advanceToNextSeasonState = AbstractStateMachineFactory.getFactory().getAdvanceToNextSeasonState();
        advanceToNextSeasonState.setLeague(league);

        Assert.assertTrue(advanceToNextSeasonState.onRun() instanceof PersistState);
        Assert.assertEquals(101, league.getConferences().get(0).getDivisions().get(0).getTeams().get(0).getPlayers().get(0).getPlayerAge());
        Assert.assertEquals(2, league.getFreeAgents().get(0).getElapsedDaysFromLastBDay());
    }
}
