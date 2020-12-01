package com.TradingTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerStats;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.Trading.ITradingFactory;
import com.IceHockeyLeague.Trading.Trade;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TradeTest {
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
    public void validateTradeTest() {
        ITeam team1 = leagueManagerFactory.createTeam();
        ITeam team2 = leagueManagerFactory.createTeam();
        team1.setTeamName("Team 1");
        team2.setTeamName("Team 2");
        List<ITeamPlayer> dummyPlayers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ITeamPlayer player = leagueManagerFactory.createTeamPlayer();
            IPlayerStats stats = leagueManagerFactory.createPlayerStats();
            stats.setPosition("Forward");
            player.setPlayerStats(stats);
            dummyPlayers.add(player);
        }
        team1.setPlayers(dummyPlayers);
        team2.setPlayers(dummyPlayers);
        Trade testObj = tradingFactory.createTrade(5);
        testObj.setSendingTeam(team1);
        testObj.setReceivingTeam(team2);
        testObj.setSendingPlayers(dummyPlayers);
        testObj.setReceivingPlayers(dummyPlayers);
        boolean success = testObj.validateTrade();
        Assert.assertTrue(success);
    }
}
