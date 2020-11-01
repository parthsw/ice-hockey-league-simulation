package com.IceHockeyLeagueTest.LeagueManagerTest.DivisionTest;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.Division.IDivisionPersistence;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Team.ITeamPersistence;
import com.IceHockeyLeagueTest.LeagueManagerTest.TestLeagueManagerFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DivisionTest {
    private static AbstractLeagueManagerFactory leagueManagerFactory;

    @BeforeClass
    public static void setup() {
        AbstractLeagueManagerFactory.setFactory(new TestLeagueManagerFactory());
        leagueManagerFactory = AbstractLeagueManagerFactory.getFactory();
    }

    @Test
    public void ConstructorTest() {
        IDivision division = leagueManagerFactory.getDivision();
        Assert.assertEquals(-1, division.getDivisionID());
        Assert.assertEquals(-1, division.getConferenceID());
    }

    @Test
    public void getDivisionIDTest() {
        IDivision division = leagueManagerFactory.getDivision();
        division.setDivisionID(11);
        Assert.assertEquals(11, division.getDivisionID());
    }

    @Test
    public void setDivisionIDTest() {
        IDivision division = leagueManagerFactory.getDivision();
        division.setDivisionID(3);
        Assert.assertEquals(3, division.getDivisionID());
    }

    @Test
    public void getDivisionNameTest() {
        IDivision division = leagueManagerFactory.getDivision();
        division.setDivisionName("Atlantic");
        Assert.assertEquals("Atlantic", division.getDivisionName());
    }

    @Test
    public void setDivisionNameTest() {
        IDivision division = leagueManagerFactory.getDivision();
        division.setDivisionName("Pacific");
        Assert.assertEquals("Pacific", division.getDivisionName());
    }

    @Test
    public void getConferenceIDTest() {
        IDivision division = leagueManagerFactory.getDivision();
        division.setConferenceID(11);
        Assert.assertEquals(11, division.getConferenceID());
    }

    @Test
    public void setConferenceIDTest() {
        IDivision division = leagueManagerFactory.getDivision();
        division.setConferenceID(3);
        Assert.assertEquals(3, division.getConferenceID());
    }

    @Test
    public void getTeamByIdTest() {
        IDivision division = leagueManagerFactory.getDivision();
        Assert.assertNull(division.getTeamById(1));
    }

    @Test
    public void addTeamTest() {
        IDivision division = leagueManagerFactory.getDivision();
        ITeam team = leagueManagerFactory.getTeam();
        team.setTeamName("Halifax");

        division.addTeam(team);
        List<ITeam> divisionTeams = division.getTeams();
        Assert.assertEquals(1, divisionTeams.size());
    }

    @Test
    public void setTeamsTest() {
        IDivision division = leagueManagerFactory.getDivision();
        List<ITeam> teams = new ArrayList<>();
        ITeamPersistence teamDB = leagueManagerFactory.getTeamDB();
        division.loadTeams(teamDB, teams);

        division.setTeams(teams);

        List<ITeam> divisionTeams = division.getTeams();
        Assert.assertEquals(1, divisionTeams.size());
    }

    @Test
    public void saveDivisionTest() {
        IDivision division = leagueManagerFactory.getDivision();
        IDivisionPersistence divisionDB = leagueManagerFactory.getDivisionDB();

        Assert.assertTrue(division.saveDivision(divisionDB));
        Assert.assertEquals(1, division.getDivisionID());
        Assert.assertEquals(1, division.getConferenceID());
        Assert.assertEquals("Atlantic", division.getDivisionName());
    }

    @Test
    public void loadTeamsTest() {
        IDivision division = leagueManagerFactory.getDivision();
        ITeamPersistence teamDB = leagueManagerFactory.getTeamDB();
        List<ITeam> teams = new ArrayList<>();

        division.loadTeams(teamDB, teams);
        Assert.assertEquals(1, teams.size());
    }

    @Test
    public void isNullOrEmptyTest() {
        IDivision division = leagueManagerFactory.getDivision();
        Assert.assertTrue(division.isNullOrEmpty(""));
        Assert.assertFalse(division.isNullOrEmpty("central"));
    }

    @Test
    public void isDivisionNameExistTest() {
        IDivision division = leagueManagerFactory.getDivision();
        IDivisionPersistence divisionDB = leagueManagerFactory.getDivisionDB();
        List<IDivision> divisions = new ArrayList<>();
        divisionDB.loadDivisions(1, divisions);

        Assert.assertFalse(division.isDivisionNameExist(divisions, "central"));
        Assert.assertTrue(division.isDivisionNameExist(divisions, "atlantic"));
    }

}
