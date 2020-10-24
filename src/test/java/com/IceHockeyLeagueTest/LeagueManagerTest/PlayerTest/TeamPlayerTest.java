package com.IceHockeyLeagueTest.LeagueManagerTest.PlayerTest;

import com.IceHockeyLeague.LeagueManager.LeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeagueTest.LeagueManagerTest.TestLeagueManagerFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TeamPlayerTest {

    @Before
    public void setup() {
        LeagueManagerFactory.setFactory(new TestLeagueManagerFactory());
    }

    @Test
    public void ConstructorTest() {
        ITeamPlayer teamPlayer = LeagueManagerFactory.getFactory().getTeamPlayer();
        Assert.assertEquals(-1, teamPlayer.getTeamID());
        Assert.assertEquals(-1, teamPlayer.getTeamPlayerID());
    }

    @Test
    public void getTeamPlayerIDTest() {
        ITeamPlayer teamPlayer = LeagueManagerFactory.getFactory().getTeamPlayer();
        teamPlayer.setTeamPlayerID(2);
        Assert.assertEquals(2, teamPlayer.getTeamPlayerID());
    }

    @Test
    public void setTeamPlayerIDTest() {
        ITeamPlayer teamPlayer = LeagueManagerFactory.getFactory().getTeamPlayer();
        teamPlayer.setTeamPlayerID(9);
        Assert.assertEquals(9, teamPlayer.getTeamPlayerID());
    }

    @Test
    public void isCaptainTest() {
        ITeamPlayer teamPlayer = LeagueManagerFactory.getFactory().getTeamPlayer();
        teamPlayer.setIsCaptain(true);
        Assert.assertTrue(teamPlayer.isCaptain());
    }

    @Test
    public void setIsCaptainTest() {
        ITeamPlayer teamPlayer = LeagueManagerFactory.getFactory().getTeamPlayer();
        teamPlayer.setIsCaptain(false);
        Assert.assertFalse(teamPlayer.isCaptain());
    }

    @Test
    public void getTeamIDTest() {
        ITeamPlayer teamPlayer = LeagueManagerFactory.getFactory().getTeamPlayer();
        teamPlayer.setTeamID(11);
        Assert.assertEquals(11, teamPlayer.getTeamID());
    }

    @Test
    public void setTeamIDTest() {
        ITeamPlayer teamPlayer = LeagueManagerFactory.getFactory().getTeamPlayer();
        teamPlayer.setTeamID(5);
        Assert.assertEquals(5, teamPlayer.getTeamID());
    }
}
