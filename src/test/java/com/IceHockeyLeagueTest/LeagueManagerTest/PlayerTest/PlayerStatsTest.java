package com.IceHockeyLeagueTest.LeagueManagerTest.PlayerTest;

import com.IceHockeyLeague.LeagueManager.LeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerStats;
import com.IceHockeyLeagueTest.LeagueManagerTest.TestLeagueManagerFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerStatsTest {

    @Before
    public void setup() {
        LeagueManagerFactory.setFactory(new TestLeagueManagerFactory());
    }

    @Test
    public void ConstructorTest() {
        IPlayerStats playerStats = LeagueManagerFactory.getFactory().getPlayerStats();
        Assert.assertEquals("", playerStats.getPosition());
        Assert.assertEquals(0, playerStats.getChecking());
        Assert.assertEquals(0, playerStats.getSaving());
        Assert.assertEquals(0, playerStats.getShooting());
        Assert.assertEquals(0, playerStats.getSkating());
        Assert.assertEquals(0, playerStats.getStrength(), 0.0);
    }

    @Test
    public void getPositionTest() {
        IPlayerStats playerStats = LeagueManagerFactory.getFactory().getPlayerStats();
        playerStats.setPosition("defense");
        Assert.assertTrue(playerStats.getPosition().equalsIgnoreCase("defense"));
    }

    @Test
    public void setPositionTest() {
        IPlayerStats playerStats = LeagueManagerFactory.getFactory().getPlayerStats();
        playerStats.setPosition("goalie");
        Assert.assertTrue(playerStats.getPosition().equalsIgnoreCase("goalie"));
    }

    @Test
    public void getSkatingTest() {
        IPlayerStats playerStats = LeagueManagerFactory.getFactory().getPlayerStats();
        playerStats.setSkating(12);
        Assert.assertEquals(playerStats.getSkating(), 12);
    }

    @Test
    public void setSkatingTest() {
        IPlayerStats playerStats = LeagueManagerFactory.getFactory().getPlayerStats();
        playerStats.setSkating(1);
        Assert.assertEquals(playerStats.getSkating(), 1);

        playerStats.setSkating(-11);
        Assert.assertEquals(playerStats.getSkating(), 1);
    }

    @Test
    public void getShootingTest() {
        IPlayerStats playerStats = LeagueManagerFactory.getFactory().getPlayerStats();
        playerStats.setShooting(20);
        Assert.assertEquals(playerStats.getShooting(), 20);
    }

    @Test
    public void setShootingTest() {
        IPlayerStats playerStats = LeagueManagerFactory.getFactory().getPlayerStats();
        playerStats.setShooting(11);
        Assert.assertEquals(playerStats.getShooting(), 11);

        playerStats.setShooting(0);
        Assert.assertEquals(playerStats.getShooting(), 11);
    }

    @Test
    public void getCheckingTest() {
        IPlayerStats playerStats = LeagueManagerFactory.getFactory().getPlayerStats();
        playerStats.setChecking(13);
        Assert.assertEquals(playerStats.getChecking(), 13);
    }

    @Test
    public void setCheckingTest() {
        IPlayerStats playerStats = LeagueManagerFactory.getFactory().getPlayerStats();
        playerStats.setChecking(9);
        Assert.assertEquals(playerStats.getChecking(), 9);

        playerStats.setChecking(30);
        Assert.assertEquals(playerStats.getChecking(), 9);
    }

    @Test
    public void getSavingTest() {
        IPlayerStats playerStats = LeagueManagerFactory.getFactory().getPlayerStats();
        playerStats.setSaving(18);
        Assert.assertEquals(playerStats.getSaving(), 18);
    }

    @Test
    public void setSavingTest() {
        IPlayerStats playerStats = LeagueManagerFactory.getFactory().getPlayerStats();
        playerStats.setSaving(2);
        Assert.assertEquals(playerStats.getSaving(), 2);

        playerStats.setSaving(21);
        Assert.assertEquals(playerStats.getSaving(), 2);
    }

    @Test
    public void setStrengthTest() {
        IPlayerStats playerStats = LeagueManagerFactory.getFactory().getPlayerStats();
        playerStats.setStrength(80);
        Assert.assertEquals(playerStats.getStrength(), 80, 0.0);
    }

    @Test
    public void getStrengthTest() {
        IPlayerStats playerStats = LeagueManagerFactory.getFactory().getPlayerStats();
        playerStats.setStrength(230);
        Assert.assertEquals(playerStats.getStrength(), 230, 0.0);
    }

    @Test
    public void calculateStrengthTest() {
        IPlayerStats playerStats = LeagueManagerFactory.getFactory().getPlayerStats();
        playerStats.setSkating(10);
        playerStats.setShooting(4);
        playerStats.setChecking(10);
        playerStats.setSaving(18);

        playerStats.setPosition("goalie");
        Assert.assertEquals(playerStats.calculateStrength(), 28.0, 0.0);

        playerStats.setPosition("defense");
        Assert.assertEquals(playerStats.calculateStrength(), 22.0, 0.0);

        playerStats.setPosition("forward");
        Assert.assertEquals(playerStats.calculateStrength(), 19.0, 0.0);

        playerStats.setPosition("invalidPosition");
        Assert.assertEquals(playerStats.calculateStrength(), 0.0, 0.0);
    }
}
