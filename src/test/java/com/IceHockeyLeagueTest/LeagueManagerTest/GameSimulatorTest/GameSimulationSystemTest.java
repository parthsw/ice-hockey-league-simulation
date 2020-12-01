package com.IceHockeyLeagueTest.LeagueManagerTest.GameSimulatorTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.GameSimulator.IGameSimulationSystem;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class GameSimulationSystemTest {
    private static ILeagueManagerFactory leagueManagerFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
    }

    @Test
    public void ConstructorTest() {
        IGameSimulationSystem gameSimulationSystem = leagueManagerFactory.createGameSimulationSystem();
        Assert.assertEquals(gameSimulationSystem.getTotalGoals(), 0);
        Assert.assertEquals(gameSimulationSystem.getTotalPenalties(), 0);
        Assert.assertEquals(gameSimulationSystem.getTotalShots(), 0);
        Assert.assertEquals(gameSimulationSystem.getTotalSaves(), 0);
        Assert.assertEquals(gameSimulationSystem.getNumberOfGamesPlayed(), 0);
    }

    @Test
    public void getTotalGoalsTest() {
        IGameSimulationSystem gameSimulationSystem = leagueManagerFactory.createGameSimulationSystem();
        gameSimulationSystem.setTotalGoals(5);
        Assert.assertEquals(gameSimulationSystem.getTotalGoals(), 5);
    }

    @Test
    public void getTotalPenaltiesTest() {
        IGameSimulationSystem gameSimulationSystem = leagueManagerFactory.createGameSimulationSystem();
        gameSimulationSystem.setTotalPenalties(50);
        Assert.assertEquals(gameSimulationSystem.getTotalPenalties(), 50);
    }

    @Test
    public void getTotalShotsTest() {
        IGameSimulationSystem gameSimulationSystem = leagueManagerFactory.createGameSimulationSystem();
        gameSimulationSystem.setTotalShots(26);
        Assert.assertEquals(gameSimulationSystem.getTotalShots(), 26);
    }

    @Test
    public void getTotalSavesTest() {
        IGameSimulationSystem gameSimulationSystem = leagueManagerFactory.createGameSimulationSystem();
        gameSimulationSystem.setTotalSaves(38);
        Assert.assertEquals(gameSimulationSystem.getTotalSaves(), 38);
    }

    @Test
    public void getNumberOfGamesPlayedTest() {
        IGameSimulationSystem gameSimulationSystem = leagueManagerFactory.createGameSimulationSystem();
        gameSimulationSystem.setNumberOfGamesPlayed(1271);
        Assert.assertEquals(gameSimulationSystem.getNumberOfGamesPlayed(), 1271);
    }

    @Test
    public void resetAllStatsTest() {
        IGameSimulationSystem gameSimulationSystem = leagueManagerFactory.createGameSimulationSystem();
        gameSimulationSystem.setTotalGoals(5);
        gameSimulationSystem.setTotalPenalties(50);
        gameSimulationSystem.setTotalShots(26);
        gameSimulationSystem.setTotalSaves(38);
        gameSimulationSystem.setNumberOfGamesPlayed(1271);

        gameSimulationSystem.resetAllStats();

        Assert.assertEquals(gameSimulationSystem.getTotalGoals(), 0);
        Assert.assertEquals(gameSimulationSystem.getTotalPenalties(), 0);
        Assert.assertEquals(gameSimulationSystem.getTotalShots(), 0);
        Assert.assertEquals(gameSimulationSystem.getTotalSaves(), 0);
        Assert.assertEquals(gameSimulationSystem.getNumberOfGamesPlayed(), 0);
    }

    @Test
    public void simulateGameAndGetWinner1Test() {
        MockTeamGenerator mockTeamGenerator = new MockTeamGenerator();
        ITeam teamA = mockTeamGenerator.getTeamWithAllStatsAsOne();
        ITeam teamB = mockTeamGenerator.getTeamWithAllStatsAsTwenty();
        IGameSimulationSystem gameSimulationSystem = leagueManagerFactory.createGameSimulationSystem();

        ITeam winner = gameSimulationSystem.simulateGameAndGetWinner(teamA, teamB);

        Assert.assertEquals(winner, teamB);
    }

    @Test
    public void simulateGameAndGetWinner2Test() {
        MockTeamGenerator mockTeamGenerator = new MockTeamGenerator();
        ITeam teamA = mockTeamGenerator.getTeamWithAllStatsAsOne();
        ITeam teamB = mockTeamGenerator.getTeamWithAllStatsAsOne();
        IGameSimulationSystem gameSimulationSystem = leagueManagerFactory.createGameSimulationSystem();

        ITeam winner = gameSimulationSystem.simulateGameAndGetWinner(teamA, teamB);

        Assert.assertNull(winner);
    }

    @Test
    public void simulateGameAndGetWinner3Test() {
        MockTeamGenerator mockTeamGenerator = new MockTeamGenerator();
        ITeam teamA = mockTeamGenerator.getTeamWithAllStatsAsTwenty();
        ITeam teamB = mockTeamGenerator.getTeamWithAllStatsAsOne();
        IGameSimulationSystem gameSimulationSystem = leagueManagerFactory.createGameSimulationSystem();

        ITeam winner = gameSimulationSystem.simulateGameAndGetWinner(teamA, teamB);

        Assert.assertEquals(winner, teamA);
    }
}
