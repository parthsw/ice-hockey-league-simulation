package com.IceHockeyLeague.Trading;

import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgent;

import java.util.List;

public class GetBestAgent implements IGetBestAgent {

    private IFreeAgent bestFreeAgent;

    public IFreeAgent getBestAgentWithPosition(List<IFreeAgent> agents, String position) {
        int bestStrength = -1;
        for (IFreeAgent agent : agents) {
            if (agent.getPlayerStats().getPosition().equalsIgnoreCase(position) && bestStrength < agent.getPlayerStats().getStrength()) {
                this.bestFreeAgent = agent;
                bestStrength = (int) agent.getPlayerStats().getStrength();
            }
        }
        return this.bestFreeAgent;
    }

    public IFreeAgent getRandomBestAgentSkater(List<IFreeAgent> agents) {
        int bestStrength = -1;
        for (IFreeAgent agent : agents) {
            if ((agent.getPlayerStats().getPosition().equalsIgnoreCase("forward") || agent.getPlayerStats().getPosition().equalsIgnoreCase("defence")) && bestStrength < agent.getPlayerStats().getStrength()) {
                this.bestFreeAgent = agent;
                bestStrength = (int) agent.getPlayerStats().getStrength();
            }
        }
        return this.bestFreeAgent;
    }
}
