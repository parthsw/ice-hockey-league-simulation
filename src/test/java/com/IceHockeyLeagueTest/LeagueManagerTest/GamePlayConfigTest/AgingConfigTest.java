package com.IceHockeyLeagueTest.LeagueManagerTest.GamePlayConfigTest;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IAgingConfig;
import com.IceHockeyLeagueTest.LeagueManagerTest.TestLeagueManagerFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class AgingConfigTest {

    @BeforeClass
    public static void setup() {
        AbstractLeagueManagerFactory.setFactory(new TestLeagueManagerFactory());
    }

    @Test
    public void setAverageRetirementAgeTest() {
        IAgingConfig agingConfig = AbstractLeagueManagerFactory.getFactory().getAgingConfig();
        agingConfig.setAverageRetirementAge(45);
        Assert.assertEquals(45, agingConfig.getAverageRetirementAge());
    }

    @Test
    public void getAverageRetirementAgeTest() {
        IAgingConfig agingConfig = AbstractLeagueManagerFactory.getFactory().getAgingConfig();
        agingConfig.setAverageRetirementAge(30);
        Assert.assertEquals(30, agingConfig.getAverageRetirementAge());
    }

    @Test
    public void setMaximumAgeTest() {
        IAgingConfig agingConfig = AbstractLeagueManagerFactory.getFactory().getAgingConfig();
        agingConfig.setMaximumAge(50);
        Assert.assertEquals(50, agingConfig.getMaximumAge());
    }


    @Test
    public void getMaximumAgeTest() {
        IAgingConfig agingConfig = AbstractLeagueManagerFactory.getFactory().getAgingConfig();
        agingConfig.setMaximumAge(56);
        Assert.assertEquals(56, agingConfig.getMaximumAge());
    }
}
