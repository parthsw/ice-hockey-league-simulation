package com.IceHockeyLeague.Trading;

import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GetBestAgent implements IGetBestAgent {
    private final Logger LOGGER = LogManager.getLogger(GetBestAgent.class);

    private IFreeAgent bestFreeAgent;

    public IFreeAgent getBestAgentWithPosition(List<IFreeAgent> agents, String position) {
        int bestStrength = -1;
        if (agents.isEmpty()) {
            LOGGER.warn("Empty agent list provided");
        }

        for (IFreeAgent agent : agents) {
            if (agent.getPlayerStats().getPosition().equalsIgnoreCase(position) && bestStrength < agent.getPlayerStats().getStrength()) {
                this.bestFreeAgent = agent;
                bestStrength = (int) agent.getPlayerStats().getStrength();
            }
        }
        return this.bestFreeAgent;
    }

    public IFreeAgent getRandomBestAgentSkater(List<IFreeAgent> agents) {
        if (agents.isEmpty()) {
            LOGGER.warn("Empty agent list provided");
        }
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
