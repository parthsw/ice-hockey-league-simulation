package com.Trading;

import com.IceHockeyLeague.LeagueManager.Player.IPlayerStats;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Player.PlayerStats;
import com.IceHockeyLeague.LeagueManager.Player.TeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Team.Team;
import com.Trading.GetWorsePlayersToTradeFromTeam;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Random;

public class GetWorsePlayersToTradeFromTeamTest {

    @Test
    public void getPlayersToTradeTest() {
        ITeam team = new Team();
        Random random = new Random();
        String[] positions = new String[]{"Forward", "Defence", "Goalie"};
        for (int i = 0; i < 20; i++) {
            ITeamPlayer player = new TeamPlayer();
            IPlayerStats stats = new PlayerStats();
            stats.setStrength(random.nextInt(100));
            String temp = positions[random.nextInt(positions.length)];
            stats.setPosition(temp);
            player.setPlayerStats(stats);
            team.addPlayer(player);
        }
        int maxTradablePlayers = 5;
        GetWorsePlayersToTradeFromTeam testObj = new GetWorsePlayersToTradeFromTeam();
        List<ITeamPlayer> result = testObj.getPlayersToTrade(maxTradablePlayers, team);
        Assert.assertTrue(result.size() <= maxTradablePlayers);

    }
}
