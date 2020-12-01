package com.IceHockeyLeagueTest.LeagueManagerTest.PlayerTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IAgingConfig;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class PlayerStatsTest {
    private static final String INVALID_POSITION = "invalidPosition";

    private static ILeagueManagerFactory leagueManagerFactory;
    private static IRandomChance randomChanceMock;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
        randomChanceMock = Mockito.mock(RandomChance.class);
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
        playerStats.setPosition(PlayerPosition.DEFENSE.toString());
        Assert.assertTrue(playerStats.getPosition().equalsIgnoreCase(PlayerPosition.DEFENSE.toString()));
    }

    @Test
    public void setPositionTest() {
        IPlayerStats playerStats = leagueManagerFactory.createPlayerStats();
        playerStats.setPosition(PlayerPosition.GOALIE.toString());
        Assert.assertTrue(playerStats.getPosition().equalsIgnoreCase(PlayerPosition.GOALIE.toString()));
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
    }

    @Test
    public void setSkatingOutOfRangeTest() {
        IPlayerStats playerStats = leagueManagerFactory.createPlayerStats();
        playerStats.setSkating(-11);
        Assert.assertEquals(playerStats.getSkating(), 0);
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
    }

    @Test
    public void setShootingOutOfRangeTest() {
        IPlayerStats playerStats = leagueManagerFactory.createPlayerStats();
        playerStats.setShooting(34);
        Assert.assertEquals(playerStats.getShooting(), 0);
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
    }

    @Test
    public void setCheckingOutOfRangeTest() {
        IPlayerStats playerStats = leagueManagerFactory.createPlayerStats();
        playerStats.setChecking(-12);
        Assert.assertEquals(playerStats.getChecking(), 0);
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
    }

    @Test
    public void setSavingOutOfRangeTest() {
        IPlayerStats playerStats = leagueManagerFactory.createPlayerStats();
        playerStats.setSaving(21);
        Assert.assertEquals(playerStats.getSaving(), 0);
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
        Assert.assertEquals(230.1f, playerStats.getStrength(), 0.0);
    }

    @Test
    public void calculateStrengthGoalieTest() {
        IPlayerStats playerStats = createStats();
        playerStats.setPosition(PlayerPosition.GOALIE.toString());
        Assert.assertEquals(30.0f, playerStats.calculateStrength(), 0.0);
    }

    @Test
    public void calculateStrengthForwardTest() {
        IPlayerStats playerStats = createStats();
        playerStats.setPosition(PlayerPosition.FORWARD.toString());
        Assert.assertEquals(21.5f, playerStats.calculateStrength(), 0.0);
    }

    @Test
    public void calculateStrengthDefenseTest() {
        IPlayerStats playerStats = createStats();
        playerStats.setPosition(PlayerPosition.DEFENSE.toString());
        Assert.assertEquals(27.5f, playerStats.calculateStrength(), 0.0);
    }

    @Test
    public void calculateStrengthInvalidPositionTest() {
        IPlayerStats playerStats = createStats();
        playerStats.setPosition(INVALID_POSITION);
        Assert.assertEquals(playerStats.calculateStrength(), 0.0, 0.0);
    }

    @Test
    public void checkStatisticDecaySkatingTest() {
        IPlayerStats playerStats = createStats();
        IAgingConfig agingConfig = createAgingConfig();
        when(randomChanceMock.getRandomFloatNumber(0, 1)).thenReturn(0.01f);
        playerStats.performStatDecay(agingConfig, randomChanceMock);
        Assert.assertEquals(10, playerStats.getSkating());
    }

    @Test
    public void checkStatisticDecaySkatingNotDegradedTest() {
        IPlayerStats playerStats = createStats();
        IAgingConfig agingConfig = createAgingConfig();
        when(randomChanceMock.getRandomFloatNumber(0, 1)).thenReturn(0.1f);
        playerStats.performStatDecay(agingConfig, randomChanceMock);
        Assert.assertEquals(11, playerStats.getSkating());
    }

    @Test
    public void checkStatisticDecayShootingTest() {
        IPlayerStats playerStats = createStats();
        IAgingConfig agingConfig = createAgingConfig();
        when(randomChanceMock.getRandomFloatNumber(0, 1)).thenReturn(0.1f, 0.04f);
        playerStats.performStatDecay(agingConfig, randomChanceMock);
        Assert.assertEquals(2, playerStats.getShooting());
    }

    @Test
    public void checkStatisticDecayCheckingTest() {
        IPlayerStats playerStats = createStats();
        IAgingConfig agingConfig = createAgingConfig();
        when(randomChanceMock.getRandomFloatNumber(0, 1)).thenReturn(0.13f, 0.14f, 0.04f);
        playerStats.performStatDecay(agingConfig, randomChanceMock);
        Assert.assertEquals(14, playerStats.getChecking());
    }

    @Test
    public void checkStatisticDecaySavingTest() {
        IPlayerStats playerStats = createStats();
        IAgingConfig agingConfig = createAgingConfig();
        when(randomChanceMock.getRandomFloatNumber(0, 1)).thenReturn(0.13f, 0.14f, 0.54f, 0.01f);
        playerStats.performStatDecay(agingConfig, randomChanceMock);
        Assert.assertEquals(18, playerStats.getSaving());
    }

    private IPlayerStats createStats() {
        IPlayerStats playerStats = leagueManagerFactory.createPlayerStats();
        playerStats.setSkating(11);
        playerStats.setShooting(3);
        playerStats.setChecking(15);
        playerStats.setSaving(19);
        return playerStats;
    }

    private IAgingConfig createAgingConfig() {
        IAgingConfig agingConfig = leagueManagerFactory.createAgingConfig();
        agingConfig.setStatDecayChance(0.05f);
        return agingConfig;
    }

}
