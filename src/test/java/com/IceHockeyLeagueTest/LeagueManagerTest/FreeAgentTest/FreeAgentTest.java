package com.IceHockeyLeagueTest.LeagueManagerTest.FreeAgentTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.Database.IDatabaseFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.ILeaguePersistence;
import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgentPersistence;
import com.IceHockeyLeague.LeagueManager.Player.IPlayer;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerCareerProgression;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Player.PlayerPosition;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

public class FreeAgentTest {
    private static ILeagueManagerFactory leagueManagerFactory;
    private static IDatabaseFactory databaseFactory;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        databaseFactory = appFactory.createDatabaseFactory();
    }

    @Test
    public void ConstructorTest() {
        IFreeAgent freeAgent = leagueManagerFactory.createFreeAgent();
        Assert.assertEquals(-1, freeAgent.getFreeAgentId());
        Assert.assertEquals(-1, freeAgent.getLeagueId());
    }

    @Test
    public void getFreeAgentIdTest() {
        IFreeAgent freeAgent = leagueManagerFactory.createFreeAgent();
        freeAgent.setFreeAgentId(2);
        Assert.assertEquals(2, freeAgent.getFreeAgentId());
    }

    @Test
    public void setFreeAgentIdTest() {
        IFreeAgent freeAgent = leagueManagerFactory.createFreeAgent();
        freeAgent.setFreeAgentId(11);
        Assert.assertEquals(11, freeAgent.getFreeAgentId());
    }

    @Test
    public void getLeagueIdTest() {
        IFreeAgent freeAgent = leagueManagerFactory.createFreeAgent();
        freeAgent.setLeagueId(9);
        Assert.assertEquals(9, freeAgent.getLeagueId());
    }

    @Test
    public void setLeagueIdTest() {
        IFreeAgent freeAgent = leagueManagerFactory.createFreeAgent();
        freeAgent.setLeagueId(17);
        Assert.assertEquals(17, freeAgent.getLeagueId());
    }

    @Test
    public void convertToTeamPlayerTest() {
        IFreeAgentPersistence freeAgentDB = databaseFactory.createFreeAgentPersistence();
        ITeamPlayer teamPlayer = leagueManagerFactory.createTeamPlayer();
        List<IFreeAgent> freeAgents = new ArrayList<>();
        freeAgentDB.loadFreeAgents(1, freeAgents);
        IFreeAgent freeAgent = freeAgents.get(1);

        freeAgent.convertToTeamPlayer(teamPlayer);

        Assert.assertFalse(teamPlayer.isCaptain());
        Assert.assertEquals(-1, teamPlayer.getTeamId());
        Assert.assertEquals(-1, teamPlayer.getTeamPlayerId());
    }

    @Test
    public void generateFreeAgentTest() {
        IFreeAgent freeAgent = leagueManagerFactory.createFreeAgent();
        IPlayer player = leagueManagerFactory.createPlayer();
        player.setPlayerName("Joey");

        freeAgent.generateFreeAgent(player);
        Assert.assertEquals("Joey", freeAgent.getPlayerName());
    }

    @Test
    public void bestFreeAgentForPositionNotFoundTest() {
        List<IFreeAgent> freeAgents = new ArrayList<>();
        IFreeAgentPersistence freeAgentDb = databaseFactory.createFreeAgentPersistence();
        IFreeAgent freeAgent = leagueManagerFactory.createFreeAgent();

        freeAgentDb.loadFreeAgents(1, freeAgents);
        Assert.assertNull(freeAgent.bestFreeAgentForPosition(freeAgents, PlayerPosition.GOALIE.toString()));
    }

    @Test
    public void bestFreeAgentForPositionFoundTest() {
        List<IFreeAgent> freeAgents = new ArrayList<>();
        IFreeAgentPersistence freeAgentDb = databaseFactory.createFreeAgentPersistence();
        IFreeAgent freeAgent = leagueManagerFactory.createFreeAgent();
        freeAgentDb.loadFreeAgents(1, freeAgents);

        IFreeAgent bestFreeAgent = freeAgent.bestFreeAgentForPosition(freeAgents, PlayerPosition.FORWARD.toString());
        Assert.assertEquals("Fred Two", bestFreeAgent.getPlayerName());
        Assert.assertEquals(3, bestFreeAgent.getFreeAgentId());
    }

    @Test
    public void bestFreeAgentForPositionInvalidTest() {
        thrown.expect(IllegalArgumentException.class);
        IFreeAgent freeAgent = leagueManagerFactory.createFreeAgent();
        freeAgent.bestFreeAgentForPosition(null, PlayerPosition.FORWARD.toString());
    }

    @Test
    public void saveFreeAgentTest() {
        IFreeAgentPersistence freeAgentDb = databaseFactory.createFreeAgentPersistence();
        IFreeAgent freeAgent = leagueManagerFactory.createFreeAgent();

        freeAgent.saveFreeAgent(freeAgentDb);

        Assert.assertEquals(1, freeAgent.getLeagueId());
        Assert.assertEquals(1, freeAgent.getFreeAgentId());
    }

    @Test
    public void handleFreeAgentRetirementTest() {
        IPlayerCareerProgression playerCareerProgression = leagueManagerFactory.createPlayerCareerProgression(leagueManagerFactory.createRandomChance());
        ILeaguePersistence leagueDB = databaseFactory.createLeaguePersistence();
        IFreeAgent freeAgent = leagueManagerFactory.createFreeAgent();
        ILeague league = leagueManagerFactory.createLeague();
        leagueDB.loadLeague(1, league);
        Assert.assertFalse(freeAgent.handleFreeAgentRetirement(playerCareerProgression, league));
    }

}
