package com.IceHockeyLeague.LeagueManager.Player;

import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

public class TeamPlayer extends Player implements ITeamPlayer {
    private int teamPlayerId;
    private boolean isCaptain;
    private int teamId;

    public TeamPlayer() {
        setDefaults();
    }

    private void setDefaults() {
        teamPlayerId = -1;
        teamId = -1;
    }

    @Override
    public int getTeamPlayerId() {
        return teamPlayerId;
    }

    @Override
    public void setTeamPlayerId(int teamPlayerId) {
        this.teamPlayerId = teamPlayerId;
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
    public int getTeamId() {
        return teamId;
    }

    @Override
    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Override
    public IFreeAgent convertToFreeAgent(IFreeAgent freeAgent) {
        super.convertBetweenPlayerTypes(freeAgent);
        freeAgent.setFreeAgentId(-1);
        freeAgent.setLeagueId(-1);
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
    public void generatePlayer(IPlayer player) {
        player.setPlayerName(this.getPlayerName());
        player.setPlayerStats(this.getPlayerStats());
        player.setPlayerAgeInfo(this.getPlayerAgeInfo());
    }

    @Override
    public boolean handleTeamPlayerRetirement(IPlayerCareerProgression playerCareerProgression, ITeam team, ILeague league) {
        return playerCareerProgression.handleTeamPlayerRetirement(this, team, league);
    }

}
