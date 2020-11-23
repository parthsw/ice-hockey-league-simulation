package com.IceHockeyLeague.LeagueManager.Team;

import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;

import java.util.List;

public class TeamRoster {
    private List<ITeamPlayer> players;
    private List<IFreeAgent> agents;
    private int leagueID;
    private int teamID;
    private int numberOfSkaters = 26;
    private int numberOfKeepers = 4;
    private String goalie = "Goalie";
    private String forward = "Forward";
    private String defence = "Defence";

    public TeamRoster(int teamID, int leagueID, List<ITeamPlayer> players, List<IFreeAgent> agents) {
        this.teamID = teamID;
        this.players = players;
        this.leagueID = leagueID;
        this.agents = agents;
    }

    private IFreeAgent getBestAgentWithPosition(String position) {
        IFreeAgent bestAgent = null;
        float strength = 0;
        for (IFreeAgent agent : this.agents) {
            if (agent.getPlayerStats().getPosition().equals(position)) {
                if (agent.getPlayerStats().getStrength() > strength) {
                    bestAgent = agent;
                    strength = agent.getPlayerStats().getStrength();
                }
            }
        }
        return bestAgent;
    }

    private ITeamPlayer getWorseTeamPlayerWithPosition(String position) {
        ITeamPlayer worseTeamPlayer = null;
        float strength = Float.MAX_VALUE;
        for (ITeamPlayer player : players) {
            if (player.getPlayerStats().getPosition().equals(position)) {
                if (player.getPlayerStats().getStrength() < strength) {
                    worseTeamPlayer = player;
                    strength = player.getPlayerStats().getStrength();
                }
            }
        }
        return worseTeamPlayer;
    }

    public void validateRoster() {
        int numberOfSkaters = 0;
        int numberOfKeepers = 0;

        for (ITeamPlayer player : this.players) {
            if (player.getPlayerStats().getPosition().equalsIgnoreCase(goalie)) {
                numberOfKeepers++;
            } else {
                numberOfSkaters++;
            }
        }

        if (numberOfKeepers > 4) {
            ITeamPlayer player = getWorseTeamPlayerWithPosition(goalie);
            this.players.remove(player);

        }
    }
}
