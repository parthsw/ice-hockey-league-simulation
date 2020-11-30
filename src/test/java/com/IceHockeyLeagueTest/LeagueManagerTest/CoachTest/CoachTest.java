package com.IceHockeyLeagueTest.LeagueManagerTest.CoachTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
import com.IceHockeyLeague.LeagueManager.Coach.ICoachStats;
import com.PersistenceTest.CoachPersistenceMock;
import com.PersistenceTest.PersistenceFactoryTest;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CoachTest {
    private static ILeagueManagerFactory leagueManagerFactory;
    private static PersistenceFactoryTest persistenceFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
        persistenceFactory = AppFactoryTest.createPersistenceFactoryTest();
    }

    @Test
    public void ConstructorTest() {
        ICoach coach = leagueManagerFactory.createCoach();
        Assert.assertEquals(-1, coach.getTeamID());
        Assert.assertEquals(-1, coach.getLeagueID());
        Assert.assertEquals(-1, coach.getCoachID());
    }

    @Test
    public void getCoachIDTest() {
        ICoach coach = leagueManagerFactory.createCoach();
        coach.setCoachID(3);
        Assert.assertEquals(3, coach.getCoachID());
    }

    @Test
    public void setCoachIDTest() {
        ICoach coach = leagueManagerFactory.createCoach();
        coach.setCoachID(22);
        Assert.assertEquals(22, coach.getCoachID());
    }

    @Test
    public void getCoachNameTest() {
        ICoach coach = leagueManagerFactory.createCoach();
        coach.setCoachName("Bob M");
        Assert.assertEquals("Bob M", coach.getCoachName());
    }

    @Test
    public void setCoachNameTest() {
        ICoach coach = leagueManagerFactory.createCoach();
        coach.setCoachName("Michael");
        Assert.assertEquals("Michael", coach.getCoachName());
    }

    @Test
    public void getTeamIDTest() {
        ICoach coach = leagueManagerFactory.createCoach();
        coach.setTeamID(3);
        Assert.assertEquals(3, coach.getTeamID());
    }

    @Test
    public void setTeamIDTest() {
        ICoach coach = leagueManagerFactory.createCoach();
        coach.setTeamID(22);
        Assert.assertEquals(22, coach.getTeamID());
    }

    @Test
    public void getLeagueIDTest() {
        ICoach coach = leagueManagerFactory.createCoach();
        coach.setLeagueID(3);
        Assert.assertEquals(3, coach.getLeagueID());
    }

    @Test
    public void setLeagueIDTest() {
        ICoach coach = leagueManagerFactory.createCoach();
        coach.setLeagueID(22);
        Assert.assertEquals(22, coach.getLeagueID());
    }

    @Test
    public void setCoachStatsTest() {
        ICoachStats stats = leagueManagerFactory.createCoachStats();
        stats.setSaving(0.7f);
        stats.setChecking(0.5f);
        stats.setShooting(0.3f);
        stats.setSkating(0.8f);

        ICoach coach = leagueManagerFactory.createCoach();
        coach.setCoachStats(stats);

        ICoachStats coachStats = coach.getCoachStats();
        Assert.assertEquals(0.7f, coachStats.getSaving(), 0.0);
        Assert.assertEquals(0.3f, coachStats.getShooting(), 0.0);
    }

    @Test
    public void isValidTest() {
        ICoach coach = leagueManagerFactory.createCoach();

        coach.setCoachName("Raj K");
        Assert.assertTrue(coach.isValid());

        coach.setCoachName("");
        Assert.assertFalse(coach.isValid());
    }

    @Test
    public void isCoachNameExistTest() {
        CoachPersistenceMock coachPersistenceMock = persistenceFactory.createCoachPersistence();
        ICoach coach = leagueManagerFactory.createCoach();
        List<ICoach> coaches = new ArrayList<>();
        coachPersistenceMock.loadLeagueCoaches(1, coaches);
        Assert.assertFalse(coach.isCoachNameExist(coaches, ""));
        Assert.assertTrue(coach.isCoachNameExist(coaches, "joe doe"));
    }

}
