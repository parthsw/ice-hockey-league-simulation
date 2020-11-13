package com.IceHockeyLeagueTest.LeagueStandingsTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueStandings.ILeagueStandingsFactory;
import com.IceHockeyLeague.LeagueStandings.IStanding;
import com.IceHockeyLeague.LeagueStandings.Standing;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StandingTest {
    private static ILeagueManagerFactory leagueManagerFactory;
    private static ILeagueStandingsFactory leagueStandingsFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        AbstractAppFactory.setLeagueStandingsFactory(appFactory.createLeagueStandingsFactory());
        leagueStandingsFactory = AbstractAppFactory.getLeagueStandingsFactory();
    }

    @Test
    public void setConferenceTest() {
        IStanding standing = leagueStandingsFactory.createStanding();
        IConference conference = leagueManagerFactory.createConference();
        standing.setConference(conference);
        Assert.assertEquals(conference, standing.getConference());
    }

    @Test
    public void setDivisionTest() {
        IStanding standing = leagueStandingsFactory.createStanding();
        IDivision division = leagueManagerFactory.createDivision();
        standing.setDivision(division);
        Assert.assertEquals(division, standing.getDivision());
    }

    @Test
    public void setTeamTest() {
        IStanding standing = leagueStandingsFactory.createStanding();
        ITeam team = leagueManagerFactory.createTeam();
        standing.setTeam(team);
        Assert.assertEquals(team, standing.getTeam());
    }

    @Test
    public void setGamesPlayedTest() {
        IStanding standing = leagueStandingsFactory.createStanding();
        standing.setGamesPlayed(5);
        Assert.assertEquals(5, standing.getGamesPlayed());
    }

    @Test
    public void incrementGamesPlayedTest() {
        IStanding standing = leagueStandingsFactory.createStanding();
        standing.setGamesPlayed(5);
        standing.incrementGamesPlayed();
        Assert.assertEquals(6, standing.getGamesPlayed());
    }

    @Test
    public void setGamesWonTest() {
        IStanding standing = leagueStandingsFactory.createStanding();
        standing.setGamesWon(3);
        Assert.assertEquals(3, standing.getGamesWon());
    }

    @Test
    public void incrementGamesWonTest() {
        IStanding standing = leagueStandingsFactory.createStanding();
        standing.setGamesWon(3);
        standing.incrementGamesWon();
        Assert.assertEquals(4, standing.getGamesWon());
    }

    @Test
    public void setPointsTest() {
        IStanding standing = leagueStandingsFactory.createStanding();
        standing.setPoints(8);
        Assert.assertEquals(8, standing.getPoints());
    }

    @Test
    public void incrementPointsTest() {
        IStanding standing = leagueStandingsFactory.createStanding();
        standing.setPoints(8);
        standing.incrementPoints();
        Assert.assertEquals(10, standing.getPoints());
    }

    @Test
    public void sortStandingsOnDifferentPointsTest() {
        List<IStanding> standings = new ArrayList<>();

        IStanding standing1 = leagueStandingsFactory.createStanding();
        standing1.setPoints(6);
        standing1.setGamesWon(3);
        standing1.setGamesPlayed(5);

        IStanding standing2 = leagueStandingsFactory.createStanding();
        standing2.setPoints(8);
        standing2.setGamesWon(4);
        standing2.setGamesPlayed(5);

        standings.add(standing1);
        standings.add(standing2);

        standings.sort(Standing.standingComparator);
        Assert.assertEquals(standing2, standings.get(0));
        Assert.assertEquals(standing1, standings.get(1));
    }

    @Test
    public void sortStandingsOnSamePointsDifferentGamesWonTest() {
        List<IStanding> standings = new ArrayList<>();

        IStanding standing1 = leagueStandingsFactory.createStanding();
        standing1.setPoints(6);
        standing1.setGamesWon(2);
        standing1.setGamesPlayed(5);

        IStanding standing2 = leagueStandingsFactory.createStanding();
        standing2.setPoints(6);
        standing2.setGamesWon(3);
        standing2.setGamesPlayed(5);

        standings.add(standing1);
        standings.add(standing2);

        standings.sort(Standing.standingComparator);
        Assert.assertEquals(standing2, standings.get(0));
        Assert.assertEquals(standing1, standings.get(1));
    }

    @Test
    public void sortStandingsOnSamePointsSameGamesWonDifferentGamesPlayedTest() {
        List<IStanding> standings = new ArrayList<>();

        IStanding standing1 = leagueStandingsFactory.createStanding();
        standing1.setPoints(6);
        standing1.setGamesWon(3);
        standing1.setGamesPlayed(5);

        IStanding standing2 = leagueStandingsFactory.createStanding();
        standing2.setPoints(6);
        standing2.setGamesWon(3);
        standing2.setGamesPlayed(4);

        standings.add(standing1);
        standings.add(standing2);

        standings.sort(Standing.standingComparator);
        Assert.assertEquals(standing2, standings.get(0));
        Assert.assertEquals(standing1, standings.get(1));
    }
}
