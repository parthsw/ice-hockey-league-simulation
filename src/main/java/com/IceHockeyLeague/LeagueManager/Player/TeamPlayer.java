package com.IceHockeyLeague.LeagueManager.Player;

import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

public class TeamPlayer extends Player implements ITeamPlayer {
    private int teamPlayerID;
    private boolean isCaptain;
    private int teamID;

    public TeamPlayer() {
        setDefaults();
    }

    private void setDefaults() {
        teamPlayerID = -1;
        teamID = -1;
    }

    @Override
    public int getTeamPlayerID() {
        return teamPlayerID;
    }

    @Override
    public void setTeamPlayerID(int teamPlayerID) {
        this.teamPlayerID = teamPlayerID;
    }

    @Override
    public boolean isCaptain() {
        return isCaptain;
    }

    @Override
    public void setIsCaptain(boolean captain) {
        isCaptain = captain;
    }

    @Override
    public int getTeamID() {
        return teamID;
    }

    @Override
    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    @Override
    public IFreeAgent convertToFreeAgent(IFreeAgent freeAgent) {
        super.convertBetweenPlayerTypes(freeAgent);
        freeAgent.setFreeAgentID(-1);
        freeAgent.setLeagueID(-1);
        return freeAgent;
    }

    @Override
    public void generateTeamPlayer(IPlayer player) {
        this.setPlayerName(player.getPlayerName());
        this.setPlayerStats(player.getPlayerStats());
        this.setPlayerAgeInfo(player.getPlayerAgeInfo());
        this.setIsCaptain(false);
    }

    @Override
    public boolean saveTeamPlayer(ITeamPlayerPersistence teamPlayerDB) {
        return teamPlayerDB.saveTeamPlayer(this);
    }

    @Override
    public boolean handleTeamPlayerRetirement(IPlayerCareerProgression playerCareerProgression, ITeam team, ILeague league) {
        return playerCareerProgression.handleTeamPlayerRetirement(this, team, league);
    }
}
