package com.Trading;

import com.IceHockeyLeague.LeagueManager.Player.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;

import java.util.List;

public class GetBestAgent {
    private IFreeAgent bestFreeagent;
    private ITeamPlayer worseTeamPlayer;

    public IFreeAgent getBestAgentWithPosition(List<IFreeAgent> agents, String position){
        int bestStrength = -1;
        for (IFreeAgent agent : agents){
            if (agent.getPlayerStats().getPosition().equals(position) && bestStrength < agent.getPlayerStats().getStrength()) {
                this.bestFreeagent = agent;
                bestStrength = agent.getPlayerStats().getStrength();
            }
        }
        return this.bestFreeagent;
    }

    public ITeamPlayer getWorsePlayerInTeamWithPosition(List<ITeamPlayer> players, String position){
        int bestStrength = Integer.MAX_VALUE;
        for(ITeamPlayer player : players) {
            if (player.getPlayerStats().getPosition().equals(position) && bestStrength > player.getPlayerStats().getStrength()) {
                this.worseTeamPlayer = player;
                bestStrength = player.getPlayerStats().getStrength();
            }
        }
        return this.worseTeamPlayer;
    }

    public IFreeAgent getRandomBestAgentSkater(List<IFreeAgent> agents){
        int bestStrength = -1;
        for (IFreeAgent agent : agents){
            if ((agent.getPlayerStats().getPosition().equals("Forward") || agent.getPlayerStats().getPosition().equals("Defence")) && bestStrength < agent.getPlayerStats().getStrength()) {
                this.bestFreeagent = agent;
                bestStrength = agent.getPlayerStats().getStrength();
            }
        }
        return this.bestFreeagent;
    }

    public ITeamPlayer getWorseSkaterInTeam(List<ITeamPlayer> players){
        int bestStrength = Integer.MAX_VALUE;
        for(ITeamPlayer player : players) {
            if ((player.getPlayerStats().getPosition().equals("Forward") || player.getPlayerStats().getPosition().equals("Defence")) && bestStrength > player.getPlayerStats().getStrength()) {
                this.worseTeamPlayer = player;
                bestStrength = player.getPlayerStats().getStrength();
            }
        }
        return this.worseTeamPlayer;
    }

}
