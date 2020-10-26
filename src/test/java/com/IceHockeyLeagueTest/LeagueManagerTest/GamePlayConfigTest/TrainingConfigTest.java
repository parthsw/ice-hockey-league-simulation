package com.IceHockeyLeagueTest.LeagueManagerTest.GamePlayConfigTest;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.ITrainingConfig;
import com.IceHockeyLeagueTest.LeagueManagerTest.TestLeagueManagerFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TrainingConfigTest {

    @BeforeClass
    public static void setup() {
        AbstractLeagueManagerFactory.setFactory(new TestLeagueManagerFactory());
    }

    @Test
    public void setDaysUntilStatIncreaseCheckTest() {
        ITrainingConfig trainingConfig = AbstractLeagueManagerFactory.getFactory().getTrainingConfig();
        trainingConfig.setDaysUntilStatIncreaseCheck(111);
        Assert.assertEquals(111, trainingConfig.getDaysUntilStatIncreaseCheck());
    }


    @Test
    public void getDaysUntilStatIncreaseCheckTest() {
        ITrainingConfig trainingConfig = AbstractLeagueManagerFactory.getFactory().getTrainingConfig();
        trainingConfig.setDaysUntilStatIncreaseCheck(27);
        Assert.assertEquals(27, trainingConfig.getDaysUntilStatIncreaseCheck());
    }
}
