package com.IceHockeyLeagueTest.LeagueManagerTest.CoachTest;

import com.IceHockeyLeague.LeagueManager.Coach.ICoachStats;
import com.IceHockeyLeague.LeagueManager.LeagueManagerFactory;
import com.IceHockeyLeagueTest.LeagueManagerTest.TestLeagueManagerFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CoachStatsTest {
    @Before
    public void setup() {
        LeagueManagerFactory.setFactory(new TestLeagueManagerFactory());
    }

    @Test
    public void ConstructorTest() {
        ICoachStats coachStats = LeagueManagerFactory.getFactory().getCoachStats();
        Assert.assertEquals(0.0, coachStats.getSkating(), 0.0);
        Assert.assertEquals(0.0, coachStats.getShooting(), 0.0);
        Assert.assertEquals(0.0, coachStats.getChecking(), 0.0);
        Assert.assertEquals(0.0, coachStats.getSaving(), 0.0);
    }

    @Test
    public void getSkatingTest() {
        ICoachStats coachStats = LeagueManagerFactory.getFactory().getCoachStats();
        coachStats.setSkating(0.8);
        Assert.assertEquals(0.8, coachStats.getSkating(), 0.0);
    }

    @Test
    public void setSkatingTest() {
        ICoachStats coachStats = LeagueManagerFactory.getFactory().getCoachStats();
        coachStats.setSkating(0.8);
        Assert.assertEquals(0.8, coachStats.getSkating(), 0.0);

        coachStats.setSkating(1.1);
        Assert.assertEquals(0.8, coachStats.getSkating(), 0.0);
    }

    @Test
    public void getShootingTest() {
        ICoachStats coachStats = LeagueManagerFactory.getFactory().getCoachStats();
        coachStats.setShooting(0.7);
        Assert.assertEquals(0.7, coachStats.getShooting(), 0.0);
    }

    @Test
    public void setShootingTest() {
        ICoachStats coachStats = LeagueManagerFactory.getFactory().getCoachStats();
        coachStats.setShooting(1.0);
        Assert.assertEquals(1.0, coachStats.getShooting(), 0.0);

        coachStats.setShooting(1.9);
        Assert.assertEquals(1.0, coachStats.getShooting(), 0.0);
    }

    @Test
    public void getCheckingTest() {
        ICoachStats coachStats = LeagueManagerFactory.getFactory().getCoachStats();
        coachStats.setChecking(0.4);
        Assert.assertEquals(0.4, coachStats.getChecking(), 0.0);
    }

    @Test
    public void setCheckingTest() {
        ICoachStats coachStats = LeagueManagerFactory.getFactory().getCoachStats();
        coachStats.setChecking(0.1);
        Assert.assertEquals(0.1, coachStats.getChecking(), 0.0);

        coachStats.setChecking(2.3);
        Assert.assertEquals(0.1, coachStats.getChecking(), 0.0);
    }

    @Test
    public void getSavingTest() {
        ICoachStats coachStats = LeagueManagerFactory.getFactory().getCoachStats();
        coachStats.setSaving(0.4);
        Assert.assertEquals(0.4, coachStats.getSaving(), 0.0);
    }

    @Test
    public void setSavingTest() {
        ICoachStats coachStats = LeagueManagerFactory.getFactory().getCoachStats();
        coachStats.setSaving(0.5);
        Assert.assertEquals(0.5, coachStats.getSaving(), 0.0);

        coachStats.setSaving(9);
        Assert.assertEquals(0.5, coachStats.getSaving(), 0.0);
    }

}
