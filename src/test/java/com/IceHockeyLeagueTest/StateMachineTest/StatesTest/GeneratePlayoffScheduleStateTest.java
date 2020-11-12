package com.IceHockeyLeagueTest.StateMachineTest.StatesTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.Conference.Conference;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.Division;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.League;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Team.Team;
import com.IceHockeyLeague.LeagueStandings.IStanding;
import com.IceHockeyLeague.LeagueStandings.Standing;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;
import com.IceHockeyLeague.StateMachine.States.AbstractState;
import com.IceHockeyLeague.StateMachine.States.TrainingState;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GeneratePlayoffScheduleStateTest {
    private static IStateMachineFactory stateMachineFactory;

    private ILeague createDummyLeague() {
        ILeague league = new League();
        league.setConferences(new ArrayList<>());

        IConference conference1 = new Conference();
        conference1.setDivisions(new ArrayList<>());
        IConference conference2 = new Conference();
        conference2.setDivisions(new ArrayList<>());

        IDivision division1 = new Division();
        division1.setTeams(new ArrayList<>());
        IDivision division2 = new Division();
        division2.setTeams(new ArrayList<>());
        IDivision division3 = new Division();
        division3.setTeams(new ArrayList<>());
        IDivision division4 = new Division();
        division4.setTeams(new ArrayList<>());

        ITeam team1 = new Team();
        ITeam team2 = new Team();
        ITeam team3 = new Team();
        ITeam team4 = new Team();
        ITeam team5 = new Team();
        ITeam team6 = new Team();
        ITeam team7 = new Team();
        ITeam team8 = new Team();
        ITeam team9 = new Team();
        ITeam team10 = new Team();
        ITeam team11 = new Team();
        ITeam team12 = new Team();
        ITeam team13 = new Team();
        ITeam team14 = new Team();
        ITeam team15 = new Team();
        ITeam team16 = new Team();
        ITeam team17 = new Team();
        ITeam team18 = new Team();
        ITeam team19 = new Team();
        ITeam team20 = new Team();

        league.addConference(conference1);
        league.addConference(conference2);
        conference1.addDivision(division1);
        conference1.addDivision(division2);
        conference2.addDivision(division3);
        conference2.addDivision(division4);
        division1.addTeam(team1);
        division1.addTeam(team2);
        division1.addTeam(team3);
        division1.addTeam(team4);
        division1.addTeam(team5);
        division2.addTeam(team6);
        division2.addTeam(team7);
        division2.addTeam(team8);
        division2.addTeam(team9);
        division2.addTeam(team10);
        division3.addTeam(team11);
        division3.addTeam(team12);
        division3.addTeam(team13);
        division3.addTeam(team14);
        division3.addTeam(team15);
        division4.addTeam(team16);
        division4.addTeam(team17);
        division4.addTeam(team18);
        division4.addTeam(team19);
        division4.addTeam(team20);

        return league;
    }

    private List<IStanding> createDummyStandings(ILeague league) {
        IConference conference1 = league.getConferences().get(0);
        IConference conference2 = league.getConferences().get(1);
        IDivision division1 = conference1.getDivisions().get(0);
        IDivision division2 = conference1.getDivisions().get(1);
        IDivision division3 = conference2.getDivisions().get(0);
        IDivision division4 = conference2.getDivisions().get(1);
        ITeam team1 = division1.getTeams().get(0);
        ITeam team2 = division1.getTeams().get(1);
        ITeam team3 = division1.getTeams().get(2);
        ITeam team4 = division1.getTeams().get(3);
        ITeam team5 = division1.getTeams().get(4);
        ITeam team6 = division2.getTeams().get(0);
        ITeam team7 = division2.getTeams().get(1);
        ITeam team8 = division2.getTeams().get(2);
        ITeam team9 = division2.getTeams().get(3);
        ITeam team10 = division2.getTeams().get(4);
        ITeam team11 = division3.getTeams().get(0);
        ITeam team12 = division3.getTeams().get(1);
        ITeam team13 = division3.getTeams().get(2);
        ITeam team14 = division3.getTeams().get(3);
        ITeam team15 = division3.getTeams().get(4);
        ITeam team16 = division4.getTeams().get(0);
        ITeam team17 = division4.getTeams().get(1);
        ITeam team18 = division4.getTeams().get(2);
        ITeam team19 = division4.getTeams().get(3);
        ITeam team20 = division4.getTeams().get(4);

        IStanding standing1 = new Standing();
        standing1.setConference(conference1);
        standing1.setDivision(division1);
        standing1.setTeam(team1);
        standing1.setGamesPlayed(7);
        standing1.setGamesWon(2);
        standing1.setPoints(4);

        IStanding standing2 = new Standing();
        standing2.setConference(conference1);
        standing2.setDivision(division1);
        standing2.setTeam(team2);
        standing2.setGamesPlayed(7);
        standing2.setGamesWon(6);
        standing2.setPoints(12);

        IStanding standing3 = new Standing();
        standing3.setConference(conference1);
        standing3.setDivision(division1);
        standing3.setTeam(team3);
        standing3.setGamesPlayed(8);
        standing3.setGamesWon(4);
        standing3.setPoints(8);

        IStanding standing4 = new Standing();
        standing4.setConference(conference1);
        standing4.setDivision(division1);
        standing4.setTeam(team4);
        standing4.setGamesPlayed(7);
        standing4.setGamesWon(5);
        standing4.setPoints(11);

        IStanding standing5 = new Standing();
        standing5.setConference(conference1);
        standing5.setDivision(division1);
        standing5.setTeam(team5);
        standing5.setGamesPlayed(8);
        standing5.setGamesWon(4);
        standing5.setPoints(9);

        IStanding standing6 = new Standing();
        standing6.setConference(conference1);
        standing6.setDivision(division2);
        standing6.setTeam(team6);
        standing6.setGamesPlayed(7);
        standing6.setGamesWon(6);
        standing6.setPoints(12);

        IStanding standing7 = new Standing();
        standing7.setConference(conference1);
        standing7.setDivision(division2);
        standing7.setTeam(team7);
        standing7.setGamesPlayed(8);
        standing7.setGamesWon(5);
        standing7.setPoints(10);

        IStanding standing8 = new Standing();
        standing8.setConference(conference1);
        standing8.setDivision(division2);
        standing8.setTeam(team8);
        standing8.setGamesPlayed(7);
        standing8.setGamesWon(4);
        standing8.setPoints(9);

        IStanding standing9 = new Standing();
        standing9.setConference(conference1);
        standing9.setDivision(division2);
        standing9.setTeam(team9);
        standing9.setGamesPlayed(8);
        standing9.setGamesWon(2);
        standing9.setPoints(4);

        IStanding standing10 = new Standing();
        standing10.setConference(conference1);
        standing10.setDivision(division2);
        standing10.setTeam(team10);
        standing10.setGamesPlayed(8);
        standing10.setGamesWon(4);
        standing10.setPoints(8);

        IStanding standing11 = new Standing();
        standing11.setConference(conference2);
        standing11.setDivision(division3);
        standing11.setTeam(team11);
        standing11.setGamesPlayed(8);
        standing11.setGamesWon(3);
        standing11.setPoints(7);

        IStanding standing12 = new Standing();
        standing12.setConference(conference2);
        standing12.setDivision(division3);
        standing12.setTeam(team12);
        standing12.setGamesPlayed(8);
        standing12.setGamesWon(3);
        standing12.setPoints(6);

        IStanding standing13 = new Standing();
        standing13.setConference(conference2);
        standing13.setDivision(division3);
        standing13.setTeam(team13);
        standing13.setGamesPlayed(7);
        standing13.setGamesWon(5);
        standing13.setPoints(10);

        IStanding standing14 = new Standing();
        standing14.setConference(conference2);
        standing14.setDivision(division3);
        standing14.setTeam(team14);
        standing14.setGamesPlayed(7);
        standing14.setGamesWon(4);
        standing14.setPoints(8);

        IStanding standing15 = new Standing();
        standing15.setConference(conference2);
        standing15.setDivision(division3);
        standing15.setTeam(team15);
        standing15.setGamesPlayed(7);
        standing15.setGamesWon(6);
        standing15.setPoints(12);

        IStanding standing16 = new Standing();
        standing16.setConference(conference2);
        standing16.setDivision(division4);
        standing16.setTeam(team16);
        standing16.setGamesPlayed(7);
        standing16.setGamesWon(7);
        standing16.setPoints(14);

        IStanding standing17 = new Standing();
        standing17.setConference(conference2);
        standing17.setDivision(division4);
        standing17.setTeam(team17);
        standing17.setGamesPlayed(6);
        standing17.setGamesWon(4);
        standing17.setPoints(9);

        IStanding standing18 = new Standing();
        standing18.setConference(conference2);
        standing18.setDivision(division4);
        standing18.setTeam(team18);
        standing18.setGamesPlayed(8);
        standing18.setGamesWon(0);
        standing18.setPoints(1);

        IStanding standing19 = new Standing();
        standing19.setConference(conference2);
        standing19.setDivision(division4);
        standing19.setTeam(team19);
        standing19.setGamesPlayed(7);
        standing19.setGamesWon(3);
        standing19.setPoints(6);

        IStanding standing20 = new Standing();
        standing20.setConference(conference2);
        standing20.setDivision(division4);
        standing20.setTeam(team20);
        standing20.setGamesPlayed(7);
        standing20.setGamesWon(4);
        standing20.setPoints(8);

        List<IStanding> standings = new ArrayList<>();
        standings.add(standing1);
        standings.add(standing2);
        standings.add(standing3);
        standings.add(standing4);
        standings.add(standing5);
        standings.add(standing6);
        standings.add(standing7);
        standings.add(standing8);
        standings.add(standing9);
        standings.add(standing10);
        standings.add(standing11);
        standings.add(standing12);
        standings.add(standing13);
        standings.add(standing14);
        standings.add(standing15);
        standings.add(standing16);
        standings.add(standing17);
        standings.add(standing18);
        standings.add(standing19);
        standings.add(standing20);

        return standings;
    }

    @BeforeClass
    public static void setup() {
        AbstractAppFactory appFactory = AppFactoryTest.createAppFactoryTest();
        stateMachineFactory = appFactory.createStateMachineFactory();
    }

    @Test
    public void onRunTest() {
        ILeague league = createDummyLeague();
        List<IStanding> standingsList = createDummyStandings(league);
        league.getStandingSystem().setStandings(standingsList);
        league.getScheduleSystem().setPlayoffStartDate(LocalDate.now());
        league.getScheduleSystem().setPlayoffEndDate(LocalDate.now().plusDays(50));

        AbstractState generatePlayoffScheduleStateState = stateMachineFactory.createGeneratePlayoffScheduleState();
        generatePlayoffScheduleStateState.setLeague(league);

        Assert.assertTrue(generatePlayoffScheduleStateState.onRun() instanceof TrainingState);
        Assert.assertEquals(8, league.getScheduleSystem().getPlayoffSchedule().size());
    }
}
