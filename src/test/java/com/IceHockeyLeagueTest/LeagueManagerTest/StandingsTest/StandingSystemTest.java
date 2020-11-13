package com.IceHockeyLeagueTest.LeagueManagerTest.StandingsTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Standings.IStanding;
import com.IceHockeyLeague.LeagueManager.Standings.IStandingSystem;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StandingSystemTest {
    private static ILeagueManagerFactory leagueManagerFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
    }

    private List<IStanding> createDummyStandings() {
        IConference conference1 = leagueManagerFactory.createConference();
        IConference conference2 = leagueManagerFactory.createConference();
        IDivision division1 = leagueManagerFactory.createDivision();
        IDivision division2 = leagueManagerFactory.createDivision();
        IDivision division3 = leagueManagerFactory.createDivision();
        IDivision division4 = leagueManagerFactory.createDivision();
        ITeam team1 = leagueManagerFactory.createTeam();
        ITeam team2 = leagueManagerFactory.createTeam();
        ITeam team3 = leagueManagerFactory.createTeam();
        ITeam team4 = leagueManagerFactory.createTeam();
        ITeam team5 = leagueManagerFactory.createTeam();
        ITeam team6 = leagueManagerFactory.createTeam();
        ITeam team7 = leagueManagerFactory.createTeam();
        ITeam team8 = leagueManagerFactory.createTeam();

        IStanding standing1 = leagueManagerFactory.createStanding();
        standing1.setConference(conference1);
        standing1.setDivision(division1);
        standing1.setTeam(team1);
        standing1.setGamesPlayed(7);
        standing1.setGamesWon(1);
        standing1.setPoints(2);

        IStanding standing2 = leagueManagerFactory.createStanding();
        standing2.setConference(conference1);
        standing2.setDivision(division1);
        standing2.setTeam(team2);
        standing2.setGamesPlayed(7);
        standing2.setGamesWon(3);
        standing2.setPoints(6);

        IStanding standing3 = leagueManagerFactory.createStanding();
        standing3.setConference(conference1);
        standing3.setDivision(division2);
        standing3.setTeam(team3);
        standing3.setGamesPlayed(7);
        standing3.setGamesWon(2);
        standing3.setPoints(4);

        IStanding standing4 = leagueManagerFactory.createStanding();
        standing4.setConference(conference1);
        standing4.setDivision(division2);
        standing4.setTeam(team4);
        standing4.setGamesPlayed(6);
        standing4.setGamesWon(2);
        standing4.setPoints(4);

        IStanding standing5 = leagueManagerFactory.createStanding();
        standing5.setConference(conference2);
        standing5.setDivision(division3);
        standing5.setTeam(team5);
        standing5.setGamesPlayed(7);
        standing5.setGamesWon(5);
        standing5.setPoints(12);

        IStanding standing6 = leagueManagerFactory.createStanding();
        standing6.setConference(conference2);
        standing6.setDivision(division3);
        standing6.setTeam(team6);
        standing6.setGamesPlayed(7);
        standing6.setGamesWon(4);
        standing6.setPoints(8);

        IStanding standing7 = leagueManagerFactory.createStanding();
        standing7.setConference(conference2);
        standing7.setDivision(division4);
        standing7.setTeam(team7);
        standing7.setGamesPlayed(7);
        standing7.setGamesWon(0);
        standing7.setPoints(0);

        IStanding standing8 = leagueManagerFactory.createStanding();
        standing8.setConference(conference2);
        standing8.setDivision(division4);
        standing8.setTeam(team8);
        standing8.setGamesPlayed(7);
        standing8.setGamesWon(6);
        standing8.setPoints(12);

        List<IStanding> standings = new ArrayList<>();
        standings.add(standing1);
        standings.add(standing2);
        standings.add(standing3);
        standings.add(standing4);
        standings.add(standing5);
        standings.add(standing6);
        standings.add(standing7);
        standings.add(standing8);

        return standings;
    }

    private ILeague createDummyLeague() {
        ILeague league = leagueManagerFactory.createLeague();
        league.setConferences(new ArrayList<>());

        IConference conference1 = leagueManagerFactory.createConference();
        conference1.setDivisions(new ArrayList<>());
        IConference conference2 = leagueManagerFactory.createConference();
        conference2.setDivisions(new ArrayList<>());

        IDivision division1 = leagueManagerFactory.createDivision();
        division1.setTeams(new ArrayList<>());
        IDivision division2 = leagueManagerFactory.createDivision();
        division2.setTeams(new ArrayList<>());
        IDivision division3 = leagueManagerFactory.createDivision();
        division3.setTeams(new ArrayList<>());
        IDivision division4 = leagueManagerFactory.createDivision();
        division4.setTeams(new ArrayList<>());

        ITeam team1 = leagueManagerFactory.createTeam();
        ITeam team2 = leagueManagerFactory.createTeam();
        ITeam team3 = leagueManagerFactory.createTeam();
        ITeam team4 = leagueManagerFactory.createTeam();
        ITeam team5 = leagueManagerFactory.createTeam();
        ITeam team6 = leagueManagerFactory.createTeam();
        ITeam team7 = leagueManagerFactory.createTeam();
        ITeam team8 = leagueManagerFactory.createTeam();

        league.addConference(conference1);
        league.addConference(conference2);
        conference1.addDivision(division1);
        conference1.addDivision(division2);
        conference2.addDivision(division3);
        conference2.addDivision(division4);
        division1.addTeam(team1);
        division1.addTeam(team2);
        division2.addTeam(team3);
        division2.addTeam(team4);
        division3.addTeam(team5);
        division3.addTeam(team6);
        division4.addTeam(team7);
        division4.addTeam(team8);

        return league;
    }

    @Test
    public void setStandingsTest() {
        IStandingSystem standingSystem = leagueManagerFactory.createStandingSystem();
        standingSystem.setStandings(createDummyStandings());
        Assert.assertEquals(8, standingSystem.getStandings().size());
    }

    @Test
    public void initializeStandingsTest() {
        IStandingSystem standingSystem = leagueManagerFactory.createStandingSystem();
        ILeague dummyLeague = createDummyLeague();

        standingSystem.initializeStandings(dummyLeague);
        List<IStanding> standings = standingSystem.getStandings();
        Assert.assertEquals(8, standings.size());
    }

    @Test
    public void updateStatsForWinningTeamTest() {
        IStandingSystem standingSystem = leagueManagerFactory.createStandingSystem();
        List<IStanding> standings = createDummyStandings();
        standingSystem.setStandings(standings);

        IStanding standing = standings.get(0);
        standingSystem.updateStatsForWinningTeam(standing.getConference(), standing.getDivision(), standing.getTeam());

        Assert.assertEquals(8, standing.getGamesPlayed());
        Assert.assertEquals(2, standing.getGamesWon());
        Assert.assertEquals(4, standing.getPoints());
    }

    @Test
    public void updateStatsForLosingTeamTest() {
        IStandingSystem standingSystem = leagueManagerFactory.createStandingSystem();
        List<IStanding> standings = createDummyStandings();
        standingSystem.setStandings(standings);

        IStanding standing = standings.get(0);
        standingSystem.updateStatsForLosingTeam(standing.getConference(), standing.getDivision(), standing.getTeam());

        Assert.assertEquals(8, standing.getGamesPlayed());
        Assert.assertEquals(1, standing.getGamesWon());
        Assert.assertEquals(2, standing.getPoints());
    }

    @Test
    public void getStandingsInDivisionTest() {
        IStandingSystem standingSystem = leagueManagerFactory.createStandingSystem();
        List<IStanding> standings = createDummyStandings();
        standingSystem.setStandings(standings);

        IStanding standing = standings.get(0);
        List<IStanding> divisionStandings = standingSystem.getStandingsInDivision(standing.getDivision());

        Assert.assertEquals(2, divisionStandings.size());
        Assert.assertEquals(standings.get(0), divisionStandings.get(1));
        Assert.assertEquals(standings.get(1), divisionStandings.get(0));
    }

    @Test
    public void getStandingsInConferenceTest() {
        IStandingSystem standingSystem = leagueManagerFactory.createStandingSystem();
        List<IStanding> standings = createDummyStandings();
        standingSystem.setStandings(standings);

        IStanding standing = standings.get(0);
        List<IStanding> conferenceStandings = standingSystem.getStandingsInConference(standing.getConference());

        Assert.assertEquals(4, conferenceStandings.size());
        Assert.assertEquals(standings.get(0), conferenceStandings.get(3));
        Assert.assertEquals(standings.get(1), conferenceStandings.get(0));
        Assert.assertEquals(standings.get(2), conferenceStandings.get(2));
        Assert.assertEquals(standings.get(3), conferenceStandings.get(1));
    }

    @Test
    public void getTopStandingInConferenceTest() {
        IStandingSystem standingSystem = leagueManagerFactory.createStandingSystem();
        List<IStanding> standings = createDummyStandings();
        standingSystem.setStandings(standings);

        IStanding standing = standings.get(7);
        IStanding topStandingInConference = standingSystem.getTopStandingInConference(standing.getConference());

        Assert.assertEquals(standing, topStandingInConference);
    }
}
