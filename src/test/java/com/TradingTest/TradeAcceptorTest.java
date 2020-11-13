package com.TradingTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerStats;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.Trading.ITradingFactory;
import com.Trading.Trade;
import com.Trading.TradeAcceptor;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TradeAcceptorTest {
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
    public void acceptTrade() {
        List<ITeamPlayer> offered = new ArrayList<>();
        List<ITeamPlayer> requested = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            ITeamPlayer player = leagueManagerFactory.createTeamPlayer();
            IPlayerStats stats = leagueManagerFactory.createPlayerStats();
            int value = random.nextInt(100);
            stats.setStrength(value);
            player.setPlayerStats(stats);
            player.setPlayerName("team1");
            offered.add(player);
        }
        for (int i = 0; i < 5; i++) {
            ITeamPlayer player = leagueManagerFactory.createTeamPlayer();
            IPlayerStats stats = leagueManagerFactory.createPlayerStats();
            int value = random.nextInt(100);
            stats.setStrength(value);
            player.setPlayerStats(stats);
            player.setPlayerName("team2");
            requested.add(player);
        }

        ITeam team1 = leagueManagerFactory.createTeam();
        ITeam team2 = leagueManagerFactory.createTeam();
        team1.setPlayers(offered);
        team2.setPlayers(requested);
        Trade trade = tradingFactory.createTrade(5);
        trade.setSendingTeam(team1);
        trade.setReceivingTeam(team2);
        trade.setSendingPlayers(offered);
        trade.setReceivingPlayers(requested);

        TradeAcceptor object = tradingFactory.createTradeAcceptor(trade);
        List<ITeam> result = object.acceptTrade();
        ITeam resultTeam1 = result.get(0);
        ITeam resultTeam2 = result.get(1);
        for (ITeamPlayer player : resultTeam1.getPlayers()) {
            Assert.assertEquals("team2", player.getPlayerName());
        }

        for (ITeamPlayer player : resultTeam2.getPlayers()) {
            Assert.assertEquals("team1", player.getPlayerName());
        }
    }
}
