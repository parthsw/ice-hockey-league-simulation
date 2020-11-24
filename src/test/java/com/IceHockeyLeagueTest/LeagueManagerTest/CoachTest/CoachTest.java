package com.IceHockeyLeagueTest.LeagueManagerTest.CoachTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.Database.IDatabaseFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
import com.IceHockeyLeague.LeagueManager.Coach.ICoachPersistence;
import com.IceHockeyLeague.LeagueManager.Coach.ICoachStats;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CoachTest {
    private static ILeagueManagerFactory leagueManagerFactory;
    private static IDatabaseFactory databaseFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
        databaseFactory = appFactory.createDatabaseFactory();
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
    }

    @Test
    public void isValidTrueTest() {
        ICoach coach = leagueManagerFactory.createCoach();
        coach.setCoachName("Raj K");
        Assert.assertTrue(coach.isValid());
    }

    @Test
    public void isValidFalseTest() {
        ICoach coach = leagueManagerFactory.createCoach();
        coach.setCoachName("");
        Assert.assertFalse(coach.isValid());
    }

    @Test
    public void isCoachNameExistTrueTest() {
        ICoachPersistence coachDB = databaseFactory.createCoachPersistence();
        ICoach coach = leagueManagerFactory.createCoach();
        List<ICoach> coaches = new ArrayList<>();
        coachDB.loadLeagueCoaches(1, coaches);
        Assert.assertTrue(coach.isCoachNameExist(coaches, "joe doe"));
    }

    @Test
    public void isCoachNameExistFalseTest() {
        ICoachPersistence coachDB = databaseFactory.createCoachPersistence();
        ICoach coach = leagueManagerFactory.createCoach();
        List<ICoach> coaches = new ArrayList<>();
        coachDB.loadLeagueCoaches(1, coaches);
        Assert.assertFalse(coach.isCoachNameExist(coaches, ""));
    }

    @Test
    public void saveTeamCoachTest() {
        ICoach coach = leagueManagerFactory.createCoach();
        ICoachPersistence coachDB = databaseFactory.createCoachPersistence();
        coach.saveTeamCoach(coachDB);
        Assert.assertEquals(2, coach.getTeamID());
        Assert.assertEquals("Jonathan", coach.getCoachName());
    }

    @Test
    public void saveLeagueCoachTest() {
        ICoach coach = leagueManagerFactory.createCoach();
        ICoachPersistence coachDB = databaseFactory.createCoachPersistence();
        coach.saveLeagueCoach(coachDB);
        Assert.assertEquals(-1, coach.getTeamID());
        Assert.assertEquals("Ronald", coach.getCoachName());
        Assert.assertEquals(4, coach.getCoachID());
    }

    @Test
    public void loadTeamCoachTest() {
        ICoach coach = leagueManagerFactory.createCoach();
        ICoachPersistence coachDB = databaseFactory.createCoachPersistence();
        coach.loadTeamCoach(coachDB, coach);
        Assert.assertEquals(1, coach.getTeamID());
        Assert.assertEquals("Joe Doe", coach.getCoachName());
        Assert.assertEquals(1, coach.getCoachID());
    }

}
