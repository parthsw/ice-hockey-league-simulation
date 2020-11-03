package com.TradingTest;


import com.IceHockeyLeague.LeagueManager.Player.IPlayerStats;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Player.PlayerStats;
import com.IceHockeyLeague.LeagueManager.Player.TeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Team.Team;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class TradeTest {

    @Test
    public void validateTradeTest() {
        ITeam team1 = new Team();
        ITeam team2 = new Team();
        team1.setTeamName("Team 1");
        team2.setTeamName("Team 2");
        List<ITeamPlayer> dummyPlayers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ITeamPlayer player = new TeamPlayer();
            IPlayerStats stats = new PlayerStats();
            stats.setPosition("Forward");
            player.setPlayerStats(stats);
            dummyPlayers.add(player);
        }
        team1.setPlayers(dummyPlayers);
        team2.setPlayers(dummyPlayers);
        Trade testObj = new Trade(5);
        testObj.setSendingTeam(team1);
        testObj.setReceivingTeam(team2);
        testObj.setSendingPlayers(dummyPlayers);
        testObj.setReceivingPlayers(dummyPlayers);
        boolean success = testObj.validateTrade();
        Assert.assertEquals(true, success);
    }
}
