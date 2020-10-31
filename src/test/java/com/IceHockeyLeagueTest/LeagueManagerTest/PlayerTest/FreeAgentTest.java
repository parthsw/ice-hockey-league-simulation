package com.IceHockeyLeagueTest.LeagueManagerTest.PlayerTest;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.ILeaguePersistence;
import com.IceHockeyLeague.LeagueManager.LeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.IFreeAgentPersistence;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerCareerProgression;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeagueTest.LeagueManagerTest.TestLeagueManagerFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FreeAgentTest {
    private AbstractLeagueManagerFactory leagueManagerFactory;

    @Before
    public void setup() {
        LeagueManagerFactory.setFactory(new TestLeagueManagerFactory());
        leagueManagerFactory = AbstractLeagueManagerFactory.getFactory();
    }

    @Test
    public void ConstructorTest() {
        IFreeAgent freeAgent = leagueManagerFactory.getFreeAgent();
        Assert.assertEquals(-1, freeAgent.getFreeAgentID());
        Assert.assertEquals(-1, freeAgent.getLeagueID());
    }

    @Test
    public void getFreeAgentIDTest() {
        IFreeAgent freeAgent = leagueManagerFactory.getFreeAgent();
        freeAgent.setFreeAgentID(2);
        Assert.assertEquals(2, freeAgent.getFreeAgentID());
    }

    @Test
    public void setFreeAgentID() {
        IFreeAgent freeAgent = leagueManagerFactory.getFreeAgent();
        freeAgent.setFreeAgentID(11);
        Assert.assertEquals(11, freeAgent.getFreeAgentID());
    }

    @Test
    public void getLeagueIDTest() {
        IFreeAgent freeAgent = leagueManagerFactory.getFreeAgent();
        freeAgent.setLeagueID(9);
        Assert.assertEquals(9, freeAgent.getLeagueID());
    }

    @Test
    public void setLeagueIDTest() {
        IFreeAgent freeAgent = leagueManagerFactory.getFreeAgent();
        freeAgent.setLeagueID(17);
        Assert.assertEquals(17, freeAgent.getLeagueID());
    }

    @Test
    public void isValidTest() {
        IFreeAgent freeAgent = leagueManagerFactory.getFreeAgent();
        Assert.assertFalse(freeAgent.isValid());
    }

    @Test
    public void convertToTeamPlayerTest() {
        IFreeAgentPersistence freeAgentDB = leagueManagerFactory.getFreeAgentDB();
        List<IFreeAgent> freeAgents = new ArrayList<>();
        freeAgentDB.loadFreeAgents(1, freeAgents);

        IFreeAgent freeAgent = freeAgents.get(1);
        ITeamPlayer teamPlayer = leagueManagerFactory.getTeamPlayer();

        freeAgent.convertToTeamPlayer(teamPlayer);

        Assert.assertFalse(teamPlayer.isCaptain());
        Assert.assertEquals(-1, teamPlayer.getTeamID());
        Assert.assertEquals(-1, teamPlayer.getTeamPlayerID());
    }

    @Test
    public void saveFreeAgentTest() {
        IFreeAgentPersistence freeAgentDB = leagueManagerFactory.getFreeAgentDB();
        IFreeAgent freeAgent = leagueManagerFactory.getFreeAgent();

        freeAgent.saveFreeAgent(freeAgentDB);

        Assert.assertEquals(1, freeAgent.getLeagueID());
        Assert.assertEquals(1, freeAgent.getFreeAgentID());
    }

    @Test
    public void handleFreeAgentRetirementTest() {
        IPlayerCareerProgression playerCareerProgression = leagueManagerFactory.getPlayerCareerProgression();
        ILeaguePersistence leagueDB = leagueManagerFactory.getLeagueDB();
        IFreeAgent freeAgent = leagueManagerFactory.getFreeAgent();
        ILeague league = leagueManagerFactory.getLeague();
        leagueDB.loadLeague(1, league);

        Assert.assertFalse(freeAgent.handleFreeAgentRetirement(playerCareerProgression, league));
    }
}
