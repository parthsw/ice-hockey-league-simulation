package com.IceHockeyLeagueTest.LeagueManagerTest.LeagueTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
//import com.Database.IDatabaseFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
//import com.IceHockeyLeague.LeagueManager.Coach.ICoachPersistence;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
//import com.IceHockeyLeague.LeagueManager.Conference.IConferencePersistence;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IGamePlayConfig;
//import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IGamePlayConfigPersistence;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
//import com.IceHockeyLeague.LeagueManager.League.ILeaguePersistence;
import com.IceHockeyLeague.LeagueManager.Manager.IManager;
//import com.IceHockeyLeague.LeagueManager.Manager.IManagerPersistence;
import com.IceHockeyLeague.LeagueManager.Player.IFreeAgent;
//import com.IceHockeyLeague.LeagueManager.Player.IFreeAgentPersistence;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LeagueTest {
    private static ILeagueManagerFactory leagueManagerFactory;
    //private static IDatabaseFactory databaseFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
       // AbstractAppFactory.setDatabaseFactory(appFactory.createDatabaseFactory());
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
     //   databaseFactory = AbstractAppFactory.getDatabaseFactory();
    }

    @Test
    public void ConstructorTest() {
        ILeague league = leagueManagerFactory.createLeague();
        Assert.assertEquals(-1, league.getLeagueID());
    }

    @Test
    public void getLeagueIDTest() {
        ILeague league = leagueManagerFactory.createLeague();
        league.setLeagueID(13);
        Assert.assertEquals(13, league.getLeagueID());
    }

    @Test
    public void setLeagueIDTest() {
        ILeague league = leagueManagerFactory.createLeague();
        league.setLeagueID(1);
        Assert.assertEquals(1, league.getLeagueID());
    }

    @Test
    public void getLeagueNameTest() {
        ILeague league = leagueManagerFactory.createLeague();
        league.setLeagueName("Dalhousie Hockey League");
        Assert.assertEquals("Dalhousie Hockey League", league.getLeagueName());
    }

    @Test
    public void setLeagueNameTest() {
        ILeague league = leagueManagerFactory.createLeague();
        league.setLeagueName("NHL");
        Assert.assertEquals("NHL", league.getLeagueName());
    }

    @Test
    public void getLeagueDateTest() {
        ILeague league = leagueManagerFactory.createLeague();
        league.setLeagueDate(LocalDate.now());
        Assert.assertEquals(LocalDate.now(), league.getLeagueDate());
    }

    @Test
    public void incrementLeagueDateTest() {
        ILeague league = leagueManagerFactory.createLeague();
        league.setLeagueDate(LocalDate.now());
        league.incrementLeagueDate();
        Assert.assertEquals(LocalDate.now().plusDays(1), league.getLeagueDate());
    }

    @Test
    public void setLeagueDateTest() {
        ILeague league = leagueManagerFactory.createLeague();
        league.setLeagueDate(LocalDate.of(2000, 4, 2));
        Assert.assertEquals(LocalDate.of(2000, 4, 2), league.getLeagueDate());
    }

    @Test
    public void getDaysSinceLastStatIncreaseTest() {
        ILeague league = leagueManagerFactory.createLeague();
        league.setDaysSinceLastStatIncrease(10);
        Assert.assertEquals(10, league.getDaysSinceLastStatIncrease());
    }

    @Test
    public void setDaysSinceLastStatIncreaseTest() {
        ILeague league = leagueManagerFactory.createLeague();
        league.setDaysSinceLastStatIncrease(99);
        Assert.assertEquals(99, league.getDaysSinceLastStatIncrease());
    }

    @Test
    public void incrementDaysSinceLastStatIncreaseTest() {
        ILeague league = leagueManagerFactory.createLeague();
        league.setDaysSinceLastStatIncrease(99);
        league.incrementDaysSinceLastStatIncrease();
        Assert.assertEquals(100, league.getDaysSinceLastStatIncrease());
    }

    @Test
    public void resetDaysSinceLastStatIncreaseTest() {
        ILeague league = leagueManagerFactory.createLeague();
        league.setDaysSinceLastStatIncrease(99);
        league.resetDaysSinceLastStatIncrease();
        Assert.assertEquals(0, league.getDaysSinceLastStatIncrease());
    }

    @Test
    public void setGamePlayConfigTest() {
  //      IGamePlayConfigPersistence gamePlayConfigDB = databaseFactory.createGamePlayConfigPersistence();
        ILeague league = leagueManagerFactory.createLeague();
        IGamePlayConfig gamePlayConfig = leagueManagerFactory.createGamePlayConfig();
   //     gamePlayConfigDB.loadGamePlayConfig(1, gamePlayConfig);
        league.setGamePlayConfig(gamePlayConfig);

        IGamePlayConfig leagueConfig = league.getGamePlayConfig();
        Assert.assertEquals(1, leagueConfig.getLeagueID());
    }

    @Test
    public void getConferenceByIdTest() {
        ILeague league = leagueManagerFactory.createLeague();
        Assert.assertNull(league.getConferenceById(1));
    }

    @Test
    public void addConferenceTest() {
        ILeague league = leagueManagerFactory.createLeague();
        IConference conference = leagueManagerFactory.createConference();
        league.addConference(conference);

        List<IConference> leagueConferences = league.getConferences();
        Assert.assertEquals(1, leagueConferences.size());
    }

    @Test
    public void setConferencesTest() {
        ILeague league = leagueManagerFactory.createLeague();
  //      IConferencePersistence conferenceDB = databaseFactory.createConferencePersistence();
        List<IConference> conferences = new ArrayList<>();
   //     league.loadConferences(conferenceDB, conferences);
        league.setConferences(conferences);

        List<IConference> leagueConferences = league.getConferences();
        Assert.assertEquals(2, leagueConferences.size());
    }

    @Test
    public void getFreeAgentByIdTest() {
        ILeague league = leagueManagerFactory.createLeague();
        Assert.assertNull(league.getFreeAgentById(0));
    }

    @Test
    public void addFreeAgentTest() {
        ILeague league = leagueManagerFactory.createLeague();
        IFreeAgent freeAgent = leagueManagerFactory.createFreeAgent();
        league.addFreeAgent(freeAgent);
        List<IFreeAgent> leagueFreeAgents = league.getFreeAgents();
        Assert.assertEquals(1, leagueFreeAgents.size());
    }

    @Test
    public void removeFreeAgentTest() {
        ILeague league = leagueManagerFactory.createLeague();
    //    IFreeAgentPersistence freeAgentPersistence = databaseFactory.createFreeAgentPersistence();
        List<IFreeAgent> freeAgents = new ArrayList<>();

   //     league.loadLeagueFreeAgents(freeAgentPersistence, freeAgents);
        league.setFreeAgents(freeAgents);
        league.removeFreeAgent(freeAgents.get(0));

        List<IFreeAgent> leagueFreeAgents = league.getFreeAgents();
        Assert.assertEquals(2, leagueFreeAgents.size());

        List<IFreeAgent> emptyList = new ArrayList<>();
        league.setFreeAgents(emptyList);
        IFreeAgent agentThatNotExist = leagueManagerFactory.createFreeAgent();
        Assert.assertFalse(league.removeFreeAgent(agentThatNotExist));
    }

    @Test
    public void getCoachByIdTest() {
        ILeague league = leagueManagerFactory.createLeague();
        Assert.assertNull(league.getCoachById(0));
    }

    @Test
    public void addCoachTest() {
        ILeague league = leagueManagerFactory.createLeague();
        ICoach coach = leagueManagerFactory.createCoach();
        league.addCoach(coach);

        List<ICoach> leagueCoaches = league.getCoaches();
        Assert.assertEquals(1, leagueCoaches.size());
    }

    @Test
    public void getManagerByIdTest() {
        ILeague league = leagueManagerFactory.createLeague();
        Assert.assertNull(league.getManagerById(0));
    }

    @Test
    public void addManagerTest() {
        ILeague league = leagueManagerFactory.createLeague();
        IManager manager = leagueManagerFactory.createManager();
        league.addManager(manager);

        List<IManager> leagueManagers = league.getManagers();
        Assert.assertEquals(1, leagueManagers.size());
    }

    @Test
    public void addRetiredTeamPlayerTest() {
        ILeague league = leagueManagerFactory.createLeague();
        ITeamPlayer retiredTeamPlayer = leagueManagerFactory.createTeamPlayer();
        league.addRetiredTeamPlayer(retiredTeamPlayer);

        List<ITeamPlayer> retiredTeamPlayers = league.getRetiredTeamPlayers();
        Assert.assertEquals(1, retiredTeamPlayers.size());
    }

    @Test
    public void addRetiredFreeAgentsTest() {
        ILeague league = leagueManagerFactory.createLeague();
        IFreeAgent freeAgent = leagueManagerFactory.createFreeAgent();
        league.addRetiredFreeAgent(freeAgent);

        List<IFreeAgent> retiredFreeAgents = league.getRetiredFreeAgents();
        Assert.assertEquals(1, retiredFreeAgents.size());
    }
}
