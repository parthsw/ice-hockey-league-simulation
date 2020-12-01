package com.IceHockeyLeagueTest.LeagueManagerTest.GameSimulatorTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.*;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.Random;

public class MockTeamGenerator {
    private static ILeagueManagerFactory leagueManagerFactory;

    public MockTeamGenerator() {
        setup();
    }

    private void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
    }

    public ITeam getTeamWithRandomPlayerStats() {
        IRandomChance randomChance = new RandomChance(new Random());
        ITeam team = leagueManagerFactory.createTeam();

        for (int i = 0; i < 20; i++) {
            ITeamPlayer teamPlayer = leagueManagerFactory.createTeamPlayer();
            IPlayerStats playerStats = leagueManagerFactory.createPlayerStats();
            playerStats.setChecking(randomChance.getRandomIntegerNumber(1,20));
            playerStats.setSaving(randomChance.getRandomIntegerNumber(1,20));
            playerStats.setSkating(randomChance.getRandomIntegerNumber(1,20));
            playerStats.setShooting(randomChance.getRandomIntegerNumber(1,20));
            teamPlayer.setPlayerStats(playerStats);
            teamPlayer.setPlayerName("abc");
            team.addPlayer(teamPlayer);
        }

        for (int i = 0; i < 10; i++) {
            team.getPlayers().get(i).getPlayerStats().setPosition(PlayerPosition.FORWARD.toString());
        }
        for (int i = 10; i < 18; i++) {
            team.getPlayers().get(i).getPlayerStats().setPosition(PlayerPosition.DEFENSE.toString());
        }
        for (int i = 18; i < 20; i++) {
            team.getPlayers().get(i).getPlayerStats().setPosition(PlayerPosition.GOALIE.toString());
        }

        return team;
    }

    public ITeam getTeamWithAllStatsAsOne() {
        ITeam team = leagueManagerFactory.createTeam();

        for (int i = 0; i < 20; i++) {
            ITeamPlayer teamPlayer = leagueManagerFactory.createTeamPlayer();
            IPlayerStats playerStats = leagueManagerFactory.createPlayerStats();
            playerStats.setChecking(1);
            playerStats.setSaving(1);
            playerStats.setSkating(1);
            playerStats.setShooting(1);
            teamPlayer.setPlayerStats(playerStats);
            teamPlayer.setPlayerName("abc");
            team.addPlayer(teamPlayer);
        }

        for (int i = 0; i < 10; i++) {
            team.getPlayers().get(i).getPlayerStats().setPosition(PlayerPosition.FORWARD.toString());
        }
        for (int i = 10; i < 18; i++) {
            team.getPlayers().get(i).getPlayerStats().setPosition(PlayerPosition.DEFENSE.toString());
        }
        for (int i = 18; i < 20; i++) {
            team.getPlayers().get(i).getPlayerStats().setPosition(PlayerPosition.GOALIE.toString());
        }

        return team;
    }

    public ITeam getTeamWithAllStatsAsTwenty() {
        ITeam team = leagueManagerFactory.createTeam();

        for (int i = 0; i < 20; i++) {
            ITeamPlayer teamPlayer = leagueManagerFactory.createTeamPlayer();
            IPlayerStats playerStats = leagueManagerFactory.createPlayerStats();
            playerStats.setChecking(20);
            playerStats.setSaving(20);
            playerStats.setSkating(20);
            playerStats.setShooting(20);
            teamPlayer.setPlayerStats(playerStats);
            teamPlayer.setPlayerName("abc");
            team.addPlayer(teamPlayer);
        }

        for (int i = 0; i < 10; i++) {
            team.getPlayers().get(i).getPlayerStats().setPosition(PlayerPosition.FORWARD.toString());
        }
        for (int i = 10; i < 18; i++) {
            team.getPlayers().get(i).getPlayerStats().setPosition(PlayerPosition.DEFENSE.toString());
        }
        for (int i = 18; i < 20; i++) {
            team.getPlayers().get(i).getPlayerStats().setPosition(PlayerPosition.GOALIE.toString());
        }

        return team;
    }
}
