package com.IceHockeyLeague.LeagueManager.FreeAgent;

import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Player.*;

import java.util.List;

public class FreeAgent extends Player implements IFreeAgent {
    private int freeAgentId;
    private int leagueId;

    public FreeAgent() {
        setDefaults();
    }

    private void setDefaults() {
        freeAgentId = -1;
        leagueId = -1;
    }

    @Override
    public int getFreeAgentId() {
        return freeAgentId;
    }

    @Override
    public void setFreeAgentId(int freeAgentId) {
        this.freeAgentId = freeAgentId;
    }

    @Override
    public int getLeagueId() {
        return leagueId;
    }

    @Override
    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    @Override
    public ITeamPlayer convertToTeamPlayer(ITeamPlayer teamPlayer) {
        super.convertBetweenPlayerTypes(teamPlayer);
        teamPlayer.setTeamPlayerId(-1);
        teamPlayer.setTeamId(-1);
        teamPlayer.setIsCaptain(false);
        return teamPlayer;
    }

    @Override
    public void generateFreeAgent(IPlayer player) {
        this.setPlayerName(player.getPlayerName());
        this.setPlayerStats(player.getPlayerStats());
        this.setPlayerAgeInfo(player.getPlayerAgeInfo());
    }

    @Override
    public IFreeAgent bestFreeAgentForPosition(List<IFreeAgent> freeAgents, String position) {
        if (freeAgents == null) {
            throw new IllegalArgumentException("Please provide valid list of freeAgents");
        }

        float bestStrength = 0;
        IFreeAgent bestFreeAgent = null;
        if (freeAgents.size() > 0) {
            for (IFreeAgent freeAgent : freeAgents) {
                IPlayerStats freeAgentStats = freeAgent.getPlayerStats();
                if (freeAgentStats.getPosition().equalsIgnoreCase(position)) {
                    if (freeAgentStats.getStrength() > bestStrength) {
                        bestStrength = freeAgentStats.getStrength();
                        bestFreeAgent = freeAgent;
                    }
                }
            }
        }
        return bestFreeAgent;
    }

    @Override
    public boolean handleFreeAgentRetirement(IPlayerCareerProgression playerCareerProgression, ILeague league) {
        return playerCareerProgression.handleFreeAgentRetirement(this, league);
    }

}
