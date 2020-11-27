package com.IceHockeyLeagueTest.LeagueManagerTest.TeamTest.RosterTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerStats;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateMockObjectForRosterTesting implements IGenerateMockObjectForRosterTesting {

    private static ILeagueManagerFactory leagueManagerFactory;


    public GenerateMockObjectForRosterTesting() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
    }

    @Override
    public List<IFreeAgent> getAgentList(int number) {
        String[] positions = new String[]{"Goalie", "Forward", "Defence"};
        Random random = new Random();
        List<IFreeAgent> agents = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            String temp = positions[random.nextInt(positions.length)];
            IFreeAgent agent = leagueManagerFactory.createFreeAgent();
            IPlayerStats stats = leagueManagerFactory.createPlayerStats();
            stats.setPosition(temp);
            stats.setStrength(random.nextInt(100));
            agent.setPlayerStats(stats);
            agents.add(agent);
        }

        return agents;
    }

    @Override
    public List<ITeamPlayer> getPlayerList(int number) {
        String[] positions = new String[]{"Goalie", "Forward", "Defence"};
        Random random = new Random();
        List<ITeamPlayer> players = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            String temp = positions[random.nextInt(positions.length)];
            ITeamPlayer player = leagueManagerFactory.createTeamPlayer();
            IPlayerStats stats = leagueManagerFactory.createPlayerStats();
            stats.setPosition(temp);
            stats.setStrength(random.nextInt(100));
            if (random.nextInt(100) < 50) {
                player.setInjuredStatus(true);
            }
            player.setPlayerStats(stats);
            players.add(player);
        }

        return players;
    }
}
