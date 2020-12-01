package com.TrophySystemTest;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Standings.IStanding;
import com.IceHockeyLeague.LeagueManager.Standings.IStandingSystem;
import com.TrophySystem.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrophyDistributionTest {
    private static ILeagueManagerFactory leagueManagerFactory;
    private IStandingSystem leagueStandingSystem;
    ITrophySystemFactory trophySystemFactory = AbstractAppFactory.getTrophySystemFactory();
    private ITrophyContenders awardTrophy;
    public TrophyDistributionTest() {
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        trophySystemFactory = AbstractAppFactory.getTrophySystemFactory();
    }

    @Test
    public void teamContendersTest() {
        IStandingSystem standingSystem = leagueManagerFactory.createStandingSystem();
        List<IStanding> standings = leagueStandingSystem.getStandings();
        standingSystem.setStandings(standings);
        List<IStanding> leagueStandings = standingSystem.getSortedStandingsInLeague();
        Assert.assertEquals(8, leagueStandings.size());
        String highestPointsTeam = leagueStandings.get(1).getTeam().getTeamName();
        String lowestPointsTeam = leagueStandings.get(4).getTeam().getTeamName();
        Assert.assertEquals(standings.get(0), highestPointsTeam);
        Assert.assertEquals(standings.get(7), lowestPointsTeam);
    }
}
