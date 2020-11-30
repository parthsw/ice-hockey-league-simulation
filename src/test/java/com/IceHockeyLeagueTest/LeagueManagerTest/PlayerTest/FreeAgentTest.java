package com.IceHockeyLeagueTest.LeagueManagerTest.PlayerTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
//import com.Database.IDatabaseFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
//import com.IceHockeyLeague.LeagueManager.League.ILeaguePersistence;
import com.IceHockeyLeague.LeagueManager.Player.IFreeAgent;
//import com.IceHockeyLeague.LeagueManager.Player.IFreeAgentPersistence;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerCareerProgression;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.PersistenceTest.FreeAgentPersistenceMock;
import com.PersistenceTest.PersistenceFactoryTest;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FreeAgentTest {
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
        FreeAgentPersistenceMock freeAgentPersistenceMock = persistenceFactory.createFreeAgentPersistence();
        List<IFreeAgent> freeAgents = new ArrayList<>();
        freeAgentPersistenceMock.loadFreeAgents(1,freeAgents);
        IFreeAgent freeAgent = freeAgents.get(1);
        ITeamPlayer teamPlayer = leagueManagerFactory.createTeamPlayer();
        freeAgent.convertToTeamPlayer(teamPlayer);
        Assert.assertFalse(teamPlayer.isCaptain());
        Assert.assertEquals(-1, teamPlayer.getTeamID());
        Assert.assertEquals(-1, teamPlayer.getTeamPlayerID());
    }

    @Test
    public void bestFreeAgentForPosition() {
        List<IFreeAgent> freeAgents = new ArrayList<>();
        FreeAgentPersistenceMock freeAgentPersistenceMock = persistenceFactory.createFreeAgentPersistence();
        IFreeAgent freeAgent = leagueManagerFactory.createFreeAgent();
        freeAgentPersistenceMock.loadFreeAgents(1,freeAgents);
        Assert.assertNull(freeAgent.bestFreeAgentForPosition(freeAgents, "goalie"));
        IFreeAgent bestFreeAgent = freeAgent.bestFreeAgentForPosition(freeAgents, "forward");
        Assert.assertEquals("Fred Two", bestFreeAgent.getPlayerName());
        Assert.assertEquals(3, bestFreeAgent.getFreeAgentID());
    }

    @Test
    public void handleFreeAgentRetirementTest() {
        IPlayerCareerProgression playerCareerProgression = leagueManagerFactory.createPlayerCareerProgression(leagueManagerFactory.createRandomChance());
       // ILeaguePersistence leagueDB = databaseFactory.createLeaguePersistence();
        IFreeAgent freeAgent = leagueManagerFactory.createFreeAgent();
        ILeague league = leagueManagerFactory.createLeague();
        //leagueDB.loadLeague(1, league);

        //Assert.assertFalse(freeAgent.handleFreeAgentRetirement(playerCareerProgression, league));
    }
}
