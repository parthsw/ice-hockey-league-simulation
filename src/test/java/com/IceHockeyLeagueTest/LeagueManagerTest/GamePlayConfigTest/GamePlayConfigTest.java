package com.IceHockeyLeagueTest.LeagueManagerTest.GamePlayConfigTest;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.*;
import com.IceHockeyLeagueTest.LeagueManagerTest.TestLeagueManagerFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class GamePlayConfigTest {

    @BeforeClass
    public static void setup() {
        AbstractLeagueManagerFactory.setFactory(new TestLeagueManagerFactory());
    }

    @Test
    public void ConstructorTest() {
        IGamePlayConfig gamePlayConfig = AbstractLeagueManagerFactory.getFactory().getGamePlayConfig();
        Assert.assertEquals(-1, gamePlayConfig.getLeagueID());
    }

    @Test
    public void getLeagueIDTest() {
        IGamePlayConfig gamePlayConfig = AbstractLeagueManagerFactory.getFactory().getGamePlayConfig();
        gamePlayConfig.setLeagueID(12);
        Assert.assertEquals(12, gamePlayConfig.getLeagueID());
    }

    @Test
    public void setLeagueIDTest() {
        IGamePlayConfig gamePlayConfig = AbstractLeagueManagerFactory.getFactory().getGamePlayConfig();
        gamePlayConfig.setLeagueID(23);
        Assert.assertEquals(23, gamePlayConfig.getLeagueID());
    }

    @Test
    public void setAgingConfigTest() {
        IGamePlayConfig gamePlayConfig = AbstractLeagueManagerFactory.getFactory().getGamePlayConfig();
        gamePlayConfig.setAgingConfig(createAgingConfig());

        IAgingConfig actualAgeConfig = gamePlayConfig.getAgingConfig();
        Assert.assertEquals(50, actualAgeConfig.getMaximumAge());
    }

    @Test
    public void getAgingConfigTest() {
        IGamePlayConfig gamePlayConfig = AbstractLeagueManagerFactory.getFactory().getGamePlayConfig();
        gamePlayConfig.setAgingConfig(createAgingConfig());

        IAgingConfig actualAgeConfig = gamePlayConfig.getAgingConfig();
        Assert.assertEquals(35, actualAgeConfig.getAverageRetirementAge());
    }

    @Test
    public void setGameResolverConfigTest() {
        IGamePlayConfig gamePlayConfig = AbstractLeagueManagerFactory.getFactory().getGamePlayConfig();
        gamePlayConfig.setGameResolverConfig(createGameResolverConfig());

        IGameResolverConfig actualGameResolverConfig = gamePlayConfig.getGameResolverConfig();
        Assert.assertEquals(0.03, actualGameResolverConfig.getRandomWinChance(), 0.0);
    }

    @Test
    public void getGameResolverConfigTest() {
        IGamePlayConfig gamePlayConfig = AbstractLeagueManagerFactory.getFactory().getGamePlayConfig();
        gamePlayConfig.setGameResolverConfig(createGameResolverConfig());

        IGameResolverConfig actualGameResolverConfig = gamePlayConfig.getGameResolverConfig();
        Assert.assertEquals(0.03, actualGameResolverConfig.getRandomWinChance(), 0.0);
    }

    @Test
    public void setInjuryConfigTest() {
        IGamePlayConfig gamePlayConfig = AbstractLeagueManagerFactory.getFactory().getGamePlayConfig();
        gamePlayConfig.setInjuryConfig(createInjuryConfig());

        IInjuryConfig actualInjuryConfig = gamePlayConfig.getInjuryConfig();
        Assert.assertEquals(230, actualInjuryConfig.getInjuryDaysHigh());
    }

    @Test
    public void getInjuryConfigTest() {
        IGamePlayConfig gamePlayConfig = AbstractLeagueManagerFactory.getFactory().getGamePlayConfig();
        gamePlayConfig.setInjuryConfig(createInjuryConfig());

        IInjuryConfig actualInjuryConfig = gamePlayConfig.getInjuryConfig();
        Assert.assertEquals(3, actualInjuryConfig.getInjuryDaysLow());
    }

    @Test
    public void setTrainingConfigTest() {
        IGamePlayConfig gamePlayConfig = AbstractLeagueManagerFactory.getFactory().getGamePlayConfig();
        gamePlayConfig.setTrainingConfig(createTrainingConfig());

        ITrainingConfig actualTrainingConfig = gamePlayConfig.getTrainingConfig();
        Assert.assertEquals(111, actualTrainingConfig.getDaysUntilStatIncreaseCheck());
    }

    @Test
    public void getTrainingConfigTest() {
        IGamePlayConfig gamePlayConfig = AbstractLeagueManagerFactory.getFactory().getGamePlayConfig();
        gamePlayConfig.setTrainingConfig(createTrainingConfig());

        ITrainingConfig actualTrainingConfig = gamePlayConfig.getTrainingConfig();
        Assert.assertEquals(111, actualTrainingConfig.getDaysUntilStatIncreaseCheck());
    }

    @Test
    public void setTradingConfigTest() {
        IGamePlayConfig gamePlayConfig = AbstractLeagueManagerFactory.getFactory().getGamePlayConfig();
        gamePlayConfig.setTradingConfig(createTradingConfig());

        ITradingConfig actualTradingConfig = gamePlayConfig.getTradingConfig();
        Assert.assertEquals(6, actualTradingConfig.getLossPoint());
    }

    @Test
    public void getTradingConfigTest() {
        IGamePlayConfig gamePlayConfig = AbstractLeagueManagerFactory.getFactory().getGamePlayConfig();
        gamePlayConfig.setTradingConfig(createTradingConfig());

        ITradingConfig actualTradingConfig = gamePlayConfig.getTradingConfig();
        Assert.assertEquals(23, actualTradingConfig.getMaxPlayersPerTrade());
    }

    private IAgingConfig createAgingConfig() {
        IAgingConfig agingConfig = AbstractLeagueManagerFactory.getFactory().getAgingConfig();
        agingConfig.setMaximumAge(50);
        agingConfig.setAverageRetirementAge(35);
        return agingConfig;
    }

    private IGameResolverConfig createGameResolverConfig() {
        IGameResolverConfig resolverConfig = AbstractLeagueManagerFactory.getFactory().getGameResolverConfig();
        resolverConfig.setRandomWinChance(0.03);
        return resolverConfig;
    }

    private IInjuryConfig createInjuryConfig() {
        IInjuryConfig injuryConfig = AbstractLeagueManagerFactory.getFactory().getInjuryConfig();
        injuryConfig.setInjuryDaysHigh(230);
        injuryConfig.setInjuryDaysLow(3);
        injuryConfig.setRandomInjuryChance(0.03);
        return injuryConfig;
    }

    private ITradingConfig createTradingConfig() {
        ITradingConfig tradingConfig = AbstractLeagueManagerFactory.getFactory().getTradingConfig();
        tradingConfig.setRandomAcceptanceChance(0.05);
        tradingConfig.setMaxPlayersPerTrade(23);
        tradingConfig.setLossPoint(6);
        tradingConfig.setRandomTradeOfferChance(0.56);
        return  tradingConfig;
    }

    private ITrainingConfig createTrainingConfig() {
        ITrainingConfig trainingConfig = AbstractLeagueManagerFactory.getFactory().getTrainingConfig();
        trainingConfig.setDaysUntilStatIncreaseCheck(111);
        return trainingConfig;
    }
}
