package com.TradingTest;

import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Player.PlayerStats;
import com.IceHockeyLeague.LeagueManager.Player.TeamPlayer;
import com.Trading.TradeDecision;
import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TradeDecisionTest {

    @Test
    public void tradeDecision() {
        List<ITeamPlayer> offered = new ArrayList<>();
        List<ITeamPlayer> requested = new ArrayList<>();
        int offeredSum = 0;
        int requestedSum = 0;
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            ITeamPlayer player = new TeamPlayer();
            PlayerStats stats = new PlayerStats();
            int value = random.nextInt(100);
            stats.setStrength(value);
            player.setPlayerStats(stats);
            offeredSum += value;
            offered.add(player);
        }
        for (int i = 0; i < 5; i++) {
            ITeamPlayer player = new TeamPlayer();
            PlayerStats stats = new PlayerStats();
            int value = random.nextInt(100);
            stats.setStrength(value);
            player.setPlayerStats(stats);
            requestedSum += value;
            requested.add(player);
        }


        TradeDecision object = new TradeDecision(offered, requested, 0);
        boolean decision = object.tradeDecision();
        if (requestedSum > offeredSum) {
            Assert.assertEquals(false, decision);
        } else {
            Assert.assertEquals(true, decision);
        }

        TradeDecision object2 = new TradeDecision(offered, requested, 1);
        boolean decision2 = object2.tradeDecision();
        Assert.assertEquals(true, decision2);
        System.out.println(decision);
        System.out.println(decision2);
        System.out.println("Requested " + requestedSum);
        System.out.println("Offered " + offeredSum);
    }
}
