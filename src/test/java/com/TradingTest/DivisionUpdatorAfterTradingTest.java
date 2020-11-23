package com.TradingTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerStats;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.Trading.DivisionUpdatorAfterTrading;
import com.IceHockeyLeague.Trading.ITradingFactory;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;

public class DivisionUpdatorAfterTradingTest {
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
    public void divisionUpdatorAfterTradingTest() {
        ILeague league = leagueManagerFactory.createLeague();
        IConference conference = leagueManagerFactory.createConference();
        IDivision division = leagueManagerFactory.createDivision();
        ITeam team1 = leagueManagerFactory.createTeam();
        ITeam team2 = leagueManagerFactory.createTeam();

        ITeam newteam1 = leagueManagerFactory.createTeam();
        ITeam newteam2 = leagueManagerFactory.createTeam();

        Random random = new Random();
        String[] positions = new String[]{"Forward", "Defence", "Goalie"};
        for (int i = 0; i < 20; i++) {
            ITeamPlayer player = leagueManagerFactory.createTeamPlayer();
            IPlayerStats stats = leagueManagerFactory.createPlayerStats();
            stats.setStrength(random.nextInt(100));
            String temp = positions[random.nextInt(positions.length)];
            stats.setPosition(temp);
            player.setPlayerStats(stats);
            team1.addPlayer(player);
        }

        for (int i = 0; i < 20; i++) {
            ITeamPlayer player = leagueManagerFactory.createTeamPlayer();
            IPlayerStats stats = leagueManagerFactory.createPlayerStats();
            stats.setStrength(random.nextInt(100));
            String temp = positions[random.nextInt(positions.length)];
            stats.setPosition(temp);
            player.setPlayerStats(stats);
            team2.addPlayer(player);
        }
        division.addTeam(team1);
        division.addTeam(team2);
        conference.addDivision(division);
        league.addConference(conference);

        for (int i = 0; i < 10; i++) {
            ITeamPlayer player = leagueManagerFactory.createTeamPlayer();
            IPlayerStats stats = leagueManagerFactory.createPlayerStats();
            stats.setStrength(random.nextInt(100));
            String temp = positions[random.nextInt(positions.length)];
            stats.setPosition(temp);
            player.setPlayerStats(stats);
            newteam1.addPlayer(player);
        }

        for (int i = 0; i < 10; i++) {
            ITeamPlayer player = leagueManagerFactory.createTeamPlayer();
            IPlayerStats stats = leagueManagerFactory.createPlayerStats();
            stats.setStrength(random.nextInt(100));
            String temp = positions[random.nextInt(positions.length)];
            stats.setPosition(temp);
            player.setPlayerStats(stats);
            newteam2.addPlayer(player);
        }

        DivisionUpdatorAfterTrading testObj = tradingFactory.createDivisionUpdatorAfterTrading(league, team1, newteam1, team2, newteam2);
        for (ITeam team : division.getTeams()) {
            Assert.assertEquals(10, team.getPlayers().size());
        }
    }
}
