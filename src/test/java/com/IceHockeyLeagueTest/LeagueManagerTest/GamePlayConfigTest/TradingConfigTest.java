package com.IceHockeyLeagueTest.LeagueManagerTest.GamePlayConfigTest;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.ITradingConfig;
import com.IceHockeyLeagueTest.LeagueManagerTest.TestLeagueManagerFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TradingConfigTest {

    @BeforeClass
    public static void setup() {
        AbstractLeagueManagerFactory.setFactory(new TestLeagueManagerFactory());
    }

    @Test
    public void setLossPointTest() {
        ITradingConfig tradingConfig = AbstractLeagueManagerFactory.getFactory().getTradingConfig();
        tradingConfig.setLossPoint(7);
        Assert.assertEquals(7, tradingConfig.getLossPoint());
    }

    @Test
    public void getLossPointTest() {
        ITradingConfig tradingConfig = AbstractLeagueManagerFactory.getFactory().getTradingConfig();
        tradingConfig.setLossPoint(11);
        Assert.assertEquals(11, tradingConfig.getLossPoint());
    }

    @Test
    public void setRandomTradeOfferChanceTest() {
        ITradingConfig tradingConfig = AbstractLeagueManagerFactory.getFactory().getTradingConfig();
        tradingConfig.setRandomTradeOfferChance(7.2);
        Assert.assertEquals(7.2, tradingConfig.getRandomTradeOfferChance(), 0.0);
    }

    @Test
    public void getRandomTradeOfferChanceTest() {
        ITradingConfig tradingConfig = AbstractLeagueManagerFactory.getFactory().getTradingConfig();
        tradingConfig.setRandomTradeOfferChance(0.021);
        Assert.assertEquals(0.021, tradingConfig.getRandomTradeOfferChance(), 0.0);
    }

    @Test
    public void setMaxPlayersPerTradeTest() {
        ITradingConfig tradingConfig = AbstractLeagueManagerFactory.getFactory().getTradingConfig();
        tradingConfig.setMaxPlayersPerTrade(18);
        Assert.assertEquals(18, tradingConfig.getMaxPlayersPerTrade());
    }

    @Test
    public void getMaxPlayersPerTradeTest() {
        ITradingConfig tradingConfig = AbstractLeagueManagerFactory.getFactory().getTradingConfig();
        tradingConfig.setMaxPlayersPerTrade(14);
        Assert.assertEquals(14, tradingConfig.getMaxPlayersPerTrade());
    }

    @Test
    public void setRandomAcceptanceChanceTest() {
        ITradingConfig tradingConfig = AbstractLeagueManagerFactory.getFactory().getTradingConfig();
        tradingConfig.setRandomAcceptanceChance(0.03);
        Assert.assertEquals(0.03, tradingConfig.getRandomAcceptanceChance(), 0.0);
    }

    @Test
    public void getRandomAcceptanceChanceTest() {
        ITradingConfig tradingConfig = AbstractLeagueManagerFactory.getFactory().getTradingConfig();
        tradingConfig.setRandomAcceptanceChance(0.23);
        Assert.assertEquals(0.23, tradingConfig.getRandomAcceptanceChance(), 0.0);
    }
}
