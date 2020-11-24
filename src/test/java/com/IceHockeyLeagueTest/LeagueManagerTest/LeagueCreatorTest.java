package com.IceHockeyLeagueTest.LeagueManagerTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueCreator;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeagueTest.LeagueFileHandlerTest.LeagueJsonMock;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class LeagueCreatorTest {
    private static ILeagueManagerFactory leagueManagerFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
    }

    @Test
    public void createLeagueTest() {
        JSONObject leagueJson = LeagueJsonMock.instance().validLeagueJson();
        ILeagueCreator leagueCreator = leagueManagerFactory.createLeagueCreator();

        ILeague league = leagueCreator.createLeague(leagueJson);
        IConference conference = league.getConferences().get(0);

        Assert.assertEquals("DHL", league.getLeagueName());
        Assert.assertEquals(2, league.getConferences().size());
        Assert.assertEquals("Eastern Conference", conference.getConferenceName());
        Assert.assertEquals(3, league.getFreeAgents().size());
        Assert.assertEquals(3, league.getCoaches().size());
        Assert.assertEquals(3, league.getManagers().size());
    }

}
