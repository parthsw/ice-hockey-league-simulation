package com.IceHockeyLeagueTest.LeagueManagerTest.PlayerTest;

import com.IceHockeyLeague.LeagueManager.LeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.IPlayer;
import com.IceHockeyLeagueTest.LeagueManagerTest.TestLeagueManagerFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

    @Before
    public void setup() {
        LeagueManagerFactory.setFactory(new TestLeagueManagerFactory());
    }

    @Test
    public void ConstructorTest() {
        IPlayer player = LeagueManagerFactory.getFactory().getPlayer();
        Assert.assertFalse(player.getIsInjured());
        Assert.assertFalse(player.getIsRetired());
    }

    @Test
    public void getPlayerNameTest() {
        IPlayer player = LeagueManagerFactory.getFactory().getPlayer();
        player.setPlayerName("Joe Doe");
        Assert.assertEquals("Joe Doe", player.getPlayerName());
    }

    @Test
    public void setPlayerNameTest() {
        IPlayer player = LeagueManagerFactory.getFactory().getPlayer();
        player.setPlayerName("John");
        Assert.assertEquals("John", player.getPlayerName());
    }

    @Test
    public void getPlayerAgeTest() {
        IPlayer player = LeagueManagerFactory.getFactory().getPlayer();
        player.setPlayerAge(20);
        Assert.assertEquals(20, player.getPlayerAge());
    }

    @Test
    public void setPlayerAgeTest() {
        IPlayer player = LeagueManagerFactory.getFactory().getPlayer();
        player.setPlayerAge(46);
        Assert.assertEquals(46, player.getPlayerAge());
    }

    @Test
    public void isValidTest() {
        IPlayer player = LeagueManagerFactory.getFactory().getPlayer();
        Assert.assertFalse(player.isValid());
    }
}
