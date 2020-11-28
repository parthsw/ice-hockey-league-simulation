package com.IceHockeyLeagueTest.LeagueManagerTest.CoachTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Coach.ICoachStats;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CoachStatsTest {
    private static ILeagueManagerFactory leagueManagerFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
    }

    @Test
    public void ConstructorTest() {
        ICoachStats coachStats = leagueManagerFactory.createCoachStats();
        Assert.assertEquals(0.0f, coachStats.getSkating(), 0.0);
        Assert.assertEquals(0.0f, coachStats.getShooting(), 0.0);
        Assert.assertEquals(0.0f, coachStats.getChecking(), 0.0);
        Assert.assertEquals(0.0f, coachStats.getSaving(), 0.0);
    }

    @Test
    public void getSkatingTest() {
        ICoachStats coachStats = leagueManagerFactory.createCoachStats();
        coachStats.setSkating(0.8f);
        Assert.assertEquals(0.8f, coachStats.getSkating(), 0.0);
    }

    @Test
    public void setSkatingTest() {
        ICoachStats coachStats = leagueManagerFactory.createCoachStats();
        coachStats.setSkating(0.8f);
        Assert.assertEquals(0.8f, coachStats.getSkating(), 0.0);

        coachStats.setSkating(1.1f);
        Assert.assertEquals(0.8f, coachStats.getSkating(), 0.0);
    }

    @Test
    public void getShootingTest() {
        ICoachStats coachStats = leagueManagerFactory.createCoachStats();
        coachStats.setShooting(0.7f);
        Assert.assertEquals(0.7f, coachStats.getShooting(), 0.0);
    }

    @Test
    public void setShootingTest() {
        ICoachStats coachStats = leagueManagerFactory.createCoachStats();
        coachStats.setShooting(1.0f);
        Assert.assertEquals(1.0f, coachStats.getShooting(), 0.0);

        coachStats.setShooting(1.9f);
        Assert.assertEquals(1.0f, coachStats.getShooting(), 0.0);
    }

    @Test
    public void getCheckingTest() {
        ICoachStats coachStats = leagueManagerFactory.createCoachStats();
        coachStats.setChecking(0.4f);
        Assert.assertEquals(0.4f, coachStats.getChecking(), 0.0);
    }

    @Test
    public void setCheckingTest() {
        ICoachStats coachStats = leagueManagerFactory.createCoachStats();
        coachStats.setChecking(0.1f);
        Assert.assertEquals(0.1f, coachStats.getChecking(), 0.0);

        coachStats.setChecking(2.3f);
        Assert.assertEquals(0.1f, coachStats.getChecking(), 0.0);
    }

    @Test
    public void getSavingTest() {
        ICoachStats coachStats = leagueManagerFactory.createCoachStats();
        coachStats.setSaving(0.4f);
        Assert.assertEquals(0.4f, coachStats.getSaving(), 0.0);
    }

    @Test
    public void setSavingTest() {
        ICoachStats coachStats = leagueManagerFactory.createCoachStats();
        coachStats.setSaving(0.5f);
        Assert.assertEquals(0.5f, coachStats.getSaving(), 0.0);
        coachStats.setSaving(9);
        Assert.assertEquals(0.5f, coachStats.getSaving(), 0.0);
    }

}
