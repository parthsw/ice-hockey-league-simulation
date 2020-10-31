package com.IceHockeyLeague.LeagueManager.Player;

import com.IceHockeyLeague.LeagueManager.League.ILeague;

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
    public boolean saveFreeAgent(IFreeAgentPersistence freeAgentDB) {
        return freeAgentDB.saveFreeAgent(this);
    }

    @Override
    public boolean handleFreeAgentRetirement(IPlayerCareerProgression playerCareerProgression, ILeague league) {
        return playerCareerProgression.handleFreeAgentRetirement(this, league);
    }

}
