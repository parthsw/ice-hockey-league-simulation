package com.tradingTest;

import com.IceHockeyLeague.LeagueManager.Conference.Conference;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.Division;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.League;
import com.IceHockeyLeague.LeagueManager.Player.*;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Team.Team;
import com.Trading.SimulateTrade;
import junit.framework.Assert;
import org.junit.Test;

import java.util.Random;


public class SimulateTradeTest {


    @Test
    public void simulateTradeTest() {
        ILeague league = new League();
        league.setLeagueID(1);
        IConference conference = new Conference();
        league.addConference(conference);
        IDivision division = new Division();
        conference.addDivision(division);
        Random random = new Random();
        String[] positions = new String[]{"Forward", "Defence", "Goalie"};
        for (int j = 0; j < 20; j++) {
            IFreeAgent player = new FreeAgent();
            player.setPlayerName("AgentPlayer");
            IPlayerStats stats = new PlayerStats();
            stats.setStrength(random.nextInt(1000));
            String temp = positions[random.nextInt(positions.length)];
            stats.setPosition(temp);
            player.setPlayerStats(stats);
            player.setLeagueID(1);
            league.addFreeAgent(player);
        }
        for (int i = 0; i < 10; i++) {
            ITeam team = new Team();
            team.setLossPointValue(1);
            for (int j = 0; j < 20; j++) {
                ITeamPlayer player = new TeamPlayer();
                IPlayerStats stats = new PlayerStats();
                stats.setStrength(random.nextInt(100));
                String temp = positions[random.nextInt(positions.length)];
                stats.setPosition(temp);
                player.setPlayerStats(stats);
                team.addPlayer(player);
            }
            division.addTeam(team);
        }
        ITeam team1 = new Team();
        team1.setTeamName("teamStrong");
        team1.setLossPointValue(0);
        for (int j = 0; j < 20; j++) {
            ITeamPlayer player = new TeamPlayer();
            player.setPlayerName("fromStrongTeam");
            IPlayerStats stats = new PlayerStats();
            stats.setStrength(1001);
            String temp = positions[random.nextInt(positions.length)];
            stats.setPosition(temp);
            player.setPlayerStats(stats);
            team1.addPlayer(player);
        }
        ITeam team2 = new Team();
        team2.setTeamName("teamWeak");
        team2.setLossPointValue(5);
        for (int j = 0; j < 20; j++) {
            ITeamPlayer player = new TeamPlayer();
            player.setPlayerName("fromWeakTeam");
            IPlayerStats stats = new PlayerStats();
            stats.setStrength(101);
            String temp = positions[random.nextInt(positions.length)];
            stats.setPosition(temp);
            player.setPlayerStats(stats);
            team2.addPlayer(player);
        }
        division.addTeam(team1);
        division.addTeam(team2);

        SimulateTrade simulation = new SimulateTrade();
        simulation.simulateTrade(league, 3, 5, 1);
        simulation.simulate();

        boolean flag1 = false;
        boolean flag2 = false;

        for (ITeamPlayer player : team1.getPlayers()) {
            if (player.getPlayerName().equals("fromStrongTeam")) ;
            flag1 = true;
            break;
        }
        Assert.assertTrue(flag1);
        for (ITeamPlayer player : team2.getPlayers()) {
            if (player.getPlayerName().equals("fromStrongTeam")) ;
            flag2 = true;
            break;
        }
        Assert.assertTrue(flag2);
    }

    @Test
    public void simulateTradeTest2() {
        ILeague league = new League();
        league.setLeagueID(1);
        IConference conference = new Conference();
        league.addConference(conference);
        IDivision division = new Division();
        conference.addDivision(division);
        Random random = new Random();
        String[] positions = new String[]{"Forward", "Defence", "Goalie"};
        for (int j = 0; j < 20; j++) {
            IFreeAgent player = new FreeAgent();
            player.setPlayerName("AgentPlayer");
            IPlayerStats stats = new PlayerStats();
            stats.setStrength(random.nextInt(100));
            String temp = positions[random.nextInt(positions.length)];
            stats.setPosition(temp);
            player.setPlayerStats(stats);
            player.setLeagueID(1);
            league.addFreeAgent(player);
        }
        for (int i = 0; i < 10; i++) {
            ITeam team = new Team();
            team.setLossPointValue(1);
            for (int j = 0; j < 20; j++) {
                ITeamPlayer player = new TeamPlayer();
                IPlayerStats stats = new PlayerStats();
                stats.setStrength(random.nextInt(100));
                String temp = positions[random.nextInt(positions.length)];
                stats.setPosition(temp);
                player.setPlayerStats(stats);
                team.addPlayer(player);
            }
            division.addTeam(team);
        }
        ITeam team1 = new Team();
        team1.setTeamName("teamStrong");
        team1.setIsUserCreated(true);
        team1.setLossPointValue(0);
        for (int j = 0; j < 20; j++) {
            ITeamPlayer player = new TeamPlayer();
            player.setPlayerName("fromStrongTeam");
            IPlayerStats stats = new PlayerStats();
            stats.setStrength(1001);
            String temp = positions[random.nextInt(positions.length)];
            stats.setPosition(temp);
            player.setPlayerStats(stats);
            team1.addPlayer(player);
        }
        ITeam team2 = new Team();
        team2.setTeamName("teamWeak");
        team2.setLossPointValue(5);
        for (int j = 0; j < 20; j++) {
            ITeamPlayer player = new TeamPlayer();
            player.setPlayerName("fromWeakTeam");
            IPlayerStats stats = new PlayerStats();
            stats.setStrength(random.nextInt(100));
            String temp = positions[random.nextInt(positions.length)];
            stats.setPosition(temp);
            player.setPlayerStats(stats);
            team2.addPlayer(player);
        }
        division.addTeam(team1);
        division.addTeam(team2);

        SimulateTrade simulation = new SimulateTrade();
        simulation.simulateTrade(league, 3, 5, 1);
        simulation.simulate();

        boolean flag1 = false;
        boolean flag2 = false;

        for (ITeamPlayer player : team1.getPlayers()) {
            if (player.getPlayerName().equals("fromStrongTeam")) ;
            flag1 = true;
            break;
        }
        Assert.assertTrue(flag1);
        for (ITeamPlayer player : team2.getPlayers()) {
            if (player.getPlayerName().equals("fromStrongTeam")) ;
            flag2 = true;
            break;
        }
        Assert.assertTrue(flag2);
    }
}
