package com.IceHockeyLeagueTest.LeagueManagerTest.PlayerTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
//import com.Database.IDatabaseFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Player.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerCareerProgression;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
//import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayerPersistence;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TeamPlayerTest {
    private static ILeagueManagerFactory leagueManagerFactory;
    //private static IDatabaseFactory databaseFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
        //databaseFactory = appFactory.createDatabaseFactory();
    }

    @Test
    public void ConstructorTest() {
        ITeamPlayer teamPlayer = leagueManagerFactory.createTeamPlayer();
        Assert.assertEquals(-1, teamPlayer.getTeamID());
        Assert.assertEquals(-1, teamPlayer.getTeamPlayerID());
    }

    @Test
    public void getTeamPlayerIDTest() {
        ITeamPlayer teamPlayer = leagueManagerFactory.createTeamPlayer();
        teamPlayer.setTeamPlayerID(2);
        Assert.assertEquals(2, teamPlayer.getTeamPlayerID());
    }

    @Test
    public void setTeamPlayerIDTest() {
        ITeamPlayer teamPlayer = leagueManagerFactory.createTeamPlayer();
        teamPlayer.setTeamPlayerID(9);
        Assert.assertEquals(9, teamPlayer.getTeamPlayerID());
    }

    @Test
    public void isCaptainTest() {
        ITeamPlayer teamPlayer = leagueManagerFactory.createTeamPlayer();
        teamPlayer.setIsCaptain(true);
        Assert.assertTrue(teamPlayer.isCaptain());
    }

    @Test
    public void setIsCaptainTest() {
        ITeamPlayer teamPlayer = leagueManagerFactory.createTeamPlayer();
        teamPlayer.setIsCaptain(false);
        Assert.assertFalse(teamPlayer.isCaptain());
    }

    @Test
    public void getTeamIDTest() {
        ITeamPlayer teamPlayer = leagueManagerFactory.createTeamPlayer();
        teamPlayer.setTeamID(11);
        Assert.assertEquals(11, teamPlayer.getTeamID());
    }

    @Test
    public void setTeamIDTest() {
        ITeamPlayer teamPlayer = leagueManagerFactory.createTeamPlayer();
        teamPlayer.setTeamID(5);
        Assert.assertEquals(5, teamPlayer.getTeamID());
    }

    @Test
    public void convertToFreeAgentTest() {
       // ITeamPlayerPersistence teamPlayerDB = databaseFactory.createTeamPlayerPersistence();
        List<ITeamPlayer> teamPlayers = new ArrayList<>();
        //teamPlayerDB.loadTeamPlayers(1, teamPlayers);
        ITeamPlayer teamPlayer = teamPlayers.get(0);
        IFreeAgent freeAgent = leagueManagerFactory.createFreeAgent();

        teamPlayer.convertToFreeAgent(freeAgent);

        Assert.assertEquals(-1, freeAgent.getLeagueID());
        Assert.assertEquals("Fred One", freeAgent.getPlayerName());
    }

    @Test
    public void saveTeamPlayerTest() {
        //ITeamPlayerPersistence teamPlayerDB = databaseFactory.createTeamPlayerPersistence();
        ITeamPlayer teamPlayer = leagueManagerFactory.createTeamPlayer();
        //teamPlayer.saveTeamPlayer(teamPlayerDB);

        Assert.assertEquals(1, teamPlayer.getTeamPlayerID());
        Assert.assertEquals(1, teamPlayer.getTeamID());
    }

    @Test
    public void handleTeamPlayerRetirementTest() {
        IPlayerCareerProgression playerCareerProgression = leagueManagerFactory.createPlayerCareerProgression(leagueManagerFactory.createRandomChance());
        ITeam team = leagueManagerFactory.createTeam();
        ILeague league = leagueManagerFactory.createLeague();
        ITeamPlayer player = leagueManagerFactory.createTeamPlayer();

        Assert.assertFalse(player.handleTeamPlayerRetirement(playerCareerProgression, team, league));
    }
}
