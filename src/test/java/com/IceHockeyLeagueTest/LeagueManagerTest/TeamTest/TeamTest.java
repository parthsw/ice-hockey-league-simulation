package com.IceHockeyLeagueTest.LeagueManagerTest.TeamTest;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
import com.IceHockeyLeague.LeagueManager.Coach.ICoachPersistence;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Manager.IManager;
import com.IceHockeyLeague.LeagueManager.Manager.IManagerPersistence;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayerPersistence;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Team.ITeamPersistence;
import com.IceHockeyLeague.LeagueManager.Team.ITeamStrengthCalculator;
import com.IceHockeyLeagueTest.LeagueManagerTest.TestLeagueManagerFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TeamTest {
    private static AbstractLeagueManagerFactory leagueManagerFactory;

    @BeforeClass
    public static void setup() {
        AbstractLeagueManagerFactory.setFactory(new TestLeagueManagerFactory());
        leagueManagerFactory = AbstractLeagueManagerFactory.getFactory();
    }

    @Test
    public void ConstructorTest() {
        ITeam team = leagueManagerFactory.getTeam();
        Assert.assertEquals(-1, team.getTeamID());
        Assert.assertEquals(-1, team.getDivisionID());
        Assert.assertEquals(0f, team.getTeamStrength(), 0.0);
        Assert.assertFalse(team.getIsUserCreated());
    }

    @Test
    public void getTeamIDTest() {
        ITeam team = leagueManagerFactory.getTeam();
        team.setTeamID(9);
        Assert.assertEquals(9, team.getTeamID());
    }

    @Test
    public void setTeamIDTest() {
        ITeam team = leagueManagerFactory.getTeam();
        team.setTeamID(5);
        Assert.assertEquals(5, team.getTeamID());
    }

    @Test
    public void getTeamNameTest() {
        ITeam team = leagueManagerFactory.getTeam();
        team.setTeamName("Halifax");
        Assert.assertEquals("Halifax", team.getTeamName());
    }

    @Test
    public void setTeamNameTest() {
        ITeam team = leagueManagerFactory.getTeam();
        team.setTeamName("Montreal");
        Assert.assertEquals("Montreal", team.getTeamName());
    }

    @Test
    public void getIsUserCreatedTest() {
        ITeam team = leagueManagerFactory.getTeam();
        team.setIsUserCreated(false);
        Assert.assertFalse(team.getIsUserCreated());
    }

    @Test
    public void setIsUserCreatedTest() {
        ITeam team = leagueManagerFactory.getTeam();
        team.setIsUserCreated(true);
        Assert.assertTrue(team.getIsUserCreated());
    }

    @Test
    public void setTeamStrengthTest() {
        ITeam team = leagueManagerFactory.getTeam();
        team.setTeamStrength(1.2f);
        Assert.assertEquals(1.2f, team.getTeamStrength(), 0.0);
    }

    @Test
    public void getTeamStrengthTest() {
        ITeam team = leagueManagerFactory.getTeam();
        team.setTeamStrength(56.7f);
        Assert.assertEquals(56.7f, team.getTeamStrength(), 0.0);
    }

    @Test
    public void getDivisionIDTest() {
        ITeam team = leagueManagerFactory.getTeam();
        team.setDivisionID(3);
        Assert.assertEquals(3, team.getDivisionID());
    }

    @Test
    public void setDivisionIDTest() {
        ITeam team = leagueManagerFactory.getTeam();
        team.setDivisionID(7);
        Assert.assertEquals(7, team.getDivisionID());
    }

    @Test
    public void getPlayerByIdTest() {
        ITeam team = leagueManagerFactory.getTeam();
        Assert.assertNull(team.getPlayerById(1));
    }

    @Test
    public void addPlayerTest() {
        ITeam team = leagueManagerFactory.getTeam();
        ITeamPlayer teamPlayer = leagueManagerFactory.getTeamPlayer();
        teamPlayer.setTeamPlayerID(1);

        team.addPlayer(teamPlayer);
        Assert.assertEquals(1, team.getPlayers().size());
    }

    @Test
    public void removePlayerTest() {
        ITeam team = leagueManagerFactory.getTeam();
        ITeamPlayerPersistence teamPlayerDB = leagueManagerFactory.getTeamPlayerDB();
        List<ITeamPlayer> teamPlayers = new ArrayList<>();
        teamPlayerDB.loadTeamPlayers(1, teamPlayers);
        team.setPlayers(teamPlayers);

        team.removePlayer(teamPlayers.get(0));
        List<ITeamPlayer> players = team.getPlayers();
        Assert.assertEquals(1, players.size());

        List<ITeamPlayer> emptyTeamPlayers = new ArrayList<>();
        team.setPlayers(emptyTeamPlayers);
        ITeamPlayer playerThatNotExist = leagueManagerFactory.getTeamPlayer();
        Assert.assertFalse(team.removePlayer(playerThatNotExist));
    }

    @Test
    public void getPlayersTest() {
        ITeam team = leagueManagerFactory.getTeam();
        List<ITeamPlayer> teamPlayers = new ArrayList<>();

        ITeamPlayerPersistence teamPlayerDB = leagueManagerFactory.getTeamPlayerDB();
        teamPlayerDB.loadTeamPlayers(1, teamPlayers);

        team.setPlayers(teamPlayers);
        Assert.assertEquals(2, team.getPlayers().size());

    }

    @Test
    public void setPlayersTest() {
        ITeam team = leagueManagerFactory.getTeam();
        List<ITeamPlayer> teamPlayers = new ArrayList<>();

        ITeamPlayerPersistence teamPlayerDB = leagueManagerFactory.getTeamPlayerDB();
        teamPlayerDB.loadTeamPlayers(1, teamPlayers);

        team.setPlayers(teamPlayers);
        Assert.assertEquals(2, team.getPlayers().size());
    }

    @Test
    public void getCoachTest() {
        ITeam team = leagueManagerFactory.getTeam();
        ICoach coach = leagueManagerFactory.getCoach();

        ICoachPersistence coachDB = leagueManagerFactory.getCoachDB();
        coachDB.loadTeamCoach(1, coach);

        Assert.assertNull(team.getCoach());
        team.setCoach(coach);
        Assert.assertNotNull(team.getCoach());
    }

    @Test
    public void setCoachTest() {
        ITeam team = leagueManagerFactory.getTeam();
        ICoach coach = leagueManagerFactory.getCoach();

        ICoachPersistence coachDB = leagueManagerFactory.getCoachDB();
        coachDB.loadTeamCoach(1, coach);

        Assert.assertNull(team.getCoach());
        team.setCoach(coach);
        Assert.assertNotNull(team.getCoach());
    }

    @Test
    public void getManagerTest() {
        ITeam team = leagueManagerFactory.getTeam();
        IManager manager = leagueManagerFactory.getManager();

        IManagerPersistence managerDB = leagueManagerFactory.getManagerDB();
        managerDB.loadTeamManager(1, manager);

        Assert.assertNull(team.getManager());
        team.setManager(manager);
        Assert.assertNotNull(team.getManager());
    }

    @Test
    public void setManagerTest() {
        ITeam team = leagueManagerFactory.getTeam();
        IManager manager = leagueManagerFactory.getManager();

        IManagerPersistence managerDB = leagueManagerFactory.getManagerDB();
        managerDB.loadTeamManager(1, manager);

        Assert.assertNull(team.getManager());
        team.setManager(manager);
        Assert.assertNotNull(team.getManager());
    }

    @Test
    public void checkTeamPlayersTest() {
        ITeam team = leagueManagerFactory.getTeam();
        Assert.assertFalse(team.checkTeamPlayers());
    }

    @Test
    public void saveTeamTest() {
        ITeam team = leagueManagerFactory.getTeam();
        ITeamPersistence teamDB = leagueManagerFactory.getTeamDB();
        team.saveTeam(teamDB);

        Assert.assertEquals("Halifax", team.getTeamName());
        Assert.assertEquals(89.5f, team.getTeamStrength(), 0.0);
    }

    @Test
    public void loadPlayersTest() {
        ITeamPlayerPersistence teamPlayerDB = leagueManagerFactory.getTeamPlayerDB();
        List<ITeamPlayer> teamPlayers = new ArrayList<>();
        ITeam team = leagueManagerFactory.getTeam();

        Assert.assertTrue(team.loadPlayers(teamPlayerDB, teamPlayers));
        Assert.assertEquals(2, teamPlayers.size());
    }

    @Test
    public void checkIfTeamNameExistsTest() {
        ITeamPersistence teamDB = leagueManagerFactory.getTeamDB();
        List<ILeague> leagues = new ArrayList<>();
        ITeam team = leagueManagerFactory.getTeam();

        team.checkIfTeamNameExists(teamDB, "Halifax", leagues);
        Assert.assertEquals(2, leagues.size());
    }

    @Test
    public void isNullOrEmptyTest() {
        ITeam team = leagueManagerFactory.getTeam();

        Assert.assertTrue(team.isNullOrEmpty(""));
        Assert.assertFalse(team.isNullOrEmpty("Halifax"));
    }

    @Test
    public void isTeamNameExistTest() {
        ITeamPersistence teamDB = leagueManagerFactory.getTeamDB();
        List<ITeam> teams = new ArrayList<>();
        teamDB.loadTeams(1, teams);

        ITeam team = leagueManagerFactory.getTeam();
        team.setTeamName("Boston");
        Assert.assertTrue(team.isTeamNameExist(teams));

        team.setTeamName("Halifax");
        Assert.assertFalse(team.isTeamNameExist(teams));
    }

    @Test
    public void calculateTeamStrengthTest() {
        ITeamStrengthCalculator teamStrength = leagueManagerFactory.getTeamStrengthCalculator();
        ITeam team = leagueManagerFactory.getTeam();

        ITeamPlayerPersistence teamPlayerDB = leagueManagerFactory.getTeamPlayerDB();
        List<ITeamPlayer> teamPlayers = new ArrayList<>();

        teamPlayerDB.loadTeamPlayers(1, teamPlayers);
        team.setPlayers(teamPlayers);

        Assert.assertEquals(46.5f, team.calculateTeamStrength(teamStrength), 0.0);
    }

}
