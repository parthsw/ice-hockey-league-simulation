package com.IceHockeyLeagueTest.LeagueManagerTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueCreator;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeagueTest.LeagueFileHandlerTest.LeagueJsonMock;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LeagueCreatorTest {
    private static ILeagueCreator leagueCreator;
    private static ILeague league;
    private static ILeagueManagerFactory leagueManagerFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        AbstractAppFactory.setTrophySystemFactory(appFactory.createTrophySystemFactory());
        ILeagueManagerFactory leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        leagueCreator = leagueManagerFactory.createLeagueCreator();
    }

    @Before
    public void setupBeforeTest() {
        JSONObject leagueJson = LeagueJsonMock.instance().validLeagueJson();
        league = leagueCreator.createLeague(leagueJson);
    }

    @Test
    public void createLeagueLeagueNameTest() {
        Assert.assertEquals("DHL", league.getLeagueName());
    }

    @Test
    public void createLeagueConferencesTest() {
        Assert.assertEquals(2, league.getConferences().size());
    }

    @Test
    public void createLeagueFreeAgentsTest() {
        Assert.assertEquals(60, league.getFreeAgents().size());
    }

    @Test
    public void createLeagueCoachesTest() {
        Assert.assertEquals(3, league.getCoaches().size());
    }

    @Test
    public void createLeagueManagersTest() {
        Assert.assertEquals(3, league.getManagers().size());
    }

}
