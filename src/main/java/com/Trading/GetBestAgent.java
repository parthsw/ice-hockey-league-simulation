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
            if(agent.getPlayerPosition().equals(position) && bestStrength<agent.getStrength()){
                this.bestFreeagent = agent;
                bestStrength = agent.getStrength();
            }
        }
        return this.bestFreeagent;
    }

    public ITeamPlayer getWorsePlayerInTeamWithPosition(List<ITeamPlayer> players, String position){
        int bestStrength = Integer.MAX_VALUE;
        for(ITeamPlayer player : players) {
            if (player.getPlayerPosition().equals(position) && bestStrength > player.getStrength()) {
                this.worseTeamPlayer = player;
                bestStrength = player.getStrength();
            }
        }
        return this.worseTeamPlayer;
    }

    public IFreeAgent getRandomBestAgentSkater(List<IFreeAgent> agents){
        int bestStrength = -1;
        for (IFreeAgent agent : agents){
            if((agent.getPlayerPosition().equals("Forward") || agent.getPlayerPosition().equals("Defence")) && bestStrength<agent.getStrength()){
                this.bestFreeagent = agent;
                bestStrength = agent.getStrength();
            }
        }
        return this.bestFreeagent;
    }

    public ITeamPlayer getWorseSkaterInTeam(List<ITeamPlayer> players){
        int bestStrength = Integer.MAX_VALUE;
        for(ITeamPlayer player : players) {
            if ((player.getPlayerPosition().equals("Forward") || player.getPlayerPosition().equals("Defence")) && bestStrength > player.getStrength()) {
                this.worseTeamPlayer = player;
                bestStrength = player.getStrength();
            }
        }
        return this.worseTeamPlayer;
    }

}
