package com.IceHockeyLeagueTest.LeagueManagerTest.StandingsTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Standings.IStanding;
import com.IceHockeyLeague.LeagueManager.Standings.Standing;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StandingTest {
    private static ILeagueManagerFactory leagueManagerFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
    }

    @Test
    public void setConferenceTest() {
        IStanding standing = leagueManagerFactory.createStanding();
        IConference conference = leagueManagerFactory.createConference();
        standing.setConference(conference);
        Assert.assertEquals(conference, standing.getConference());
    }

    @Test
    public void setDivisionTest() {
        IStanding standing = leagueManagerFactory.createStanding();
        IDivision division = leagueManagerFactory.createDivision();
        standing.setDivision(division);
        Assert.assertEquals(division, standing.getDivision());
    }

    @Test
    public void setTeamTest() {
        IStanding standing = leagueManagerFactory.createStanding();
        ITeam team = leagueManagerFactory.createTeam();
        standing.setTeam(team);
        Assert.assertEquals(team, standing.getTeam());
    }

    @Test
    public void setGamesPlayedTest() {
        IStanding standing = leagueManagerFactory.createStanding();
        standing.setGamesPlayed(5);
        Assert.assertEquals(5, standing.getGamesPlayed());
    }

    @Test
    public void incrementGamesPlayedTest() {
        IStanding standing = leagueManagerFactory.createStanding();
        standing.setGamesPlayed(5);
        standing.incrementGamesPlayed();
        Assert.assertEquals(6, standing.getGamesPlayed());
    }

    @Test
    public void setGamesWonTest() {
        IStanding standing = leagueManagerFactory.createStanding();
        standing.setGamesWon(3);
        Assert.assertEquals(3, standing.getGamesWon());
    }

    @Test
    public void incrementGamesWonTest() {
        IStanding standing = leagueManagerFactory.createStanding();
        standing.setGamesWon(3);
        standing.incrementGamesWon();
        Assert.assertEquals(4, standing.getGamesWon());
    }

    @Test
    public void setPointsTest() {
        IStanding standing = leagueManagerFactory.createStanding();
        standing.setPoints(8);
        Assert.assertEquals(8, standing.getPoints());
    }

    @Test
    public void incrementPointsTest() {
        IStanding standing = leagueManagerFactory.createStanding();
        standing.setPoints(8);
        standing.incrementPoints();
        Assert.assertEquals(10, standing.getPoints());
    }

    @Test
    public void sortStandingsOnDifferentPointsTest() {
        List<IStanding> standings = new ArrayList<>();

        IStanding standing1 = leagueManagerFactory.createStanding();
        standing1.setPoints(6);
        standing1.setGamesWon(3);
        standing1.setGamesPlayed(5);

        IStanding standing2 = leagueManagerFactory.createStanding();
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

        IStanding standing1 = leagueManagerFactory.createStanding();
        standing1.setPoints(6);
        standing1.setGamesWon(2);
        standing1.setGamesPlayed(5);

        IStanding standing2 = leagueManagerFactory.createStanding();
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

        IStanding standing1 = leagueManagerFactory.createStanding();
        standing1.setPoints(6);
        standing1.setGamesWon(3);
        standing1.setGamesPlayed(5);

        IStanding standing2 = leagueManagerFactory.createStanding();
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
