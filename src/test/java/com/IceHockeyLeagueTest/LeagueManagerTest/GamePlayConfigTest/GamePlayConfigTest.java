package com.IceHockeyLeagueTest.LeagueManagerTest.GamePlayConfigTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.*;
import com.PersistenceTest.PersistenceFactoryTest;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class GamePlayConfigTest {
    private static ILeagueManagerFactory leagueManagerFactory;
    private static PersistenceFactoryTest persistenceFactory;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
        persistenceFactory = AppFactoryTest.createPersistenceFactoryTest();
    }

    @Test
    public void ConstructorTest() {
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        Assert.assertEquals(-1, gamePlayConfig.getGamePlayConfigId());
        Assert.assertEquals(-1, gamePlayConfig.getLeagueId());
    }

    @Test
    public void getGamePlayConfigIdTest() {
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        gamePlayConfig.setGamePlayConfigId(7);
        Assert.assertEquals(7, gamePlayConfig.getGamePlayConfigId());
    }

    @Test
    public void setGamePlayConfigIdTest() {
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        gamePlayConfig.setGamePlayConfigId(12);
        Assert.assertEquals(12, gamePlayConfig.getGamePlayConfigId());
    }

    @Test
    public void getLeagueIdTest() {
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        gamePlayConfig.setLeagueId(12);
        Assert.assertEquals(12, gamePlayConfig.getLeagueId());
    }

    @Test
    public void setLeagueIdTest() {
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        gamePlayConfig.setLeagueId(23);
        Assert.assertEquals(23, gamePlayConfig.getLeagueId());
    }

    @Test
    public void setAgingConfigTest() {
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        IAgingConfig actualAgeConfig;
        gamePlayConfig.setAgingConfig(createAgingConfig());

        actualAgeConfig = gamePlayConfig.getAgingConfig();
        Assert.assertEquals(50, actualAgeConfig.getMaximumAge());
    }

    @Test
    public void setAgingConfigInvalidTest() {
        thrown.expect(IllegalArgumentException.class);
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        gamePlayConfig.setAgingConfig(null);
    }

    @Test
    public void getAgingConfigTest() {
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        IAgingConfig actualAgeConfig;
        gamePlayConfig.setAgingConfig(createAgingConfig());

        actualAgeConfig = gamePlayConfig.getAgingConfig();
        Assert.assertEquals(35, actualAgeConfig.getAverageRetirementAge());
    }

    @Test
    public void setGameResolverConfigTest() {
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        IGameResolverConfig actualGameResolverConfig;
        gamePlayConfig.setGameResolverConfig(createGameResolverConfig());

        actualGameResolverConfig = gamePlayConfig.getGameResolverConfig();
        Assert.assertEquals(0.03f, actualGameResolverConfig.getRandomWinChance(), 0.0);
    }

    @Test
    public void setGameResolverConfigInvalidTest() {
        thrown.expect(IllegalArgumentException.class);
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        gamePlayConfig.setGameResolverConfig(null);
    }

    @Test
    public void getGameResolverConfigTest() {
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        IGameResolverConfig actualGameResolverConfig;
        gamePlayConfig.setGameResolverConfig(createGameResolverConfig());

        actualGameResolverConfig = gamePlayConfig.getGameResolverConfig();
        Assert.assertEquals(0.03f, actualGameResolverConfig.getRandomWinChance(), 0.0);
    }

    @Test
    public void setInjuryConfigTest() {
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        IInjuryConfig actualInjuryConfig;
        gamePlayConfig.setInjuryConfig(createInjuryConfig());

        actualInjuryConfig = gamePlayConfig.getInjuryConfig();
        Assert.assertEquals(230, actualInjuryConfig.getInjuryDaysHigh());
    }

    @Test
    public void setInjuryConfigInvalidTest() {
        thrown.expect(IllegalArgumentException.class);
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        gamePlayConfig.setInjuryConfig(null);
    }

    @Test
    public void getInjuryConfigTest() {
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        IInjuryConfig actualInjuryConfig;
        gamePlayConfig.setInjuryConfig(createInjuryConfig());

        actualInjuryConfig = gamePlayConfig.getInjuryConfig();
        Assert.assertEquals(3, actualInjuryConfig.getInjuryDaysLow());
    }

    @Test
    public void setTrainingConfigTest() {
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        ITrainingConfig actualTrainingConfig;
        gamePlayConfig.setTrainingConfig(createTrainingConfig());

        actualTrainingConfig = gamePlayConfig.getTrainingConfig();
        Assert.assertEquals(111, actualTrainingConfig.getDaysUntilStatIncreaseCheck());
    }

    @Test
    public void setTrainingConfigInvalidTest() {
        thrown.expect(IllegalArgumentException.class);
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        gamePlayConfig.setTrainingConfig(null);
    }

    @Test
    public void getTrainingConfigTest() {
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        ITrainingConfig actualTrainingConfig;
        gamePlayConfig.setTrainingConfig(createTrainingConfig());

        actualTrainingConfig = gamePlayConfig.getTrainingConfig();
        Assert.assertEquals(111, actualTrainingConfig.getDaysUntilStatIncreaseCheck());
    }

    @Test
    public void setTradingConfigTest() {
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        ITradingConfig actualTradingConfig;
        gamePlayConfig.setTradingConfig(createTradingConfig());

        actualTradingConfig = gamePlayConfig.getTradingConfig();
        Assert.assertEquals(6, actualTradingConfig.getLossPoint());
    }

    @Test
    public void setTradingConfigInvalidTest() {
        thrown.expect(IllegalArgumentException.class);
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        gamePlayConfig.setTradingConfig(null);
    }

    @Test
    public void getTradingConfigTest() {
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
        ITradingConfig actualTradingConfig;
        gamePlayConfig.setTradingConfig(createTradingConfig());

        actualTradingConfig = gamePlayConfig.getTradingConfig();
        Assert.assertEquals(23, actualTradingConfig.getMaxPlayersPerTrade());
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
        return tradingConfig;
    }

    private ITrainingConfig createTrainingConfig() {
        ITrainingConfig trainingConfig = leagueManagerFactory.createTrainingConfig();
        trainingConfig.setDaysUntilStatIncreaseCheck(111);
        return trainingConfig;
    }

}
