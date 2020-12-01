package com.IceHockeyLeagueTest.LeagueManagerTest.GamePlayConfigTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IAgingConfig;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class AgingConfigTest {
    private static ILeagueManagerFactory leagueManagerFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
    }

    @Test
    public void setAverageRetirementAgeTest() {
        IAgingConfig agingConfig = leagueManagerFactory.createAgingConfig();
        agingConfig.setAverageRetirementAge(45);
        Assert.assertEquals(45, agingConfig.getAverageRetirementAge());
    }

    @Test
    public void getAverageRetirementAgeTest() {
        IAgingConfig agingConfig = leagueManagerFactory.createAgingConfig();
        agingConfig.setAverageRetirementAge(30);
        Assert.assertEquals(30, agingConfig.getAverageRetirementAge());
    }

    @Test
    public void setMaximumAgeTest() {
        IAgingConfig agingConfig = leagueManagerFactory.createAgingConfig();
        agingConfig.setMaximumAge(50);
        Assert.assertEquals(50, agingConfig.getMaximumAge());
    }

    @Test
    public void getMaximumAgeTest() {
        IAgingConfig agingConfig = leagueManagerFactory.createAgingConfig();
        agingConfig.setMaximumAge(56);
        Assert.assertEquals(56, agingConfig.getMaximumAge());
    }

    @Test
    public void setStatDecayChanceTest() {
        IAgingConfig agingConfig = leagueManagerFactory.createAgingConfig();
        agingConfig.setStatDecayChance(0.05f);
        Assert.assertEquals(0.05f, agingConfig.getStatDecayChance(), 0.0);
    }

    @Test
    public void getStatDecayChanceTest() {
        IAgingConfig agingConfig = leagueManagerFactory.createAgingConfig();
        agingConfig.setStatDecayChance(1.23f);
        Assert.assertEquals(1.23f, agingConfig.getStatDecayChance(), 0.0);
    }

}
