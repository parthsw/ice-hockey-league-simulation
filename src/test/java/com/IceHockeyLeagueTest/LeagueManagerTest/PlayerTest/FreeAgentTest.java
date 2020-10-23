package com.IceHockeyLeagueTest.LeagueManagerTest.PlayerTest;

import com.IceHockeyLeague.LeagueManager.LeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.IFreeAgent;
import com.IceHockeyLeagueTest.LeagueManagerTest.TestLeagueManagerFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FreeAgentTest {

    @Before
    public void setup() {
        LeagueManagerFactory.setFactory(new TestLeagueManagerFactory());
    }

    @Test
    public void ConstructorTest() {
        IFreeAgent freeAgent = LeagueManagerFactory.getFactory().getFreeAgent();
        Assert.assertEquals(-1, freeAgent.getFreeAgentID());
        Assert.assertEquals(-1, freeAgent.getLeagueID());
    }

    @Test
    public void getFreeAgentIDTest() {
        IFreeAgent freeAgent = LeagueManagerFactory.getFactory().getFreeAgent();
        freeAgent.setFreeAgentID(2);
        Assert.assertEquals(2, freeAgent.getFreeAgentID());
    }

    @Test
    public void setFreeAgentID() {
        IFreeAgent freeAgent = LeagueManagerFactory.getFactory().getFreeAgent();
        freeAgent.setFreeAgentID(11);
        Assert.assertEquals(11, freeAgent.getFreeAgentID());
    }

    @Test
    public void getLeagueIDTest() {
        IFreeAgent freeAgent = LeagueManagerFactory.getFactory().getFreeAgent();
        freeAgent.setLeagueID(9);
        Assert.assertEquals(9, freeAgent.getLeagueID());
    }

    @Test
    public void setLeagueIDTest() {
        IFreeAgent freeAgent = LeagueManagerFactory.getFactory().getFreeAgent();
        freeAgent.setLeagueID(17);
        Assert.assertEquals(17, freeAgent.getLeagueID());
    }

    @Test
    public void isValidTest() {
        IFreeAgent freeAgent = LeagueManagerFactory.getFactory().getFreeAgent();
        Assert.assertFalse(freeAgent.isValid());
    }

}
