package com.IceHockeyLeagueTest.LeagueManagerTest.GamePlayConfigTest;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IInjuryConfig;
import com.IceHockeyLeagueTest.LeagueManagerTest.TestLeagueManagerFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class InjuryConfigTest {
    private static AbstractLeagueManagerFactory leagueManagerFactory;

    @BeforeClass
    public static void setup() {
        AbstractLeagueManagerFactory.setFactory(new TestLeagueManagerFactory());
        leagueManagerFactory = AbstractLeagueManagerFactory.getFactory();
    }

    @Test
    public void setRandomInjuryChanceTest() {
        IInjuryConfig injuryConfig = leagueManagerFactory.getInjuryConfig();
        injuryConfig.setRandomInjuryChance(1.11f);
        Assert.assertEquals(1.11f, injuryConfig.getRandomInjuryChance(), 0.0);
    }

    @Test
    public void getRandomInjuryChanceTest() {
        IInjuryConfig injuryConfig = leagueManagerFactory.getInjuryConfig();
        injuryConfig.setRandomInjuryChance(2.12f);
        Assert.assertEquals(2.12f, injuryConfig.getRandomInjuryChance(), 0.0);
    }

    @Test
    public void setInjuryDaysLowTest() {
        IInjuryConfig injuryConfig = leagueManagerFactory.getInjuryConfig();
        injuryConfig.setInjuryDaysLow(5);
        Assert.assertEquals(5, injuryConfig.getInjuryDaysLow());
    }

    @Test
    public void getInjuryDaysLowTest() {
        IInjuryConfig injuryConfig = leagueManagerFactory.getInjuryConfig();
        injuryConfig.setInjuryDaysLow(1);
        Assert.assertEquals(1, injuryConfig.getInjuryDaysLow());
    }

    @Test
    public void setInjuryDaysHighTest() {
        IInjuryConfig injuryConfig = leagueManagerFactory.getInjuryConfig();
        injuryConfig.setInjuryDaysHigh(340);
        Assert.assertEquals(340, injuryConfig.getInjuryDaysHigh());
    }

    @Test
    public void getInjuryDaysHighTest() {
        IInjuryConfig injuryConfig = leagueManagerFactory.getInjuryConfig();
        injuryConfig.setInjuryDaysHigh(311);
        Assert.assertEquals(311, injuryConfig.getInjuryDaysHigh());
    }
}
