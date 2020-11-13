package com.TradingTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerStats;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.Trading.GetTopNBestPlayersForGivenPosition;
import com.Trading.ITradingFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Random;

public class GetTopNBestPlayersForGivenPositionTest {
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
    public void getPlayersTest() {
        ITeam team = leagueManagerFactory.createTeam();
        String[] positions = new String[]{"Goalie", "Forward", "Defence"};
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            String temp = positions[random.nextInt(positions.length)];
            ITeamPlayer player = leagueManagerFactory.createTeamPlayer();
            IPlayerStats stats = leagueManagerFactory.createPlayerStats();
            stats.setPosition(temp);
            stats.setStrength(random.nextInt(100));
            player.setPlayerStats(stats);
            team.addPlayer(player);
        }

        GetTopNBestPlayersForGivenPosition object = tradingFactory.createGetTopNBestPlayersForGivenPosition(team, 1, "Forward");
        List<ITeamPlayer> players = object.getPlayers();
        Assert.assertEquals(1, players.size());
        for (ITeamPlayer player : players) {
            Assert.assertEquals("Forward", player.getPlayerStats().getPosition());

        }

    }

    @Test
    public void getCominedStrengthTest() {
        ITeam team = leagueManagerFactory.createTeam();
        String[] positions = new String[]{"Goalie", "Forward", "Defence"};
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            String temp = positions[random.nextInt(positions.length)];
            ITeamPlayer player = leagueManagerFactory.createTeamPlayer();
            IPlayerStats stats = leagueManagerFactory.createPlayerStats();
            stats.setPosition(temp);
            stats.setStrength(random.nextInt(100));
            player.setPlayerStats(stats);
            team.addPlayer(player);
        }
        String tempPosition = team.getPlayers().get(0).getPlayerStats().getPosition();
        GetTopNBestPlayersForGivenPosition object = tradingFactory.createGetTopNBestPlayersForGivenPosition(team, 1, tempPosition);
        object.getPlayers();
        float sum = object.getCombinedStrendth();
        if (sum > 0) {
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }

    }
}
