package com.IceHockeyLeagueTest.LeagueManagerTest.GameSimulatorTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.GameSimulator.IGameSimulationConfig;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class GameSimulationConfigTest {
    private static ILeagueManagerFactory leagueManagerFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
    }

    @Test
    public void constructorTest() {
        IGameSimulationConfig gameSimulationConfig = leagueManagerFactory.createGameSimulationConfig();
        Assert.assertTrue(gameSimulationConfig.getAverageGoalsPerTeam() > 0);
        Assert.assertTrue(gameSimulationConfig.getAveragePenaltiesPerTeam() > 0);
        Assert.assertTrue(gameSimulationConfig.getAverageShotsPerTeam() > 0);
        Assert.assertTrue(gameSimulationConfig.getAverageSavesPerTeam() > 0);
    }

    @Test
    public void getAverageGoalsPerTeamTest() {
        IGameSimulationConfig gameSimulationConfig = leagueManagerFactory.createGameSimulationConfig();
        gameSimulationConfig.setAverageGoalsPerTeam(10);
        Assert.assertEquals(gameSimulationConfig.getAverageGoalsPerTeam(), 10);
    }

    @Test
    public void getAveragePenaltiesPerTeamTest() {
        IGameSimulationConfig gameSimulationConfig = leagueManagerFactory.createGameSimulationConfig();
        gameSimulationConfig.setAveragePenaltiesPerTeam(2);
        Assert.assertEquals(gameSimulationConfig.getAveragePenaltiesPerTeam(), 2);
    }

    @Test
    public void getAverageShotsPerTeamTest() {
        IGameSimulationConfig gameSimulationConfig = leagueManagerFactory.createGameSimulationConfig();
        gameSimulationConfig.setAverageShotsPerTeam(100);
        Assert.assertEquals(gameSimulationConfig.getAverageShotsPerTeam(), 100);
    }

    @Test
    public void getAverageSavesPerTeamTest() {
        IGameSimulationConfig gameSimulationConfig = leagueManagerFactory.createGameSimulationConfig();
        gameSimulationConfig.setAverageSavesPerTeam(49);
        Assert.assertEquals(gameSimulationConfig.getAverageSavesPerTeam(), 49);
    }
}
