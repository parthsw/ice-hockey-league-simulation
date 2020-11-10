package com.Trading;


import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Player.PlayerStats;
import com.IceHockeyLeague.LeagueManager.Player.TeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Team.Team;
import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GetBestPlayersFromAllTeamsTest {


    @Test
    public void getBestTradeOptionTest() {
        String[] positions = new String[]{"Goalie", "Forward", "Defence"};
        Random random = new Random();

        List<ITeam> teams = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ITeam team = new Team();
            teams.add(team);
        }

        for (ITeam team : teams) {
            for (int i = 0; i < 20; i++) {
                String temp = positions[random.nextInt(positions.length)];
                ITeamPlayer player = new TeamPlayer();
                PlayerStats stats = new PlayerStats();
                stats.setPosition(temp);
                stats.setStrength(random.nextInt(100));
                player.setPlayerStats(stats);
                team.addPlayer(player);
            }
        }

        ITeam test = new Team();
        for (int i = 0; i < 20; i++) {
            String temp = positions[random.nextInt(positions.length)];
            ITeamPlayer player = new TeamPlayer();
            PlayerStats stats = new PlayerStats();
            stats.setPosition(temp);
            stats.setStrength(random.nextInt(10000));
            player.setPlayerStats(stats);
            test.addPlayer(player);
        }
        test.setTeamName("Best Team");
        teams.add(test);

        GetBestPlayersFromAllTeams object = new GetBestPlayersFromAllTeams(teams);
        object.getBestTradeOption("Forward", 1);
        ITeam bestTeam = object.getTeam();
        List<ITeamPlayer> players = object.getBestPlayersSet();
        Assert.assertEquals("Best Team", bestTeam.getTeamName());
        Assert.assertEquals(1, players.size());
    }
}
