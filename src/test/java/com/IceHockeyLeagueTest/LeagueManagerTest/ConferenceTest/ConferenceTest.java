package com.IceHockeyLeagueTest.LeagueManagerTest.ConferenceTest;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Conference.IConferencePersistence;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.Division.IDivisionPersistence;
import com.IceHockeyLeagueTest.LeagueManagerTest.TestLeagueManagerFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ConferenceTest {
    private static AbstractLeagueManagerFactory leagueManagerFactory;

    @BeforeClass
    public static void setup() {
        AbstractLeagueManagerFactory.setFactory(new TestLeagueManagerFactory());
        leagueManagerFactory = AbstractLeagueManagerFactory.getFactory();
    }

    @Test
    public void ConstructorTest() {
        IConference conference = leagueManagerFactory.getConference();
        Assert.assertEquals(-1, conference.getConferenceID());
        Assert.assertEquals(-1, conference.getLeagueID());
    }

    @Test
    public void getConferenceIDTest() {
        IConference conference = leagueManagerFactory.getConference();
        conference.setConferenceID(34);
        Assert.assertEquals(34, conference.getConferenceID());
    }

    @Test
    public void setConferenceIDTest() {
        IConference conference = leagueManagerFactory.getConference();
        conference.setConferenceID(4);
        Assert.assertEquals(4, conference.getConferenceID());
    }

    @Test
    public void getConferenceNameTest() {
        IConference conference = leagueManagerFactory.getConference();
        conference.setConferenceName("Western");
        Assert.assertEquals("Western", conference.getConferenceName());
    }

    @Test
    public void setConferenceNameTest() {
        IConference conference = leagueManagerFactory.getConference();
        conference.setConferenceName("Eastern");
        Assert.assertEquals("Eastern", conference.getConferenceName());
    }

    @Test
    public void getLeagueIDTest() {
        IConference conference = leagueManagerFactory.getConference();
        conference.setLeagueID(34);
        Assert.assertEquals(34, conference.getLeagueID());
    }

    @Test
    public void setLeagueIDTest() {
        IConference conference = leagueManagerFactory.getConference();
        conference.setLeagueID(4);
        Assert.assertEquals(4, conference.getLeagueID());
    }

    @Test
    public void getDivisionByIdTest() {
        IConference conference = leagueManagerFactory.getConference();
        Assert.assertNull(conference.getDivisionById(1));
    }

    @Test
    public void addDivisionTest() {
        IConference conference = leagueManagerFactory.getConference();
        IDivision division = leagueManagerFactory.getDivision();
        conference.addDivision(division);

        List<IDivision> conferenceDivisions = conference.getDivisions();
        Assert.assertEquals(1, conferenceDivisions.size());
    }

    @Test
    public void setDivisionsTest() {
        IConference conference = leagueManagerFactory.getConference();
        IDivisionPersistence divisionDB = leagueManagerFactory.getDivisionDB();
        List<IDivision> divisions = new ArrayList<>();
        conference.loadDivisions(divisionDB, divisions);
        conference.setDivisions(divisions);

        List<IDivision> conferenceDivisions = conference.getDivisions();
        Assert.assertEquals(2, conferenceDivisions.size());
    }

    @Test
    public void saveConferenceTest() {
        IConference conference = leagueManagerFactory.getConference();
        IConferencePersistence conferenceDB = leagueManagerFactory.getConferenceDB();

        Assert.assertTrue(conference.saveConference(conferenceDB));
        Assert.assertEquals(1, conference.getConferenceID());
        Assert.assertEquals(1, conference.getLeagueID());
        Assert.assertEquals("Eastern Conference", conference.getConferenceName());
    }

    @Test
    public void loadDivisionsTest() {
        IConference conference = leagueManagerFactory.getConference();
        IDivisionPersistence divisionDB = leagueManagerFactory.getDivisionDB();
        List<IDivision> divisions = new ArrayList<>();

        conference.loadDivisions(divisionDB, divisions);
        Assert.assertEquals(2, divisions.size());
    }

    @Test
    public void isNullOrEmptyTest() {
        IConference conference = leagueManagerFactory.getConference();
        Assert.assertTrue(conference.isNullOrEmpty(null));
        Assert.assertFalse(conference.isNullOrEmpty("Eastern"));
    }

    @Test
    public void isConferenceNameExistTest() {
        IConference conference = leagueManagerFactory.getConference();
        IConferencePersistence conferenceDB = leagueManagerFactory.getConferenceDB();
        List<IConference> conferences = new ArrayList<>();
        conferenceDB.loadConferences(1, conferences);

        Assert.assertFalse(conference.isConferenceNameExist(conferences, "central"));
        Assert.assertTrue(conference.isConferenceNameExist(conferences, "Eastern Conference"));
    }

}
