package com.IceHockeyLeagueTest.LeagueManagerTest.ConferenceTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.Database.IDatabaseFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Conference.IConferencePersistence;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.Division.IDivisionPersistence;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ConferenceTest {
    private static ILeagueManagerFactory leagueManagerFactory;
    private static IDatabaseFactory databaseFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactoryTest());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
        databaseFactory = appFactory.createDatabaseFactory();
    }

    @Test
    public void ConstructorTest() {
        IConference conference = leagueManagerFactory.createConference();
        Assert.assertEquals(-1, conference.getConferenceID());
        Assert.assertEquals(-1, conference.getLeagueID());
    }

    @Test
    public void getConferenceIDTest() {
        IConference conference = leagueManagerFactory.createConference();
        conference.setConferenceID(34);
        Assert.assertEquals(34, conference.getConferenceID());
    }

    @Test
    public void setConferenceIDTest() {
        IConference conference = leagueManagerFactory.createConference();
        conference.setConferenceID(4);
        Assert.assertEquals(4, conference.getConferenceID());
    }

    @Test
    public void getConferenceNameTest() {
        IConference conference = leagueManagerFactory.createConference();
        conference.setConferenceName("Western");
        Assert.assertEquals("Western", conference.getConferenceName());
    }

    @Test
    public void setConferenceNameTest() {
        IConference conference = leagueManagerFactory.createConference();
        conference.setConferenceName("Eastern");
        Assert.assertEquals("Eastern", conference.getConferenceName());
    }

    @Test
    public void getLeagueIDTest() {
        IConference conference = leagueManagerFactory.createConference();
        conference.setLeagueID(34);
        Assert.assertEquals(34, conference.getLeagueID());
    }

    @Test
    public void setLeagueIDTest() {
        IConference conference = leagueManagerFactory.createConference();
        conference.setLeagueID(4);
        Assert.assertEquals(4, conference.getLeagueID());
    }

    @Test
    public void getDivisionByIdTest() {
        IConference conference = leagueManagerFactory.createConference();
        Assert.assertNull(conference.getDivisionById(1));
    }

    @Test
    public void addDivisionTest() {
        IConference conference = leagueManagerFactory.createConference();
        IDivision division = leagueManagerFactory.createDivision();
        conference.addDivision(division);

        List<IDivision> conferenceDivisions = conference.getDivisions();
        Assert.assertEquals(1, conferenceDivisions.size());
    }

    @Test
    public void setDivisionsTest() {
        IConference conference = leagueManagerFactory.createConference();
        IDivisionPersistence divisionDB = databaseFactory.createDivisionPersistence();
        List<IDivision> divisions = new ArrayList<>();
        conference.loadDivisions(divisionDB, divisions);
        conference.setDivisions(divisions);

        List<IDivision> conferenceDivisions = conference.getDivisions();
        Assert.assertEquals(2, conferenceDivisions.size());
    }

    @Test
    public void saveConferenceTest() {
        IConference conference = leagueManagerFactory.createConference();
        IConferencePersistence conferenceDB = databaseFactory.createConferencePersistence();

        Assert.assertTrue(conference.saveConference(conferenceDB));
        Assert.assertEquals(1, conference.getConferenceID());
        Assert.assertEquals(1, conference.getLeagueID());
        Assert.assertEquals("Eastern Conference", conference.getConferenceName());
    }

    @Test
    public void loadDivisionsTest() {
        IConference conference = leagueManagerFactory.createConference();
        IDivisionPersistence divisionDB = databaseFactory.createDivisionPersistence();
        List<IDivision> divisions = new ArrayList<>();

        conference.loadDivisions(divisionDB, divisions);
        Assert.assertEquals(2, divisions.size());
    }

    @Test
    public void isNullOrEmptyTest() {
        IConference conference = leagueManagerFactory.createConference();
        Assert.assertTrue(conference.isNullOrEmpty(null));
        Assert.assertFalse(conference.isNullOrEmpty("Eastern"));
    }

    @Test
    public void isConferenceNameExistTest() {
        IConference conference = leagueManagerFactory.createConference();
        IConferencePersistence conferenceDB = databaseFactory.createConferencePersistence();
        List<IConference> conferences = new ArrayList<>();
        conferenceDB.loadConferences(1, conferences);

        Assert.assertFalse(conference.isConferenceNameExist(conferences, "central"));
        Assert.assertTrue(conference.isConferenceNameExist(conferences, "Eastern Conference"));
    }

}
