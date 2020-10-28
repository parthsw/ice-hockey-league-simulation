package com.IceHockeyLeagueTest.LeagueManagerTest.PlayerTest;

import com.IceHockeyLeague.LeagueManager.LeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.IPlayer;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerStats;
import com.IceHockeyLeagueTest.LeagueManagerTest.TestLeagueManagerFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

    private static final String FORWARD = "forward";

    @Before
    public void setup() {
        LeagueManagerFactory.setFactory(new TestLeagueManagerFactory());
    }

    @Test
    public void ConstructorTest() {
        IPlayer player = LeagueManagerFactory.getFactory().getPlayer();
        Assert.assertFalse(player.getInjuredStatus());
        Assert.assertFalse(player.getIsRetired());
    }

    @Test
    public void getPlayerNameTest() {
        IPlayer player = LeagueManagerFactory.getFactory().getPlayer();
        player.setPlayerName("Joe Doe");
        Assert.assertEquals("Joe Doe", player.getPlayerName());
    }

    @Test
    public void setPlayerNameTest() {
        IPlayer player = LeagueManagerFactory.getFactory().getPlayer();
        player.setPlayerName("John");
        Assert.assertEquals("John", player.getPlayerName());
    }

    @Test
    public void getPlayerAgeTest() {
        IPlayer player = LeagueManagerFactory.getFactory().getPlayer();
        player.setPlayerAge(20);
        Assert.assertEquals(20, player.getPlayerAge());
    }

    @Test
    public void setPlayerAgeTest() {
        IPlayer player = LeagueManagerFactory.getFactory().getPlayer();
        player.setPlayerAge(46);
        Assert.assertEquals(46, player.getPlayerAge());
    }

    @Test
    public void getIsInjuredTest() {
        IPlayer player = LeagueManagerFactory.getFactory().getPlayer();
        player.setInjuredStatus(true);
        Assert.assertTrue(player.getInjuredStatus());
    }

    @Test
    public void setIsInjuredTest() {
        IPlayer player = LeagueManagerFactory.getFactory().getPlayer();
        player.setInjuredStatus(false);
        Assert.assertFalse(player.getInjuredStatus());
    }

    @Test
    public void getIsRetiredTest() {
        IPlayer player = LeagueManagerFactory.getFactory().getPlayer();
        player.setIsRetired(true);
        Assert.assertTrue(player.getIsRetired());
    }

    @Test
    public void setIsRetiredTest() {
        IPlayer player = LeagueManagerFactory.getFactory().getPlayer();
        player.setIsRetired(false);
        Assert.assertFalse(player.getIsRetired());
    }

    @Test
    public void getPlayerStatsTest() {
        IPlayer player = LeagueManagerFactory.getFactory().getPlayer();
        IPlayerStats expectedStats = createPlayerStats();

        player.setPlayerStats(expectedStats);
        IPlayerStats actualStats = player.getPlayerStats();

        Assert.assertEquals(expectedStats.getChecking(), actualStats.getChecking());
    }

    @Test
    public void calculateStrength() {
        IPlayer player = LeagueManagerFactory.getFactory().getPlayer();
        IPlayerStats forwardStats = createPlayerStats();

        Assert.assertEquals(21.5, player.calculateStrength(forwardStats), 0.0);
    }

    @Test
    public void isValidTest() {
        IPlayer player = LeagueManagerFactory.getFactory().getPlayer();
        Assert.assertFalse(player.isValid());
    }

    private IPlayerStats createPlayerStats() {
        IPlayerStats stats = LeagueManagerFactory.getFactory().getPlayerStats();
        stats.setSaving(9);
        stats.setChecking(11);
        stats.setShooting(8);
        stats.setSkating(8);
        stats.setPosition(FORWARD);
        return stats;
    }
}
