package com.IceHockeyLeague.LeagueManager.Team.TeamRoster;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerStats;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;

import java.util.List;

public class TeamRoster implements ITeamRoster {

    private List<ITeamPlayer> players;
    private List<IFreeAgent> agents;
    private int numberOfSkaters = 26;
    private int numberOfKeepers = 4;
    private String goalie = "Goalie";
    private String forward = "Forward";
    private String defence = "Defence";
    private ILeagueManagerFactory leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();

    public void setPlayers(List<ITeamPlayer> players) {
        this.players = players;
    }

    public void setAgents(List<IFreeAgent> agents) {
        this.agents = agents;
    }

    private IFreeAgent getBestAgentWithPosition(String position, List<IFreeAgent> agents) {
        IFreeAgent bestAgent = null;
        float strength = 0;
        for (IFreeAgent agent : agents) {
            if (agent.getInjuredStatus()) {
            } else {
                if (agent.getPlayerStats().getPosition().equals(position)) {
                    if (agent.getPlayerStats().getStrength() > strength) {
                        bestAgent = agent;
                        strength = agent.getPlayerStats().getStrength();
                    }
                }
            }

        }
        return bestAgent;
    }

    private ITeamPlayer getWorseTeamPlayerWithPosition(String position, List<ITeamPlayer> players) {
        ITeamPlayer worseTeamPlayer = null;
        float strength = Float.MAX_VALUE;
        for (ITeamPlayer player : players) {
            if (player.getInjuredStatus()) {
            } else {
                if (player.getPlayerStats().getPosition().equals(position)) {
                    if (player.getPlayerStats().getStrength() < strength) {
                        worseTeamPlayer = player;
                        strength = player.getPlayerStats().getStrength();
                    }
                }
            }

        }
        return worseTeamPlayer;
    }

    public void validateRoster() {
        numberOfSkaters = 0;
        numberOfKeepers = 0;

        for (ITeamPlayer player : this.players) {
            if (player.getPlayerStats().getPosition().equalsIgnoreCase(goalie)) {
                numberOfKeepers++;
            } else {
                numberOfSkaters++;
            }
        }

        if (numberOfKeepers > 4) {
            int difference = numberOfKeepers - 4;
            for (int i = 0; i < difference; i++) {
                ITeamPlayer player = getWorseTeamPlayerWithPosition(goalie, this.players);
                this.players.remove(player);
                IFreeAgent agent = leagueManagerFactory.createFreeAgent();
                IPlayerStats stats = leagueManagerFactory.createPlayerStats();
                agent.setPlayerStats(stats);
                agent = player.convertToFreeAgent(agent);
                this.agents.add(agent);
            }
        }

        if (numberOfSkaters > 26) {
            int difference = numberOfSkaters - 4;
            for (int i = 0; i < difference; i++) {
                ITeamPlayer player1 = getWorseTeamPlayerWithPosition(forward, this.players);
                ITeamPlayer player2 = getWorseTeamPlayerWithPosition(defence, this.players);
                ITeamPlayer player;
                if (player1.getPlayerStats().getStrength() < player2.getPlayerStats().getStrength()) {
                    player = player1;
                } else {
                    player = player2;
                }
                this.players.remove(player);
                IFreeAgent agent = leagueManagerFactory.createFreeAgent();
                IPlayerStats stats = leagueManagerFactory.createPlayerStats();
                agent.setPlayerStats(stats);
                agent = player.convertToFreeAgent(agent);
                this.agents.add(agent);
            }
        }

        if (numberOfKeepers < 4) {
            int difference = 4 - numberOfKeepers;
            for (int i = 0; i < difference; i++) {
                IFreeAgent agent = getBestAgentWithPosition(goalie, this.agents);
                ITeamPlayer player = leagueManagerFactory.createTeamPlayer();
                IPlayerStats stats = leagueManagerFactory.createPlayerStats();
                player.setPlayerStats(stats);
                player = agent.convertToTeamPlayer(player);
                this.agents.remove(agent);
                this.players.add(player);
            }
        }

        if (numberOfSkaters < 26) {
            int difference = 26 - numberOfSkaters;
            for (int i = 0; i < difference; i++) {
                IFreeAgent agent1 = getBestAgentWithPosition(forward, this.agents);
                IFreeAgent agent2 = getBestAgentWithPosition(defence, this.agents);
                IFreeAgent agent;
                if (agent1.getPlayerStats().getStrength() < agent2.getPlayerStats().getStrength()) {
                    agent = agent1;
                } else {
                    agent = agent2;
                }
                ITeamPlayer player = leagueManagerFactory.createTeamPlayer();
                IPlayerStats stats = leagueManagerFactory.createPlayerStats();
                player.setPlayerStats(stats);
                player = agent.convertToTeamPlayer(player);
                this.agents.remove(agent);
                this.players.add(player);
            }
        }


    }
}
