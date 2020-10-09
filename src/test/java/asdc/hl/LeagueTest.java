package asdc.hl;

import asdc.hl.leaguemodel.models.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LeagueTest {

    @Test
    public void getLeagueNameTest(){
        League league = new League();
        league.setLeagueName("DHL");
        Assert.assertEquals("DHL", league.getLeagueName());
    }

    @Test
    public void getLeagueIDTest(){
        League league = new League();
        league.setLeagueID(1);
        Assert.assertEquals(1, league.getLeagueID());
    }

    @Test
    public void getConferenceByNameTest(){
        League league = new League();
        IConference conference = new Conference();
        conference.setConferenceName("Eastern Conference");
        league.addConference(conference);
        Assert.assertEquals(conference, league.getConferenceByName("Eastern Conference"));
    }

    @Test
    public void getConferencesTest(){
        League league = new League();
        List<IConference> conferences = new ArrayList<>();

        IConference conference1 = new Conference();
        conference1.setConferenceName("Eastern Conference");
        IConference conference2 = new Conference();
        conference2.setConferenceName("Western Conference");

        conferences.add(conference1);
        conferences.add(conference2);

        league.setConferences(conferences);
        Assert.assertEquals(conferences, league.getConferences());
    }

    @Test
    public void getFreeAgentsTest(){
        League league = new League();
        List<IPlayer> freeAgents = new ArrayList<>();

        IPlayer freeAgent1 = new Player();
        freeAgent1.setPlayerName("Free Agent 1");
        IPlayer freeAgent2 = new Player();
        freeAgent2.setPlayerName("Free Agent 2");

        freeAgents.add(freeAgent1);
        freeAgents.add(freeAgent2);

        league.setFreeAgents(freeAgents);
        Assert.assertEquals(freeAgents, league.getFreeAgents());
    }


}