package com.IceHockeyLeagueTest.LeagueManagerTest.GameSimulatorTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.GameSimulator.IGameStats;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class GameStatsTest {
    private static ILeagueManagerFactory leagueManagerFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
    }

    @Test
    public void getTeam1GoalsTest() {
        IGameStats gameStats = leagueManagerFactory.createGameStats();
        gameStats.setTeam1Goals(5);
        Assert.assertEquals(gameStats.getTeam1Goals(), 5);
    }

    @Test
    public void getTeam1PenaltiesTest() {
        IGameStats gameStats = leagueManagerFactory.createGameStats();
        gameStats.setTeam1Penalties(2);
        Assert.assertEquals(gameStats.getTeam1Penalties(), 2);
    }

    @Test
    public void getTeam1ShotsTest() {
        IGameStats gameStats = leagueManagerFactory.createGameStats();
        gameStats.setTeam1Shots(25);
        Assert.assertEquals(gameStats.getTeam1Shots(), 25);
    }

    @Test
    public void getTeam1SavesTest() {
        IGameStats gameStats = leagueManagerFactory.createGameStats();
        gameStats.setTeam1Saves(34);
        Assert.assertEquals(gameStats.getTeam1Saves(), 34);
    }

    @Test
    public void getTeam2GoalsTest() {
        IGameStats gameStats = leagueManagerFactory.createGameStats();
        gameStats.setTeam2Goals(6);
        Assert.assertEquals(gameStats.getTeam2Goals(), 6);
    }

    @Test
    public void getTeam2PenaltiesTest() {
        IGameStats gameStats = leagueManagerFactory.createGameStats();
        gameStats.setTeam2Penalties(1);
        Assert.assertEquals(gameStats.getTeam2Penalties(), 1);
    }

    @Test
    public void getTeam2ShotsTest() {
        IGameStats gameStats = leagueManagerFactory.createGameStats();
        gameStats.setTeam2Shots(18);
        Assert.assertEquals(gameStats.getTeam2Shots(), 18);
    }

    @Test
    public void getTeam2SavesTest() {
        IGameStats gameStats = leagueManagerFactory.createGameStats();
        gameStats.setTeam2Saves(26);
        Assert.assertEquals(gameStats.getTeam2Saves(), 26);
    }
}
