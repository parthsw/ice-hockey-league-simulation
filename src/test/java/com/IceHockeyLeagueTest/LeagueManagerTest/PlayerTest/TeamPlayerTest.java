package com.IceHockeyLeagueTest.LeagueManagerTest.PlayerTest;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.LeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerCareerProgression;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayerPersistence;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeagueTest.LeagueManagerTest.TestLeagueManagerFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TeamPlayerTest {
    private static AbstractLeagueManagerFactory leagueManagerFactory;

    @BeforeClass
    public static void setup() {
        LeagueManagerFactory.setFactory(new TestLeagueManagerFactory());
        leagueManagerFactory = AbstractLeagueManagerFactory.getFactory();
    }

    @Test
    public void ConstructorTest() {
        ITeamPlayer teamPlayer = leagueManagerFactory.getTeamPlayer();
        Assert.assertEquals(-1, teamPlayer.getTeamID());
        Assert.assertEquals(-1, teamPlayer.getTeamPlayerID());
    }

    @Test
    public void getTeamPlayerIDTest() {
        ITeamPlayer teamPlayer = leagueManagerFactory.getTeamPlayer();
        teamPlayer.setTeamPlayerID(2);
        Assert.assertEquals(2, teamPlayer.getTeamPlayerID());
    }

    @Test
    public void setTeamPlayerIDTest() {
        ITeamPlayer teamPlayer = leagueManagerFactory.getTeamPlayer();
        teamPlayer.setTeamPlayerID(9);
        Assert.assertEquals(9, teamPlayer.getTeamPlayerID());
    }

    @Test
    public void isCaptainTest() {
        ITeamPlayer teamPlayer = leagueManagerFactory.getTeamPlayer();
        teamPlayer.setIsCaptain(true);
        Assert.assertTrue(teamPlayer.isCaptain());
    }

    @Test
    public void setIsCaptainTest() {
        ITeamPlayer teamPlayer = leagueManagerFactory.getTeamPlayer();
        teamPlayer.setIsCaptain(false);
        Assert.assertFalse(teamPlayer.isCaptain());
    }

    @Test
    public void getTeamIDTest() {
        ITeamPlayer teamPlayer = leagueManagerFactory.getTeamPlayer();
        teamPlayer.setTeamID(11);
        Assert.assertEquals(11, teamPlayer.getTeamID());
    }

    @Test
    public void setTeamIDTest() {
        ITeamPlayer teamPlayer = leagueManagerFactory.getTeamPlayer();
        teamPlayer.setTeamID(5);
        Assert.assertEquals(5, teamPlayer.getTeamID());
    }

    @Test
    public void convertToFreeAgentTest() {
        ITeamPlayerPersistence teamPlayerDB = leagueManagerFactory.getTeamPlayerDB();
        List<ITeamPlayer> teamPlayers = new ArrayList<>();
        teamPlayerDB.loadTeamPlayers(1, teamPlayers);
        ITeamPlayer teamPlayer = teamPlayers.get(0);
        IFreeAgent freeAgent = leagueManagerFactory.getFreeAgent();

        teamPlayer.convertToFreeAgent(freeAgent);

        Assert.assertEquals(-1, freeAgent.getLeagueID());
        Assert.assertEquals("Fred One", freeAgent.getPlayerName());
    }

    @Test
    public void saveTeamPlayerTest() {
        ITeamPlayerPersistence teamPlayerDB = leagueManagerFactory.getTeamPlayerDB();
        ITeamPlayer teamPlayer = leagueManagerFactory.getTeamPlayer();
        teamPlayer.saveTeamPlayer(teamPlayerDB);

        Assert.assertEquals(1, teamPlayer.getTeamPlayerID());
        Assert.assertEquals(1, teamPlayer.getTeamID());
    }

    @Test
    public void handleTeamPlayerRetirementTest() {
        IPlayerCareerProgression playerCareerProgression = leagueManagerFactory.getPlayerCareerProgression();
        ITeam team = leagueManagerFactory.getTeam();
        ILeague league = leagueManagerFactory.getLeague();
        ITeamPlayer player = leagueManagerFactory.getTeamPlayer();

        Assert.assertFalse(player.handleTeamPlayerRetirement(playerCareerProgression, team, league));
    }
}
