package com.IceHockeyLeague.LeagueManager.Player;

import com.IceHockeyLeague.LeagueManager.League.ILeague;

import java.util.List;

public class FreeAgent extends Player implements IFreeAgent {
    private int freeAgentID;
    private int leagueID;

    public FreeAgent() {
        setDefaults();
    }

    private void setDefaults() {
        freeAgentID = -1;
        leagueID = -1;
    }

    @Override
    public int getFreeAgentID() {
        return freeAgentID;
    }

    @Override
    public void setFreeAgentID(int freeAgentID) {
        this.freeAgentID = freeAgentID;
    }

    @Override
    public int getLeagueID() {
        return leagueID;
    }

    @Override
    public void setLeagueID(int leagueID) {
        this.leagueID = leagueID;
    }

    @Override
    public ITeamPlayer convertToTeamPlayer(ITeamPlayer teamPlayer) {
        super.convertBetweenPlayerTypes(teamPlayer);
        teamPlayer.setTeamPlayerID(-1);
        teamPlayer.setTeamID(-1);
        teamPlayer.setIsCaptain(false);
        return teamPlayer;
    }

    @Override
    public IFreeAgent bestFreeAgentForPosition(List<IFreeAgent> freeAgents, String position) {
        float bestStrength = 0;
        IFreeAgent bestFreeAgent = null;
        if(freeAgents.size() > 0) {
            for (IFreeAgent freeAgent: freeAgents) {
                IPlayerStats freeAgentStats = freeAgent.getPlayerStats();
                if(freeAgentStats.getPosition().equalsIgnoreCase(position)) {
                    if(freeAgentStats.getStrength() > bestStrength) {
                        bestStrength = freeAgentStats.getStrength();
                        bestFreeAgent = freeAgent;
                    }
                }
            }
        }
        return bestFreeAgent;
    }
}
