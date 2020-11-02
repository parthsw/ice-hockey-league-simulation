package com.tradingTest;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Conference.Conference;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.Division;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.League;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerStats;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Player.PlayerStats;
import com.IceHockeyLeague.LeagueManager.Player.TeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Team.Team;
import com.IceHockeyLeagueTest.LeagueManagerTest.TestLeagueManagerFactory;
import com.Trading.DivisionUpdatorAfterTrading;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;


public class DivisionUpdatorAfterTradingTest {

    @BeforeClass
    public static void setup() {
        AbstractLeagueManagerFactory.setFactory(new TestLeagueManagerFactory());
    }

    @Test
    public void divisionUpdatorAfterTradingTest() {
        ILeague league = new League();
        IConference conference = new Conference();
        IDivision division = new Division();
        ITeam team1 = new Team();
        ITeam team2 = new Team();

        ITeam newteam1 = new Team();
        ITeam newteam2 = new Team();

        Random random = new Random();
        String[] positions = new String[]{"Forward", "Defence", "Goalie"};
        for (int i = 0; i < 20; i++) {
            ITeamPlayer player = new TeamPlayer();
            IPlayerStats stats = new PlayerStats();
            stats.setStrength(random.nextInt(100));
            String temp = positions[random.nextInt(positions.length)];
            stats.setPosition(temp);
            player.setPlayerStats(stats);
            team1.addPlayer(player);
        }

        for (int i = 0; i < 20; i++) {
            ITeamPlayer player = new TeamPlayer();
            IPlayerStats stats = new PlayerStats();
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
            ITeamPlayer player = new TeamPlayer();
            IPlayerStats stats = new PlayerStats();
            stats.setStrength(random.nextInt(100));
            String temp = positions[random.nextInt(positions.length)];
            stats.setPosition(temp);
            player.setPlayerStats(stats);
            newteam1.addPlayer(player);
        }

        for (int i = 0; i < 10; i++) {
            ITeamPlayer player = new TeamPlayer();
            IPlayerStats stats = new PlayerStats();
            stats.setStrength(random.nextInt(100));
            String temp = positions[random.nextInt(positions.length)];
            stats.setPosition(temp);
            player.setPlayerStats(stats);
            newteam2.addPlayer(player);
        }

        DivisionUpdatorAfterTrading testObj = new DivisionUpdatorAfterTrading(league, team1, newteam1, team2, newteam2);
        for (ITeam team : division.getTeams()) {
            Assert.assertEquals(10, team.getPlayers().size());
        }

    }

}
