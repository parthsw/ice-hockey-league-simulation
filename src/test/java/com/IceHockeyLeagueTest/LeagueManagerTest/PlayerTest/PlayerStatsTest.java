package com.IceHockeyLeagueTest.LeagueManagerTest.PlayerTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerStats;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class PlayerStatsTest {
    private static ILeagueManagerFactory leagueManagerFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
    }
    @Test
    public void ConstructorTest() {
        IPlayerStats playerStats = leagueManagerFactory.createPlayerStats();
        Assert.assertEquals("", playerStats.getPosition());
        Assert.assertEquals(0, playerStats.getChecking());
        Assert.assertEquals(0, playerStats.getSaving());
        Assert.assertEquals(0, playerStats.getShooting());
        Assert.assertEquals(0, playerStats.getSkating());
        Assert.assertEquals(0f, playerStats.getStrength(), 0.0);
    }

    @Test
    public void getPositionTest() {
        IPlayerStats playerStats = leagueManagerFactory.createPlayerStats();
        playerStats.setPosition("defense");
        Assert.assertTrue(playerStats.getPosition().equalsIgnoreCase("defense"));
    }

    @Test
    public void setPositionTest() {
        IPlayerStats playerStats = leagueManagerFactory.createPlayerStats();
        playerStats.setPosition("goalie");
        Assert.assertTrue(playerStats.getPosition().equalsIgnoreCase("goalie"));
    }

    @Test
    public void getSkatingTest() {
        IPlayerStats playerStats = leagueManagerFactory.createPlayerStats();
        playerStats.setSkating(12);
        Assert.assertEquals(playerStats.getSkating(), 12);
    }

    @Test
    public void setSkatingTest() {
        IPlayerStats playerStats = leagueManagerFactory.createPlayerStats();
        playerStats.setSkating(1);
        Assert.assertEquals(playerStats.getSkating(), 1);

        playerStats.setSkating(-11);
        Assert.assertEquals(playerStats.getSkating(), 1);
    }

    @Test
    public void getShootingTest() {
        IPlayerStats playerStats = leagueManagerFactory.createPlayerStats();
        playerStats.setShooting(20);
        Assert.assertEquals(playerStats.getShooting(), 20);
    }

    @Test
    public void setShootingTest() {
        IPlayerStats playerStats = leagueManagerFactory.createPlayerStats();
        playerStats.setShooting(11);
        Assert.assertEquals(playerStats.getShooting(), 11);

        playerStats.setShooting(0);
        Assert.assertEquals(playerStats.getShooting(), 11);
    }

    @Test
    public void getCheckingTest() {
        IPlayerStats playerStats = leagueManagerFactory.createPlayerStats();
        playerStats.setChecking(13);
        Assert.assertEquals(playerStats.getChecking(), 13);
    }

    @Test
    public void setCheckingTest() {
        IPlayerStats playerStats = leagueManagerFactory.createPlayerStats();
        playerStats.setChecking(9);
        Assert.assertEquals(playerStats.getChecking(), 9);

        playerStats.setChecking(30);
        Assert.assertEquals(playerStats.getChecking(), 9);
    }

    @Test
    public void getSavingTest() {
        IPlayerStats playerStats = leagueManagerFactory.createPlayerStats();
        playerStats.setSaving(18);
        Assert.assertEquals(playerStats.getSaving(), 18);
    }

    @Test
    public void setSavingTest() {
        IPlayerStats playerStats = leagueManagerFactory.createPlayerStats();
        playerStats.setSaving(2);
        Assert.assertEquals(playerStats.getSaving(), 2);

        playerStats.setSaving(21);
        Assert.assertEquals(playerStats.getSaving(), 2);
    }

    @Test
    public void setStrengthTest() {
        IPlayerStats playerStats = leagueManagerFactory.createPlayerStats();
        playerStats.setStrength(80.8f);
        Assert.assertEquals(80.8f, playerStats.getStrength(), 0.0);
    }

    @Test
    public void getStrengthTest() {
        IPlayerStats playerStats = leagueManagerFactory.createPlayerStats();
        playerStats.setStrength(230.1f);
        Assert.assertEquals(230.1f, playerStats.getStrength(),0.0);
    }

    @Test
    public void calculateStrengthTest() {
        IPlayerStats playerStats = leagueManagerFactory.createPlayerStats();
        playerStats.setSkating(11);
        playerStats.setShooting(3);
        playerStats.setChecking(15);
        playerStats.setSaving(19);

        playerStats.setPosition("goalie");
        Assert.assertEquals(30.0f, playerStats.calculateStrength(), 0.0);

        playerStats.setPosition("defense");
        Assert.assertEquals(27.5f, playerStats.calculateStrength(), 0.0);

        playerStats.setPosition("forward");
        Assert.assertEquals(21.5f, playerStats.calculateStrength(), 0.0);

        playerStats.setPosition("invalidPosition");
        Assert.assertEquals(playerStats.calculateStrength(), 0.0, 0.0);
    }
}
