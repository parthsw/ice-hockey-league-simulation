package com.tradingTest;


import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Player.PlayerStats;
import com.IceHockeyLeague.LeagueManager.Player.TeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Team.Team;
import com.Trading.GetTopNBestPlayersForGivenPosition;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Random;


public class GetTopNBestPlayersForGivenPositionTest {


    @Test
    public void getPlayersTest() {
        ITeam team = new Team();
        String[] positions = new String[]{"Goalie", "Forward", "Defence"};
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            String temp = positions[random.nextInt(positions.length)];
            ITeamPlayer player = new TeamPlayer();
            PlayerStats stats = new PlayerStats();
            stats.setPosition(temp);
            stats.setStrength(random.nextInt(100));
            player.setPlayerStats(stats);
            team.addPlayer(player);
        }

        GetTopNBestPlayersForGivenPosition object = new GetTopNBestPlayersForGivenPosition(team, 1, "Forward");
        List<ITeamPlayer> players = object.getPlayers();
        Assert.assertEquals(1, players.size());
        for (ITeamPlayer player : players) {
            Assert.assertEquals("Forward", player.getPlayerStats().getPosition());

        }

    }

    @Test
    public void getCominedStrengthTest() {
        ITeam team = new Team();
        String[] positions = new String[]{"Goalie", "Forward", "Defence"};
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            String temp = positions[random.nextInt(positions.length)];
            ITeamPlayer player = new TeamPlayer();
            PlayerStats stats = new PlayerStats();
            stats.setPosition(temp);
            stats.setStrength(random.nextInt(100));
            player.setPlayerStats(stats);
            team.addPlayer(player);
        }
        String tempPosition = team.getPlayers().get(0).getPlayerStats().getPosition();
        GetTopNBestPlayersForGivenPosition object = new GetTopNBestPlayersForGivenPosition(team, 1, tempPosition);
        object.getPlayers();
        float sum = object.getCombinedStrendth();
        if (sum > 0) {
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }

    }
}
