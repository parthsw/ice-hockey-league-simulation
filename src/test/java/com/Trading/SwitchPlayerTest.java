package com.Trading;

import com.IceHockeyLeague.LeagueManager.Player.*;
import com.Trading.SwitchPlayer;
import org.junit.Assert;
import org.junit.Test;

public class SwitchPlayerTest {

    @Test
    public void teamToFreeTradeTest() {
        ITeamPlayer player = new TeamPlayer();
        player.setPlayerName("Sagar");
        PlayerStats stats = new PlayerStats();
        stats.setPosition("Defence");
        stats.setStrength(10);
        player.setPlayerStats(stats);
        SwitchPlayer object = new SwitchPlayer();
        IFreeAgent agent = object.teamToFreeTrade(player, 1);

        Assert.assertEquals("Sagar", agent.getPlayerName());
        Assert.assertEquals("Defence", agent.getPlayerStats().getPosition());
        Assert.assertEquals(10, (int) agent.getPlayerStats().getStrength());
    }

    @Test
    public void freeToTeamTradeTest() {
        IFreeAgent agent = new FreeAgent();
        agent.setPlayerName("Sagar");
        PlayerStats stats = new PlayerStats();
        stats.setPosition("Defence");
        stats.setStrength(10);
        agent.setPlayerStats(stats);
        SwitchPlayer object = new SwitchPlayer();
        ITeamPlayer player = object.freeToTeamTrade(agent, 1);

        Assert.assertEquals("Sagar", player.getPlayerName());
        Assert.assertEquals("Defence", player.getPlayerStats().getPosition());
        Assert.assertEquals(10, (int) player.getPlayerStats().getStrength());
    }
}
