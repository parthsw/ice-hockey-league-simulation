package com.TradingTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerStats;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.Trading.ITradingFactory;
import com.IceHockeyLeague.Trading.TradeDecision;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TradeDecisionTest {
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
    public void tradeDecision() {
        List<ITeamPlayer> offered = new ArrayList<>();
        List<ITeamPlayer> requested = new ArrayList<>();
        int offeredSum = 0;
        int requestedSum = 0;
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            ITeamPlayer player = leagueManagerFactory.createTeamPlayer();
            IPlayerStats stats = leagueManagerFactory.createPlayerStats();
            int value = random.nextInt(100);
            stats.setStrength(value);
            player.setPlayerStats(stats);
            offeredSum += value;
            offered.add(player);
        }
        for (int i = 0; i < 5; i++) {
            ITeamPlayer player = leagueManagerFactory.createTeamPlayer();
            IPlayerStats stats = leagueManagerFactory.createPlayerStats();
            int value = random.nextInt(100);
            stats.setStrength(value);
            player.setPlayerStats(stats);
            requestedSum += value;
            requested.add(player);
        }

        TradeDecision object = tradingFactory.createTradeDecision(offered, requested, 0);
        boolean decision = object.tradeDecision();
        if (requestedSum > offeredSum) {
            Assert.assertFalse(decision);
        } else {
            Assert.assertTrue(decision);
        }

        TradeDecision object2 = tradingFactory.createTradeDecision(offered, requested, 1);
        boolean decision2 = object2.tradeDecision();
        Assert.assertTrue(decision2);
        System.out.println(decision);
        System.out.println(decision2);
        System.out.println("Requested " + requestedSum);
        System.out.println("Offered " + offeredSum);
    }
}
