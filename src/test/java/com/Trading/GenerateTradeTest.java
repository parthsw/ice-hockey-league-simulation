package com.Trading;

import com.IceHockeyLeague.LeagueManager.Player.IPlayerStats;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Player.PlayerStats;
import com.IceHockeyLeague.LeagueManager.Player.TeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Team.Team;
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
        for (int i = 0; i < 5; i++) {
            ITeamPlayer player = new TeamPlayer();
            IPlayerStats stats = new PlayerStats();
            stats.setStrength(random.nextInt(100));
            String temp = positions[random.nextInt(positions.length)];
            stats.setPosition(temp);
            player.setPlayerStats(stats);
            team1.addPlayer(player);
        }

        for (int i = 0; i < 5; i++) {
            ITeamPlayer player = new TeamPlayer();
            IPlayerStats stats = new PlayerStats();
            stats.setStrength(random.nextInt(100));
            String temp = positions[random.nextInt(positions.length)];
            stats.setPosition(temp);
            player.setPlayerStats(stats);
            team2.addPlayer(player);
        }
        int maxTradablePlayers = 1;

        GenerateTrade testObject = new GenerateTrade();
        testObject.generateTrade(team1, maxTradablePlayers, team2, 1);
        boolean result = testObject.getTradeResult();
        Assert.assertTrue(result);
    }
}
