package com.IceHockeyLeagueTest.LeagueManagerTest.GamePlayConfigTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.ITrainingConfig;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TrainingConfigTest {
    private static ILeagueManagerFactory leagueManagerFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
    }

    @Test
    public void setDaysUntilStatIncreaseCheckTest() {
        ITrainingConfig trainingConfig = leagueManagerFactory.createTrainingConfig();
        trainingConfig.setDaysUntilStatIncreaseCheck(111);
        Assert.assertEquals(111, trainingConfig.getDaysUntilStatIncreaseCheck());
    }

    @Test
    public void getDaysUntilStatIncreaseCheckTest() {
        ITrainingConfig trainingConfig = leagueManagerFactory.createTrainingConfig();
        trainingConfig.setDaysUntilStatIncreaseCheck(27);
        Assert.assertEquals(27, trainingConfig.getDaysUntilStatIncreaseCheck());
    }
}
