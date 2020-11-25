package com.IceHockeyLeague.LeagueManager.Team.Roster;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerStats;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;

import java.util.List;

public class InactiveRoster implements IInactiveRoster {
    private ILeagueManagerFactory leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
    private List<ITeamPlayer> inactivePlayers;
    private List<IFreeAgent> agents;

    public List<ITeamPlayer> getInactivePlayers() {
        return inactivePlayers;
    }

    public void setInactivePlayers(List<ITeamPlayer> inactivePlayers) {
        this.inactivePlayers = inactivePlayers;
    }

    public List<IFreeAgent> getAgents() {
        return agents;
    }

    public void setAgents(List<IFreeAgent> agents) {
        this.agents = agents;
    }

    public ITeamPlayer getReplacementPlayer(ITeamPlayer player, String position) {
        boolean needAgent = true;
        float bestReplacementStrength = 0;
        ITeamPlayer bestReplacement = null;

        for (ITeamPlayer inactivePlayer : inactivePlayers) {
            if (inactivePlayer.getInjuredStatus()) {

            } else {
                if (inactivePlayer.getPlayerStats().getStrength() > bestReplacementStrength &&
                        inactivePlayer.getPlayerStats().getPosition().equals(position)) {
                    bestReplacementStrength = inactivePlayer.getPlayerStats().getStrength();
                    needAgent = false;
                    bestReplacement = inactivePlayer;
                }
            }
        }

        if (needAgent) {
            IFreeAgent bestAgent = null;
            float strength = 0;
            ITeamPlayer bestReplacementFromAgent = leagueManagerFactory.createTeamPlayer();
            IPlayerStats stats = leagueManagerFactory.createPlayerStats();
            bestReplacementFromAgent.setPlayerStats(stats);
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

            bestReplacement = bestAgent.convertToTeamPlayer(bestReplacementFromAgent);
            agents.remove(bestAgent);
            IFreeAgent agent = leagueManagerFactory.createFreeAgent();
            IPlayerStats playerStats = leagueManagerFactory.createPlayerStats();
            agent.setPlayerStats(playerStats);
            agent = player.convertToFreeAgent(agent);
            agents.add(agent);
        } else {
            inactivePlayers.remove(bestReplacement);
        }
        return bestReplacement;
    }

}
