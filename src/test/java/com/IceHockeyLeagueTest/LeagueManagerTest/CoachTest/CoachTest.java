package com.IceHockeyLeagueTest.LeagueManagerTest.CoachTest;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
import com.IceHockeyLeague.LeagueManager.Coach.ICoachPersistence;
import com.IceHockeyLeague.LeagueManager.Coach.ICoachStats;
import com.IceHockeyLeagueTest.LeagueManagerTest.TestLeagueManagerFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CoachTest {
    private static AbstractLeagueManagerFactory leagueManagerFactory;

    @BeforeClass
    public static void setup() {
        AbstractLeagueManagerFactory.setFactory(new TestLeagueManagerFactory());
        leagueManagerFactory = AbstractLeagueManagerFactory.getFactory();
    }

    @Test
    public void ConstructorTest() {
        ICoach coach = leagueManagerFactory.getCoach();
        Assert.assertEquals(-1, coach.getTeamID());
        Assert.assertEquals(-1, coach.getLeagueID());
        Assert.assertEquals(-1, coach.getCoachID());
    }

    @Test
    public void getCoachIDTest() {
        ICoach coach = leagueManagerFactory.getCoach();
        coach.setCoachID(3);
        Assert.assertEquals(3, coach.getCoachID());
    }

    @Test
    public void setCoachIDTest() {
        ICoach coach = leagueManagerFactory.getCoach();
        coach.setCoachID(22);
        Assert.assertEquals(22, coach.getCoachID());
    }

    @Test
    public void getCoachNameTest() {
        ICoach coach = leagueManagerFactory.getCoach();
        coach.setCoachName("Bob M");
        Assert.assertEquals("Bob M", coach.getCoachName());
    }

    @Test
    public void setCoachNameTest() {
        ICoach coach = leagueManagerFactory.getCoach();
        coach.setCoachName("Michael");
        Assert.assertEquals("Michael", coach.getCoachName());
    }

    @Test
    public void getTeamIDTest() {
        ICoach coach = leagueManagerFactory.getCoach();
        coach.setTeamID(3);
        Assert.assertEquals(3, coach.getTeamID());
    }

    @Test
    public void setTeamIDTest() {
        ICoach coach = leagueManagerFactory.getCoach();
        coach.setTeamID(22);
        Assert.assertEquals(22, coach.getTeamID());
    }

    @Test
    public void getLeagueIDTest() {
        ICoach coach = leagueManagerFactory.getCoach();
        coach.setLeagueID(3);
        Assert.assertEquals(3, coach.getLeagueID());
    }

    @Test
    public void setLeagueIDTest() {
        ICoach coach = leagueManagerFactory.getCoach();
        coach.setLeagueID(22);
        Assert.assertEquals(22, coach.getLeagueID());
    }

    @Test
    public void setCoachStatsTest() {
        ICoachStats stats = leagueManagerFactory.getCoachStats();
        stats.setSaving(0.7f);
        stats.setChecking(0.5f);
        stats.setShooting(0.3f);
        stats.setSkating(0.8f);

        ICoach coach = leagueManagerFactory.getCoach();
        coach.setCoachStats(stats);

        ICoachStats coachStats = coach.getCoachStats();
        Assert.assertEquals(0.7f, coachStats.getSaving(), 0.0);
        Assert.assertEquals(0.3f, coachStats.getShooting(), 0.0);
    }

    @Test
    public void isValidTest() {
        ICoach coach = leagueManagerFactory.getCoach();

        coach.setCoachName("Raj K");
        Assert.assertTrue(coach.isValid());

        coach.setCoachName("");
        Assert.assertFalse(coach.isValid());
    }

    @Test
    public void isCoachNameExistTest() {
        ICoachPersistence coachDB = leagueManagerFactory.getCoachDB();
        ICoach coach = leagueManagerFactory.getCoach();
        List<ICoach> coaches = new ArrayList<>();

        coachDB.loadLeagueCoaches(1, coaches);

        Assert.assertFalse(coach.isCoachNameExist(coaches, ""));
        Assert.assertTrue(coach.isCoachNameExist(coaches, "joe doe"));
    }

    @Test
    public void saveTeamCoachTest() {
        ICoach coach = leagueManagerFactory.getCoach();
        ICoachPersistence coachDB = leagueManagerFactory.getCoachDB();

        coach.saveTeamCoach(coachDB);

        Assert.assertEquals(2, coach.getTeamID());
        Assert.assertEquals("Jonathan", coach.getCoachName());
    }

    @Test
    public void saveLeagueCoachTest() {
        ICoach coach = leagueManagerFactory.getCoach();
        ICoachPersistence coachDB = leagueManagerFactory.getCoachDB();

        coach.saveLeagueCoach(coachDB);

        Assert.assertEquals(-1, coach.getTeamID());
        Assert.assertEquals("Ronald", coach.getCoachName());
        Assert.assertEquals(4, coach.getCoachID());
    }

    @Test
    public void loadTeamCoachTest() {
        ICoach coach = leagueManagerFactory.getCoach();
        ICoachPersistence coachDB = leagueManagerFactory.getCoachDB();

        coach.loadTeamCoach(coachDB, coach);

        Assert.assertEquals(1, coach.getTeamID());
        Assert.assertEquals("Joe Doe", coach.getCoachName());
        Assert.assertEquals(1, coach.getCoachID());
    }
}
