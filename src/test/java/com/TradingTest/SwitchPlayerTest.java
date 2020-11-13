package com.TradingTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.*;
import com.Trading.ISwitchPlayer;
import com.Trading.ITradingFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SwitchPlayerTest {
    private static ILeagueManagerFactory leagueManagerFactory;
    private static ITradingFactory tradingFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        tradingFactory = appFactory.createTradingFactory();
    }

    @Test
    public void teamToFreeTradeTest() {
        ITeamPlayer player = leagueManagerFactory.createTeamPlayer();
        player.setPlayerName("Sagar");
        IPlayerStats stats = leagueManagerFactory.createPlayerStats();
        stats.setPosition("Defence");
        stats.setStrength(10);
        player.setPlayerStats(stats);
        ISwitchPlayer object = tradingFactory.createSwitchPlayer();
        IFreeAgent agent = object.teamToFreeTrade(player, 1);

        Assert.assertEquals("Sagar", agent.getPlayerName());
        Assert.assertEquals("Defence", agent.getPlayerStats().getPosition());
        Assert.assertEquals(10, (int) agent.getPlayerStats().getStrength());
    }

    @Test
    public void freeToTeamTradeTest() {
        IFreeAgent agent = leagueManagerFactory.createFreeAgent();
        agent.setPlayerName("Sagar");
        IPlayerStats stats = leagueManagerFactory.createPlayerStats();
        stats.setPosition("Defence");
        stats.setStrength(10);
        agent.setPlayerStats(stats);
        ISwitchPlayer object = tradingFactory.createSwitchPlayer();
        ITeamPlayer player = object.freeToTeamTrade(agent, 1);

        Assert.assertEquals("Sagar", player.getPlayerName());
        Assert.assertEquals("Defence", player.getPlayerStats().getPosition());
        Assert.assertEquals(10, (int) player.getPlayerStats().getStrength());
    }
}
