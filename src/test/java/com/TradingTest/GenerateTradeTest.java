package com.TradingTest;

import com.IceHockeyLeague.LeagueManager.Player.IPlayerStats;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Player.PlayerStats;
import com.IceHockeyLeague.LeagueManager.Player.TeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Team.Team;
import com.Trading.GenerateTrade;
import com.Trading.Trade;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class GenerateTradeTest {

    @Test
    public void generateTradeTest() {
        ITeam team1 = new Team();
        ITeam team2 = new Team();
        Random random = new Random();
        String[] positions = new String[]{"Forward", "Defence", "Goalie"};
        for (int i = 0; i < 20; i++) {
            ITeamPlayer player = new TeamPlayer();
            IPlayerStats stats = new PlayerStats();
            stats.setStrength(random.nextInt(100));
            String temp = positions[random.nextInt(positions.length)];
            stats.setPosition(temp);
            player.setPlayerStats(stats);
            team1.addPlayer(player);
        }

        for (int i = 0; i < 20; i++) {
            ITeamPlayer player = new TeamPlayer();
            IPlayerStats stats = new PlayerStats();
            stats.setStrength(random.nextInt(100));
            String temp = positions[random.nextInt(positions.length)];
            stats.setPosition(temp);
            player.setPlayerStats(stats);
            team2.addPlayer(player);
        }
        int maxTradablePlayers = 4;

        GenerateTrade testObject = new GenerateTrade();
        testObject.generateTrade(team1, maxTradablePlayers, team2);
        testObject.decideTrade(1);
        boolean result = testObject.getTradeResult();
        Assert.assertTrue(result);
    }

    @Test
    public void generateTradeTest2() {
        ITeam team1 = new Team();
        ITeam team2 = new Team();
        team1.setTeamName("Team 1");
        team2.setTeamName("Team 2");
        Random random = new Random();
        String[] positions = new String[]{"Forward", "Defence", "Goalie"};
        for (int j = 0; j < 20; j++) {
            ITeamPlayer player = new TeamPlayer();
            IPlayerStats stats = new PlayerStats();
            stats.setStrength(random.nextInt(100));
            String temp = positions[random.nextInt(positions.length)];
            stats.setPosition(temp);
            player.setPlayerStats(stats);
            team1.addPlayer(player);
        }
        for (int j = 0; j < 20; j++) {
            ITeamPlayer player = new TeamPlayer();
            IPlayerStats stats = new PlayerStats();
            stats.setStrength(random.nextInt(100));
            String temp = positions[random.nextInt(positions.length)];
            stats.setPosition(temp);
            player.setPlayerStats(stats);
            team2.addPlayer(player);
        }

        GenerateTrade trade = new GenerateTrade();
        trade.generateTrade(team1, 5, team2);
        Trade testTrade = trade.getGeneratedTrade();
        Assert.assertTrue(testTrade.getSendingPlayers().size() > 0);
        Assert.assertTrue(testTrade.getReceivingPlayers().size() > 0);

        trade.decideTrade(1);
        Assert.assertTrue(trade.getResultTeams().size() > 0);
    }

}
