package com.IceHockeyLeagueTest.LeagueManagerTest.PlayerTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.IPlayer;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerCareerProgression;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.PersistenceTest.PersistenceFactoryTest;
import com.PersistenceTest.TeamPlayerPersistenceMock;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TeamPlayerTest {
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
        ITeamPlayer teamPlayer = leagueManagerFactory.createTeamPlayer();
        Assert.assertEquals(-1, teamPlayer.getTeamId());
        Assert.assertEquals(-1, teamPlayer.getTeamPlayerId());
    }

    @Test
    public void getTeamPlayerIdTest() {
        ITeamPlayer teamPlayer = leagueManagerFactory.createTeamPlayer();
        teamPlayer.setTeamPlayerId(2);
        Assert.assertEquals(2, teamPlayer.getTeamPlayerId());
    }

    @Test
    public void setTeamPlayerIdTest() {
        ITeamPlayer teamPlayer = leagueManagerFactory.createTeamPlayer();
        teamPlayer.setTeamPlayerId(9);
        Assert.assertEquals(9, teamPlayer.getTeamPlayerId());
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
    public void getTeamIdTest() {
        ITeamPlayer teamPlayer = leagueManagerFactory.createTeamPlayer();
        teamPlayer.setTeamId(11);
        Assert.assertEquals(11, teamPlayer.getTeamId());
    }

    @Test
    public void setTeamIdTest() {
        ITeamPlayer teamPlayer = leagueManagerFactory.createTeamPlayer();
        teamPlayer.setTeamId(5);
        Assert.assertEquals(5, teamPlayer.getTeamId());
    }

    @Test
    public void convertToFreeAgentTest() {
        ITeamPlayerPersistence teamPlayerDb = databaseFactory.createTeamPlayerPersistence();
        IFreeAgent freeAgent = leagueManagerFactory.createFreeAgent();
        List<ITeamPlayer> teamPlayers = new ArrayList<>();
        ITeamPlayer teamPlayer;
        teamPlayerDb.loadTeamPlayers(1, teamPlayers);
        teamPlayer = teamPlayers.get(0);

        teamPlayer.convertToFreeAgent(freeAgent);

        Assert.assertEquals(-1, freeAgent.getLeagueId());
        Assert.assertEquals("Fred One", freeAgent.getPlayerName());
    }

    @Test
    public void generateTeamPlayerTest() {
        ITeamPlayer teamPlayer = leagueManagerFactory.createTeamPlayer();
        IPlayer player = leagueManagerFactory.createPlayer();
        player.setPlayerName("Rhea");

        teamPlayer.generateTeamPlayer(player);
        Assert.assertEquals("Rhea", teamPlayer.getPlayerName());
    }

    @Test
    public void saveTeamPlayerTest() {
        ITeamPlayerPersistence teamPlayerDb = databaseFactory.createTeamPlayerPersistence();
        ITeamPlayer teamPlayer = leagueManagerFactory.createTeamPlayer();
        teamPlayer.saveTeamPlayer(teamPlayerDb);

        Assert.assertEquals(1, teamPlayer.getTeamPlayerId());
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
