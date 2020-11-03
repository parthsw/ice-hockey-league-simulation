package com.IceHockeyLeagueTest.LeagueManagerTest.GamePlayConfigTest;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.ITradingConfig;
import com.IceHockeyLeagueTest.LeagueManagerTest.TestLeagueManagerFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TradingConfigTest {
    private static AbstractLeagueManagerFactory leagueManagerFactory;

    @BeforeClass
    public static void setup() {
        AbstractLeagueManagerFactory.setFactory(new TestLeagueManagerFactory());
        leagueManagerFactory = AbstractLeagueManagerFactory.getFactory();
    }

    @Test
    public void setLossPointTest() {
        ITradingConfig tradingConfig = leagueManagerFactory.getTradingConfig();
        tradingConfig.setLossPoint(7);
        Assert.assertEquals(7, tradingConfig.getLossPoint());
    }

    @Test
    public void getLossPointTest() {
        ITradingConfig tradingConfig = leagueManagerFactory.getTradingConfig();
        tradingConfig.setLossPoint(11);
        Assert.assertEquals(11, tradingConfig.getLossPoint());
    }

    @Test
    public void setRandomTradeOfferChanceTest() {
        ITradingConfig tradingConfig = leagueManagerFactory.getTradingConfig();
        tradingConfig.setRandomTradeOfferChance(7.2f);
        Assert.assertEquals(7.2f, tradingConfig.getRandomTradeOfferChance(), 0.0);
    }

    @Test
    public void getRandomTradeOfferChanceTest() {
        ITradingConfig tradingConfig = leagueManagerFactory.getTradingConfig();
        tradingConfig.setRandomTradeOfferChance(0.021f);
        Assert.assertEquals(0.021f, tradingConfig.getRandomTradeOfferChance(), 0.0);
    }

    @Test
    public void setMaxPlayersPerTradeTest() {
        ITradingConfig tradingConfig = leagueManagerFactory.getTradingConfig();
        tradingConfig.setMaxPlayersPerTrade(18);
        Assert.assertEquals(18, tradingConfig.getMaxPlayersPerTrade());
    }

    @Test
    public void getMaxPlayersPerTradeTest() {
        ITradingConfig tradingConfig = leagueManagerFactory.getTradingConfig();
        tradingConfig.setMaxPlayersPerTrade(14);
        Assert.assertEquals(14, tradingConfig.getMaxPlayersPerTrade());
    }

    @Test
    public void setRandomAcceptanceChanceTest() {
        ITradingConfig tradingConfig = leagueManagerFactory.getTradingConfig();
        tradingConfig.setRandomAcceptanceChance(0.03f);
        Assert.assertEquals(0.03f, tradingConfig.getRandomAcceptanceChance(), 0.0);
    }

    @Test
    public void getRandomAcceptanceChanceTest() {
        ITradingConfig tradingConfig = leagueManagerFactory.getTradingConfig();
        tradingConfig.setRandomAcceptanceChance(0.23f);
        Assert.assertEquals(0.23f, tradingConfig.getRandomAcceptanceChance(), 0.0);
    }
}
