package com.IceHockeyLeagueTest.LeagueManagerTest.GamePlayConfigTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.ITradingConfig;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TradingConfigTest {
    private static ILeagueManagerFactory leagueManagerFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
    }

    @Test
    public void setLossPointTest() {
        ITradingConfig tradingConfig = leagueManagerFactory.createTradingConfig();
        tradingConfig.setLossPoint(7);
        Assert.assertEquals(7, tradingConfig.getLossPoint());
    }

    @Test
    public void getLossPointTest() {
        ITradingConfig tradingConfig = leagueManagerFactory.createTradingConfig();
        tradingConfig.setLossPoint(11);
        Assert.assertEquals(11, tradingConfig.getLossPoint());
    }

    @Test
    public void setRandomTradeOfferChanceTest() {
        ITradingConfig tradingConfig = leagueManagerFactory.createTradingConfig();
        tradingConfig.setRandomTradeOfferChance(7.2f);
        Assert.assertEquals(7.2f, tradingConfig.getRandomTradeOfferChance(), 0.0);
    }

    @Test
    public void getRandomTradeOfferChanceTest() {
        ITradingConfig tradingConfig = leagueManagerFactory.createTradingConfig();
        tradingConfig.setRandomTradeOfferChance(0.021f);
        Assert.assertEquals(0.021f, tradingConfig.getRandomTradeOfferChance(), 0.0);
    }

    @Test
    public void setMaxPlayersPerTradeTest() {
        ITradingConfig tradingConfig = leagueManagerFactory.createTradingConfig();
        tradingConfig.setMaxPlayersPerTrade(18);
        Assert.assertEquals(18, tradingConfig.getMaxPlayersPerTrade());
    }

    @Test
    public void getMaxPlayersPerTradeTest() {
        ITradingConfig tradingConfig = leagueManagerFactory.createTradingConfig();
        tradingConfig.setMaxPlayersPerTrade(14);
        Assert.assertEquals(14, tradingConfig.getMaxPlayersPerTrade());
    }

    @Test
    public void setRandomAcceptanceChanceTest() {
        ITradingConfig tradingConfig = leagueManagerFactory.createTradingConfig();
        tradingConfig.setRandomAcceptanceChance(0.03f);
        Assert.assertEquals(0.03f, tradingConfig.getRandomAcceptanceChance(), 0.0);
    }

    @Test
    public void getRandomAcceptanceChanceTest() {
        ITradingConfig tradingConfig = leagueManagerFactory.createTradingConfig();
        tradingConfig.setRandomAcceptanceChance(0.23f);
        Assert.assertEquals(0.23f, tradingConfig.getRandomAcceptanceChance(), 0.0);
    }
}
