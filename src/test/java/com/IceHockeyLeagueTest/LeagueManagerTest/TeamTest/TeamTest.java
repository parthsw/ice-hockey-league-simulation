package com.IceHockeyLeagueTest.LeagueManagerTest.TeamTest;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
import com.IceHockeyLeague.LeagueManager.Coach.ICoachPersistence;
import com.IceHockeyLeague.LeagueManager.Manager.IManager;
import com.IceHockeyLeague.LeagueManager.Manager.IManagerPersistence;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayerPersistence;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Team.ITeamStrength;
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
    public void loadPlayers() {
        ITeamPlayerPersistence teamPlayerDB = leagueManagerFactory.getTeamPlayerDB();
        List<ITeamPlayer> teamPlayers = new ArrayList<>();

        Assert.assertTrue(teamPlayerDB.loadTeamPlayers(1, teamPlayers));
        Assert.assertEquals(2, teamPlayers.size());

    }

    @Test
    public void calculateTeamStrengthTest() {
        ITeamStrength teamStrength = leagueManagerFactory.getTeamStrength();
        ITeam team = leagueManagerFactory.getTeam();

        ITeamPlayerPersistence teamPlayerDB = leagueManagerFactory.getTeamPlayerDB();
        List<ITeamPlayer> teamPlayers = new ArrayList<>();

        teamPlayerDB.loadTeamPlayers(1, teamPlayers);
        team.setPlayers(teamPlayers);

        Assert.assertEquals(46.5, team.calculateTeamStrength(teamStrength), 0.0);
    }

}
