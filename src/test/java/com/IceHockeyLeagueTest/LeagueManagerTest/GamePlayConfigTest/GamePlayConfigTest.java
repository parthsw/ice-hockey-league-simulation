package com.IceHockeyLeagueTest.LeagueManagerTest.GamePlayConfigTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.Database.IDatabaseFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class GamePlayConfigTest {
    private static ILeagueManagerFactory leagueManagerFactory;
    private static IDatabaseFactory databaseFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
        databaseFactory = appFactory.createDatabaseFactory();
    }

    @Test
    public void ConstructorTest() {
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        Assert.assertEquals(-1, gamePlayConfig.getGamePlayConfigID());
        Assert.assertEquals(-1, gamePlayConfig.getLeagueID());
    }

    @Test
    public void getGamePlayConfigIDTest() {
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        gamePlayConfig.setGamePlayConfigID(7);
        Assert.assertEquals(7, gamePlayConfig.getGamePlayConfigID());
    }

    @Test
    public void setGamePlayConfigIDTest() {
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        gamePlayConfig.setGamePlayConfigID(12);
        Assert.assertEquals(12, gamePlayConfig.getGamePlayConfigID());
    }

    @Test
    public void getLeagueIDTest() {
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        gamePlayConfig.setLeagueID(12);
        Assert.assertEquals(12, gamePlayConfig.getLeagueID());
    }

    @Test
    public void setLeagueIDTest() {
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        gamePlayConfig.setLeagueID(23);
        Assert.assertEquals(23, gamePlayConfig.getLeagueID());
    }

    @Test
    public void setAgingConfigTest() {
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        gamePlayConfig.setAgingConfig(createAgingConfig());

        IAgingConfig actualAgeConfig = gamePlayConfig.getAgingConfig();
        Assert.assertEquals(50, actualAgeConfig.getMaximumAge());
    }

    @Test
    public void getAgingConfigTest() {
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        gamePlayConfig.setAgingConfig(createAgingConfig());

        IAgingConfig actualAgeConfig = gamePlayConfig.getAgingConfig();
        Assert.assertEquals(35, actualAgeConfig.getAverageRetirementAge());
    }

    @Test
    public void setGameResolverConfigTest() {
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        gamePlayConfig.setGameResolverConfig(createGameResolverConfig());

        IGameResolverConfig actualGameResolverConfig = gamePlayConfig.getGameResolverConfig();
        Assert.assertEquals(0.03f, actualGameResolverConfig.getRandomWinChance(), 0.0);
    }

    @Test
    public void getGameResolverConfigTest() {
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        gamePlayConfig.setGameResolverConfig(createGameResolverConfig());

        IGameResolverConfig actualGameResolverConfig = gamePlayConfig.getGameResolverConfig();
        Assert.assertEquals(0.03f, actualGameResolverConfig.getRandomWinChance(), 0.0);
    }

    @Test
    public void setInjuryConfigTest() {
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        gamePlayConfig.setInjuryConfig(createInjuryConfig());

        IInjuryConfig actualInjuryConfig = gamePlayConfig.getInjuryConfig();
        Assert.assertEquals(230, actualInjuryConfig.getInjuryDaysHigh());
    }

    @Test
    public void getInjuryConfigTest() {
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        gamePlayConfig.setInjuryConfig(createInjuryConfig());

        IInjuryConfig actualInjuryConfig = gamePlayConfig.getInjuryConfig();
        Assert.assertEquals(3, actualInjuryConfig.getInjuryDaysLow());
    }

    @Test
    public void setTrainingConfigTest() {
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        gamePlayConfig.setTrainingConfig(createTrainingConfig());

        ITrainingConfig actualTrainingConfig = gamePlayConfig.getTrainingConfig();
        Assert.assertEquals(111, actualTrainingConfig.getDaysUntilStatIncreaseCheck());
    }

    @Test
    public void getTrainingConfigTest() {
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        gamePlayConfig.setTrainingConfig(createTrainingConfig());

        ITrainingConfig actualTrainingConfig = gamePlayConfig.getTrainingConfig();
        Assert.assertEquals(111, actualTrainingConfig.getDaysUntilStatIncreaseCheck());
    }

    @Test
    public void setTradingConfigTest() {
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        gamePlayConfig.setTradingConfig(createTradingConfig());

        ITradingConfig actualTradingConfig = gamePlayConfig.getTradingConfig();
        Assert.assertEquals(6, actualTradingConfig.getLossPoint());
    }

    @Test
    public void getTradingConfigTest() {
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        gamePlayConfig.setTradingConfig(createTradingConfig());

        ITradingConfig actualTradingConfig = gamePlayConfig.getTradingConfig();
        Assert.assertEquals(23, actualTradingConfig.getMaxPlayersPerTrade());
    }

    @Test
    public void saveGamePlayConfigTest() {
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        gamePlayConfig.setLeagueID(2);
        gamePlayConfig.saveGamePlayConfig(databaseFactory.createGamePlayConfigPersistence());
        Assert.assertEquals(2, gamePlayConfig.getGamePlayConfigID());
        Assert.assertEquals(2, gamePlayConfig.getLeagueID());
    }

    @Test
    public void loadGamePlayConfigTest() {
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        gamePlayConfig.setLeagueID(1);
        gamePlayConfig.loadGamePlayConfig(databaseFactory.createGamePlayConfigPersistence(), gamePlayConfig);
        ITrainingConfig trainingConfig = gamePlayConfig.getTrainingConfig();

        Assert.assertEquals(1, gamePlayConfig.getGamePlayConfigID());
        Assert.assertEquals(1, gamePlayConfig.getLeagueID());
        Assert.assertEquals(100, trainingConfig.getDaysUntilStatIncreaseCheck());
    }

    private IAgingConfig createAgingConfig() {
        IAgingConfig agingConfig = leagueManagerFactory.createAgingConfig();
        agingConfig.setMaximumAge(50);
        agingConfig.setAverageRetirementAge(35);
        agingConfig.setStatDecayChance(0.05f);
        return agingConfig;
    }

    private IGameResolverConfig createGameResolverConfig() {
        IGameResolverConfig resolverConfig = leagueManagerFactory.createGameResolverConfig();
        resolverConfig.setRandomWinChance(0.03f);
        return resolverConfig;
    }

    private IInjuryConfig createInjuryConfig() {
        IInjuryConfig injuryConfig = leagueManagerFactory.createInjuryConfig();
        injuryConfig.setInjuryDaysHigh(230);
        injuryConfig.setInjuryDaysLow(3);
        injuryConfig.setRandomInjuryChance(0.03f);
        return injuryConfig;
    }

    private ITradingConfig createTradingConfig() {
        ITradingConfig tradingConfig = leagueManagerFactory.createTradingConfig();
        tradingConfig.setRandomAcceptanceChance(0.05f);
        tradingConfig.setMaxPlayersPerTrade(23);
        tradingConfig.setLossPoint(6);
        tradingConfig.setRandomTradeOfferChance(0.56f);
        return  tradingConfig;
    }

    private ITrainingConfig createTrainingConfig() {
        ITrainingConfig trainingConfig = leagueManagerFactory.createTrainingConfig();
        trainingConfig.setDaysUntilStatIncreaseCheck(111);
        return trainingConfig;
    }

}
