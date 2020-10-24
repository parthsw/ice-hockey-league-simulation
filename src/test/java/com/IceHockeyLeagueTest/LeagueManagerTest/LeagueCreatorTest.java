package com.IceHockeyLeagueTest.LeagueManagerTest;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueCreator;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeagueTest.LeagueFileHandlerTest.LeagueJsonMock;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class LeagueCreatorTest {

    @Before
    public void setup() {
        AbstractLeagueManagerFactory.setFactory(new TestLeagueManagerFactory());
    }

    @Test
    public void createLeagueTest() {
        JSONObject leagueJson = LeagueJsonMock.getInstance().validLeagueJson();
        ILeagueCreator leagueCreator = AbstractLeagueManagerFactory.getFactory().getLeagueCreator();

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
