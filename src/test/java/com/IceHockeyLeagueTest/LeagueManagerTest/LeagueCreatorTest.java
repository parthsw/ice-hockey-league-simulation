package com.IceHockeyLeagueTest.LeagueManagerTest;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueCreator;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.Player.IPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Player.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
import com.IceHockeyLeague.LeagueManager.Manager.IManager;
import com.IceHockeyLeagueTest.LeagueFileHandlerTest.LeagueJsonMock;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class LeagueCreatorTest {

    @Before
    public void setup() {
        AbstractLeagueManagerFactory.setFactory(new TestLeagueManagerFactory());
    }

    @Test
    public void createLeagueTest() {
        JSONObject leagueJson = LeagueJsonMock.getInstance().sampleLeagueJson();
        ILeagueCreator leagueCreator = AbstractLeagueManagerFactory.getFactory().getLeagueCreator();

        ILeague league = leagueCreator.createLeague(leagueJson);
        IConference conference = league.getConferences().get(0);

        Assert.assertEquals("DHL", league.getLeagueName());
        Assert.assertEquals(1, league.getConferences().size());
        Assert.assertEquals("Eastern Conference", conference.getConferenceName());
        Assert.assertEquals(1, league.getFreeAgents().size());
        Assert.assertEquals(1, league.getCoaches().size());
        Assert.assertEquals(3, league.getManagers().size());

    }
}
