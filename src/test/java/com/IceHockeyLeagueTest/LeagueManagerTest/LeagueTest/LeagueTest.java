package com.IceHockeyLeagueTest.LeagueManagerTest.LeagueTest;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
import com.IceHockeyLeague.LeagueManager.Coach.ICoachPersistence;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Conference.IConferencePersistence;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IGamePlayConfig;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IGamePlayConfigPersistence;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.ILeaguePersistence;
import com.IceHockeyLeague.LeagueManager.Manager.IManager;
import com.IceHockeyLeague.LeagueManager.Manager.IManagerPersistence;
import com.IceHockeyLeague.LeagueManager.Player.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.IFreeAgentPersistence;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeagueTest.LeagueManagerTest.TestLeagueManagerFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LeagueTest {
    private static AbstractLeagueManagerFactory leagueManagerFactory;

    @BeforeClass
    public static void setup() {
        AbstractLeagueManagerFactory.setFactory(new TestLeagueManagerFactory());
        leagueManagerFactory = AbstractLeagueManagerFactory.getFactory();
    }

    @Test
    public void ConstructorTest() {
        ILeague league = leagueManagerFactory.getLeague();
        Assert.assertEquals(-1, league.getLeagueID());
    }

    @Test
    public void getLeagueIDTest() {
        ILeague league = leagueManagerFactory.getLeague();
        league.setLeagueID(13);
        Assert.assertEquals(13, league.getLeagueID());
    }

    @Test
    public void setLeagueIDTest() {
        ILeague league = leagueManagerFactory.getLeague();
        league.setLeagueID(1);
        Assert.assertEquals(1, league.getLeagueID());
    }

    @Test
    public void getLeagueNameTest() {
        ILeague league = leagueManagerFactory.getLeague();
        league.setLeagueName("Dalhousie Hockey League");
        Assert.assertEquals("Dalhousie Hockey League", league.getLeagueName());
    }

    @Test
    public void setLeagueNameTest() {
        ILeague league = leagueManagerFactory.getLeague();
        league.setLeagueName("NHL");
        Assert.assertEquals("NHL", league.getLeagueName());
    }

    @Test
    public void setGamePlayConfigTest() {
        IGamePlayConfigPersistence gamePlayConfigDB = leagueManagerFactory.getGamePlayConfigDB();
        ILeague league = leagueManagerFactory.getLeague();
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.getGamePlayConfig();
        gamePlayConfigDB.loadGamePlayConfig(1, gamePlayConfig);
        league.setGamePlayConfig(gamePlayConfig);

        IGamePlayConfig leagueConfig = league.getGamePlayConfig();
        Assert.assertEquals(1, leagueConfig.getLeagueID());
    }

    @Test
    public void getConferenceByIdTest() {
        ILeague league = leagueManagerFactory.getLeague();
        Assert.assertNull(league.getConferenceById(1));
    }

    @Test
    public void addConferenceTest() {
        ILeague league = leagueManagerFactory.getLeague();
        IConference conference = leagueManagerFactory.getConference();
        league.addConference(conference);

        List<IConference> leagueConferences = league.getConferences();
        Assert.assertEquals(1, leagueConferences.size());
    }

    @Test
    public void setConferencesTest() {
        ILeague league = leagueManagerFactory.getLeague();
        IConferencePersistence conferenceDB = leagueManagerFactory.getConferenceDB();
        List<IConference> conferences = new ArrayList<>();
        league.loadConferences(conferenceDB, conferences);
        league.setConferences(conferences);

        List<IConference> leagueConferences = league.getConferences();
        Assert.assertEquals(2, leagueConferences.size());
    }

    @Test
    public void getFreeAgentByIdTest() {
        ILeague league = leagueManagerFactory.getLeague();
        Assert.assertNull(league.getFreeAgentById(0));
    }

    @Test
    public void addFreeAgentTest() {
        ILeague league = leagueManagerFactory.getLeague();
        IFreeAgent freeAgent = leagueManagerFactory.getFreeAgent();
        league.addFreeAgent(freeAgent);
        List<IFreeAgent> leagueFreeAgents = league.getFreeAgents();
        Assert.assertEquals(1, leagueFreeAgents.size());
    }

    @Test
    public void removeFreeAgentTest() {
        ILeague league = leagueManagerFactory.getLeague();
        IFreeAgentPersistence freeAgentPersistence = leagueManagerFactory.getFreeAgentDB();
        List<IFreeAgent> freeAgents = new ArrayList<>();

        league.loadLeagueFreeAgents(freeAgentPersistence, freeAgents);
        league.setFreeAgents(freeAgents);
        league.removeFreeAgent(freeAgents.get(0));

        List<IFreeAgent> leagueFreeAgents = league.getFreeAgents();
        Assert.assertEquals(1, leagueFreeAgents.size());

        List<IFreeAgent> emptyList = new ArrayList<>();
        league.setFreeAgents(emptyList);
        IFreeAgent agentThatNotExist = leagueManagerFactory.getFreeAgent();
        Assert.assertFalse(league.removeFreeAgent(agentThatNotExist));
    }

    @Test
    public void getCoachByIdTest() {
        ILeague league = leagueManagerFactory.getLeague();
        Assert.assertNull(league.getCoachById(0));
    }

    @Test
    public void addCoachTest() {
        ILeague league = leagueManagerFactory.getLeague();
        ICoach coach = leagueManagerFactory.getCoach();
        league.addCoach(coach);

        List<ICoach> leagueCoaches = league.getCoaches();
        Assert.assertEquals(1, leagueCoaches.size());
    }

    @Test
    public void getManagerByIdTest() {
        ILeague league = leagueManagerFactory.getLeague();
        Assert.assertNull(league.getManagerById(0));
    }

    @Test
    public void addManagerTest() {
        ILeague league = leagueManagerFactory.getLeague();
        IManager manager = leagueManagerFactory.getManager();
        league.addManager(manager);

        List<IManager> leagueManagers = league.getManagers();
        Assert.assertEquals(1, leagueManagers.size());
    }

    @Test
    public void addRetiredTeamPlayerTest() {
        ILeague league = leagueManagerFactory.getLeague();
        ITeamPlayer retiredTeamPlayer = leagueManagerFactory.getTeamPlayer();
        league.addRetiredTeamPlayer(retiredTeamPlayer);

        List<ITeamPlayer> retiredTeamPlayers = league.getRetiredTeamPlayers();
        Assert.assertEquals(1, retiredTeamPlayers.size());
    }

    @Test
    public void addRetiredFreeAgentsTest() {
        ILeague league = leagueManagerFactory.getLeague();
        IFreeAgent freeAgent = leagueManagerFactory.getFreeAgent();
        league.addRetiredFreeAgent(freeAgent);

        List<IFreeAgent> retiredFreeAgents = league.getRetiredFreeAgents();
        Assert.assertEquals(1, retiredFreeAgents.size());
    }

    @Test
    public void saveCompleteLeagueTest() {
        ILeague league = leagueManagerFactory.getLeague();
        league.saveCompleteLeague();

        Assert.assertEquals(2, league.getLeagueID());
        Assert.assertEquals("NHL", league.getLeagueName());

        List<IConference> conferences = league.getConferences();
        Assert.assertEquals(2, conferences.size());

        List<IFreeAgent> freeAgents = league.getFreeAgents();
        Assert.assertEquals(3, freeAgents.size());

        List<ICoach> coaches = league.getCoaches();
        Assert.assertEquals(3, coaches.size());

        List<IManager> managers = league.getManagers();
        Assert.assertEquals(3, managers.size());
    }

    @Test
    public void loadCompleteLeagueTest() {
        ILeague league = leagueManagerFactory.getLeague();
        league.loadCompleteLeague(1);

        Assert.assertEquals(1, league.getLeagueID());
        Assert.assertEquals("DHL", league.getLeagueName());

        List<IFreeAgent> freeAgents = league.getFreeAgents();
        Assert.assertEquals(2, freeAgents.size());

        List<ICoach> coaches = league.getCoaches();
        Assert.assertEquals(2, coaches.size());

        List<IManager> managers = league.getManagers();
        Assert.assertEquals(3, managers.size());
    }

    @Test
    public void saveLeagueTest() {
        ILeague league = leagueManagerFactory.getLeague();
        ILeaguePersistence leagueDB = leagueManagerFactory.getLeagueDB();
        league.saveLeague(leagueDB);

        Assert.assertEquals(2, league.getLeagueID());
        Assert.assertEquals("NHL", league.getLeagueName());
    }

    @Test
    public void loadLeagueTest() {
        ILeague league = leagueManagerFactory.getLeague();
        ILeaguePersistence leagueDB = leagueManagerFactory.getLeagueDB();
        league.setLeagueID(1);
        league.loadLeague(leagueDB);

        Assert.assertEquals(1, league.getLeagueID());
        Assert.assertEquals("DHL", league.getLeagueName());
    }

    @Test
    public void loadConferencesTest() {
        ILeague league = leagueManagerFactory.getLeague();
        IConferencePersistence conferenceDB = leagueManagerFactory.getConferenceDB();
        List<IConference> conferences = new ArrayList<>();
        league.loadConferences(conferenceDB, conferences);

        Assert.assertEquals(2, conferences.size());
    }

    @Test
    public void loadLeagueManagersTest() {
        ILeague league = leagueManagerFactory.getLeague();
        IManagerPersistence managerDB = leagueManagerFactory.getManagerDB();
        List<IManager> managers = new ArrayList<>();
        league.loadLeagueManagers(managerDB, managers);

        Assert.assertEquals(3, managers.size());
    }

    @Test
    public void loadLeagueCoachesTest() {
        ILeague league = leagueManagerFactory.getLeague();
        ICoachPersistence coachDB = leagueManagerFactory.getCoachDB();
        List<ICoach> coaches = new ArrayList<>();
        league.loadLeagueCoaches(coachDB, coaches);

        Assert.assertEquals(2, coaches.size());
    }

    @Test
    public void loadLeagueFreeAgentsTest() {
        ILeague league = leagueManagerFactory.getLeague();
        IFreeAgentPersistence freeAgentDB = leagueManagerFactory.getFreeAgentDB();
        List<IFreeAgent> freeAgents = new ArrayList<>();
        league.loadLeagueFreeAgents(freeAgentDB, freeAgents);

        Assert.assertEquals(2, freeAgents.size());
    }
}
