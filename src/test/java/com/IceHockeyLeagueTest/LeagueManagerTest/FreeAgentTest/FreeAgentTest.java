package com.IceHockeyLeagueTest.LeagueManagerTest.FreeAgentTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.Database.IDatabaseFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.ILeaguePersistence;
import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgentPersistence;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerCareerProgression;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FreeAgentTest {
    private static ILeagueManagerFactory leagueManagerFactory;
    private static IDatabaseFactory databaseFactory;

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
        Assert.assertEquals(-1, freeAgent.getFreeAgentID());
        Assert.assertEquals(-1, freeAgent.getLeagueID());
    }

    @Test
    public void getFreeAgentIDTest() {
        IFreeAgent freeAgent = leagueManagerFactory.createFreeAgent();
        freeAgent.setFreeAgentID(2);
        Assert.assertEquals(2, freeAgent.getFreeAgentID());
    }

    @Test
    public void setFreeAgentID() {
        IFreeAgent freeAgent = leagueManagerFactory.createFreeAgent();
        freeAgent.setFreeAgentID(11);
        Assert.assertEquals(11, freeAgent.getFreeAgentID());
    }

    @Test
    public void getLeagueIDTest() {
        IFreeAgent freeAgent = leagueManagerFactory.createFreeAgent();
        freeAgent.setLeagueID(9);
        Assert.assertEquals(9, freeAgent.getLeagueID());
    }

    @Test
    public void setLeagueIDTest() {
        IFreeAgent freeAgent = leagueManagerFactory.createFreeAgent();
        freeAgent.setLeagueID(17);
        Assert.assertEquals(17, freeAgent.getLeagueID());
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
        Assert.assertEquals(-1, teamPlayer.getTeamID());
        Assert.assertEquals(-1, teamPlayer.getTeamPlayerID());
    }

    @Test
    public void bestFreeAgentForPositionNotFoundTest() {
        List<IFreeAgent> freeAgents = new ArrayList<>();
        IFreeAgentPersistence freeAgentDB = databaseFactory.createFreeAgentPersistence();
        IFreeAgent freeAgent = leagueManagerFactory.createFreeAgent();

        freeAgentDB.loadFreeAgents(1, freeAgents);
        Assert.assertNull(freeAgent.bestFreeAgentForPosition(freeAgents, "goalie"));
    }

    @Test
    public void bestFreeAgentForPositionFoundTest() {
        List<IFreeAgent> freeAgents = new ArrayList<>();
        IFreeAgentPersistence freeAgentDB = databaseFactory.createFreeAgentPersistence();
        IFreeAgent freeAgent = leagueManagerFactory.createFreeAgent();
        freeAgentDB.loadFreeAgents(1, freeAgents);

        IFreeAgent bestFreeAgent = freeAgent.bestFreeAgentForPosition(freeAgents, "forward");
        Assert.assertEquals("Fred Two", bestFreeAgent.getPlayerName());
        Assert.assertEquals(3, bestFreeAgent.getFreeAgentID());
    }

    @Test
    public void saveFreeAgentTest() {
        IFreeAgentPersistence freeAgentDB = databaseFactory.createFreeAgentPersistence();
        IFreeAgent freeAgent = leagueManagerFactory.createFreeAgent();

        freeAgent.saveFreeAgent(freeAgentDB);

        Assert.assertEquals(1, freeAgent.getLeagueID());
        Assert.assertEquals(1, freeAgent.getFreeAgentID());
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
