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


public class TradeAcceptorTest {


    @Test
    public void acceptTrade() {
        List<ITeamPlayer> offered = new ArrayList<>();
        List<ITeamPlayer> requested = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            ITeamPlayer player = new TeamPlayer();
            PlayerStats stats = new PlayerStats();
            int value = random.nextInt(100);
            stats.setStrength(value);
            player.setPlayerStats(stats);
            player.setPlayerName("team1");
            offered.add(player);
        }
        for (int i = 0; i < 5; i++) {
            ITeamPlayer player = new TeamPlayer();
            PlayerStats stats = new PlayerStats();
            int value = random.nextInt(100);
            stats.setStrength(value);
            player.setPlayerStats(stats);
            player.setPlayerName("team2");
            requested.add(player);
        }

        ITeam team1 = new Team();
        ITeam team2 = new Team();
        team1.setPlayers(offered);
        team2.setPlayers(requested);
        Trade trade = new Trade(5);
        trade.setSendingTeam(team1);
        trade.setReceivingTeam(team2);
        trade.setSendingPlayers(offered);
        trade.setReceivingPlayers(requested);

        TradeAcceptor object = new TradeAcceptor(trade);
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
