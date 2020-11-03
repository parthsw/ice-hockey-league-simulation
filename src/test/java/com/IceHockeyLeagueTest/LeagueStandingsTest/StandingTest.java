package com.IceHockeyLeagueTest.LeagueStandingsTest;

import com.IceHockeyLeague.LeagueManager.Conference.Conference;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.Division;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Team.Team;
import com.IceHockeyLeague.LeagueStandings.IStanding;
import com.IceHockeyLeague.LeagueStandings.Standing;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StandingTest {

    @Test
    public void setConferenceTest() {
        IStanding standing = new Standing();
        IConference conference = new Conference();
        standing.setConference(conference);
        Assert.assertEquals(conference, standing.getConference());
    }

    @Test
    public void setDivisionTest() {
        IStanding standing = new Standing();
        IDivision division = new Division();
        standing.setDivision(division);
        Assert.assertEquals(division, standing.getDivision());
    }

    @Test
    public void setTeamTest() {
        IStanding standing = new Standing();
        ITeam team = new Team();
        standing.setTeam(team);
        Assert.assertEquals(team, standing.getTeam());
    }

    @Test
    public void setGamesPlayedTest() {
        IStanding standing = new Standing();
        standing.setGamesPlayed(5);
        Assert.assertEquals(5, standing.getGamesPlayed());
    }

    @Test
    public void incrementGamesPlayedTest() {
        IStanding standing = new Standing();
        standing.setGamesPlayed(5);
        standing.incrementGamesPlayed();
        Assert.assertEquals(6, standing.getGamesPlayed());
    }

    @Test
    public void setGamesWonTest() {
        IStanding standing = new Standing();
        standing.setGamesWon(3);
        Assert.assertEquals(3, standing.getGamesWon());
    }

    @Test
    public void incrementGamesWonTest() {
        IStanding standing = new Standing();
        standing.setGamesWon(3);
        standing.incrementGamesWon();
        Assert.assertEquals(4, standing.getGamesWon());
    }

    @Test
    public void setPointsTest() {
        IStanding standing = new Standing();
        standing.setPoints(8);
        Assert.assertEquals(8, standing.getPoints());
    }

    @Test
    public void incrementPointsTest() {
        IStanding standing = new Standing();
        standing.setPoints(8);
        standing.incrementPoints();
        Assert.assertEquals(10, standing.getPoints());
    }

    @Test
    public void sortStandingsOnDifferentPointsTest() {
        List<IStanding> standings = new ArrayList<>();

        Standing standing1 = new Standing();
        standing1.setPoints(6);
        standing1.setGamesWon(3);
        standing1.setGamesPlayed(5);

        Standing standing2 = new Standing();
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

        Standing standing1 = new Standing();
        standing1.setPoints(6);
        standing1.setGamesWon(2);
        standing1.setGamesPlayed(5);

        Standing standing2 = new Standing();
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

        Standing standing1 = new Standing();
        standing1.setPoints(6);
        standing1.setGamesWon(3);
        standing1.setGamesPlayed(5);

        Standing standing2 = new Standing();
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
