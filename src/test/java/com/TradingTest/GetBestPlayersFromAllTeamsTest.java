package com.TradingTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerStats;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.Trading.GetBestPlayersFromAllTeams;
import com.IceHockeyLeague.Trading.ITradingFactory;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GetBestPlayersFromAllTeamsTest {
    private static ILeagueManagerFactory leagueManagerFactory;
    private static ITradingFactory tradingFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        AbstractAppFactory.setTradingFactory(appFactory.createTradingFactory());
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        tradingFactory = AbstractAppFactory.getTradingFactory();
    }

    @Test
    public void getBestTradeOptionTest() {
        String[] positions = new String[]{"Goalie", "Forward", "Defence"};
        Random random = new Random();

        List<ITeam> teams = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ITeam team = leagueManagerFactory.createTeam();
            teams.add(team);
        }

        for (ITeam team : teams) {
            for (int i = 0; i < 20; i++) {
                String temp = positions[random.nextInt(positions.length)];
                ITeamPlayer player = leagueManagerFactory.createTeamPlayer();
                IPlayerStats stats = leagueManagerFactory.createPlayerStats();
                stats.setPosition(temp);
                stats.setStrength(random.nextInt(100));
                player.setPlayerStats(stats);
                team.addPlayer(player);
            }
        }

        ITeam test = leagueManagerFactory.createTeam();
        for (int i = 0; i < 20; i++) {
            String temp = positions[random.nextInt(positions.length)];
            ITeamPlayer player = leagueManagerFactory.createTeamPlayer();
            IPlayerStats stats = leagueManagerFactory.createPlayerStats();
            stats.setPosition(temp);
            stats.setStrength(random.nextInt(10000));
            player.setPlayerStats(stats);
            test.addPlayer(player);
        }
        test.setTeamName("Best Team");
        teams.add(test);

        GetBestPlayersFromAllTeams object = tradingFactory.createGetBestPlayersFromAllTeams(teams);
        object.getBestTradeOption("Forward", 1);
        ITeam bestTeam = object.getTeam();
        List<ITeamPlayer> players = object.getBestPlayersSet();
        Assert.assertEquals("Best Team", bestTeam.getTeamName());
        Assert.assertEquals(1, players.size());
    }
}
