package com.IceHockeyLeagueTest.LeagueManagerTest.GameSimulatorTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.GameSimulator.IGameSimulation;
import com.IceHockeyLeague.LeagueManager.GameSimulator.IGameSimulationConfig;
import com.IceHockeyLeague.LeagueManager.GameSimulator.IGameStats;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class GameSimulationTest {
    private static ILeagueManagerFactory leagueManagerFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
    }

    @Test
    public void getGameStatsTest() {
        MockTeamGenerator mockTeamGenerator = new MockTeamGenerator();
        ITeam teamA = mockTeamGenerator.getTeamWithRandomPlayerStats();
        ITeam teamB = mockTeamGenerator.getTeamWithRandomPlayerStats();
        IGameSimulationConfig gameSimulationConfig = leagueManagerFactory.createGameSimulationConfig();
        IGameStats gameStats = leagueManagerFactory.createGameStats();

        IGameSimulation gameSimulation = leagueManagerFactory.createGameSimulation(teamA, teamB, gameSimulationConfig);
        gameSimulation.setGameStats(gameStats);

        Assert.assertEquals(gameSimulation.getGameStats(), gameStats);
    }

    @Test
    public void simulateGame1Test() {
        MockTeamGenerator mockTeamGenerator = new MockTeamGenerator();
        ITeam teamA = mockTeamGenerator.getTeamWithRandomPlayerStats();
        ITeam teamB = mockTeamGenerator.getTeamWithRandomPlayerStats();
        IGameSimulationConfig gameSimulationConfig = leagueManagerFactory.createGameSimulationConfig();

        IGameSimulation gameSimulation = leagueManagerFactory.createGameSimulation(teamA, teamB, gameSimulationConfig);
        gameSimulation.simulateGame();

        Assert.assertTrue(gameSimulation.getGameStats().getTeam1Goals() >= 0);
        Assert.assertTrue(gameSimulation.getGameStats().getTeam1Penalties() >= 0);
        Assert.assertTrue(gameSimulation.getGameStats().getTeam1Shots() > 0);
        Assert.assertTrue(gameSimulation.getGameStats().getTeam1Saves() > 0);
        Assert.assertTrue(gameSimulation.getGameStats().getTeam2Goals() >= 0);
        Assert.assertTrue(gameSimulation.getGameStats().getTeam2Penalties() >= 0);
        Assert.assertTrue(gameSimulation.getGameStats().getTeam2Shots() > 0);
        Assert.assertTrue(gameSimulation.getGameStats().getTeam2Saves() > 0);
    }

    @Test
    public void simulateGame2Test() {
        MockTeamGenerator mockTeamGenerator = new MockTeamGenerator();
        ITeam teamA = mockTeamGenerator.getTeamWithAllStatsAsOne();
        ITeam teamB = mockTeamGenerator.getTeamWithAllStatsAsTwenty();
        IGameSimulationConfig gameSimulationConfig = leagueManagerFactory.createGameSimulationConfig();

        IGameSimulation gameSimulation = leagueManagerFactory.createGameSimulation(teamA, teamB, gameSimulationConfig);
        gameSimulation.simulateGame();

        Assert.assertTrue(gameSimulation.getGameStats().getTeam1Goals() >= 0);
        Assert.assertTrue(gameSimulation.getGameStats().getTeam1Penalties() >= 0);
        Assert.assertTrue(gameSimulation.getGameStats().getTeam1Shots() > 0);
        Assert.assertTrue(gameSimulation.getGameStats().getTeam1Saves() > 0);
        Assert.assertTrue(gameSimulation.getGameStats().getTeam2Goals() >= 0);
        Assert.assertTrue(gameSimulation.getGameStats().getTeam2Penalties() >= 0);
        Assert.assertTrue(gameSimulation.getGameStats().getTeam2Shots() > 0);
        Assert.assertTrue(gameSimulation.getGameStats().getTeam2Saves() > 0);
    }
}
