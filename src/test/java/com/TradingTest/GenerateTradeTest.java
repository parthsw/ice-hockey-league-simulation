package com.TradingTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerStats;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.Trading.GenerateTrade;
import com.IceHockeyLeague.Trading.ITradingFactory;
import com.IceHockeyLeague.Trading.Trade;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;

public class GenerateTradeTest {
    private static ILeagueManagerFactory leagueManagerFactory;
    private static ITradingFactory tradingFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        AbstractAppFactory.setTradingFactory(appFactory.createTradingFactory());
        AbstractAppFactory.setTradingFactory(appFactory.createTradingFactory());
        tradingFactory = AbstractAppFactory.getTradingFactory();
        AbstractAppFactory.setIOFactory(appFactory.createIOFactory());
    }

    @Test
    public void generateTradeTest() {
        ITeam team1 = leagueManagerFactory.createTeam();
        ITeam team2 = leagueManagerFactory.createTeam();
        Random random = new Random();
        String[] positions = new String[]{"Forward", "Defence", "Goalie"};
        for (int i = 0; i < 20; i++) {
            ITeamPlayer player = leagueManagerFactory.createTeamPlayer();
            IPlayerStats stats = leagueManagerFactory.createPlayerStats();
            stats.setStrength(random.nextInt(100));
            String temp = positions[random.nextInt(positions.length)];
            stats.setPosition(temp);
            player.setPlayerStats(stats);
            team1.addPlayer(player);
        }

        for (int i = 0; i < 20; i++) {
            ITeamPlayer player = leagueManagerFactory.createTeamPlayer();
            IPlayerStats stats = leagueManagerFactory.createPlayerStats();
            stats.setStrength(random.nextInt(100));
            String temp = positions[random.nextInt(positions.length)];
            stats.setPosition(temp);
            player.setPlayerStats(stats);
            team2.addPlayer(player);
        }
        int maxTradablePlayers = 4;

        GenerateTrade testObject = tradingFactory.createGenerateTrade();
        testObject.generateTrade(team1, maxTradablePlayers, team2);
        testObject.decideTrade(1);
        boolean result = testObject.getTradeResult();
        Assert.assertTrue(result);
    }

    @Test
    public void generateTradeTest2() {
        ITeam team1 = leagueManagerFactory.createTeam();
        ITeam team2 = leagueManagerFactory.createTeam();
        team1.setTeamName("Team 1");
        team2.setTeamName("Team 2");
        Random random = new Random();
        String[] positions = new String[]{"Forward", "Defence", "Goalie"};
        for (int j = 0; j < 20; j++) {
            ITeamPlayer player = leagueManagerFactory.createTeamPlayer();
            IPlayerStats stats = leagueManagerFactory.createPlayerStats();
            stats.setStrength(random.nextInt(100));
            String temp = positions[random.nextInt(positions.length)];
            stats.setPosition(temp);
            player.setPlayerStats(stats);
            team1.addPlayer(player);
        }
        for (int j = 0; j < 20; j++) {
            ITeamPlayer player = leagueManagerFactory.createTeamPlayer();
            IPlayerStats stats = leagueManagerFactory.createPlayerStats();
            stats.setStrength(random.nextInt(100));
            String temp = positions[random.nextInt(positions.length)];
            stats.setPosition(temp);
            player.setPlayerStats(stats);
            team2.addPlayer(player);
        }

        GenerateTrade trade = tradingFactory.createGenerateTrade();
        trade.generateTrade(team1, 5, team2);
        Trade testTrade = trade.getGeneratedTrade();
        Assert.assertTrue(testTrade.getSendingPlayers().size() > 0);
        Assert.assertTrue(testTrade.getReceivingPlayers().size() > 0);

        trade.decideTrade(1);
        Assert.assertTrue(trade.getResultTeams().size() > 0);
    }

}
