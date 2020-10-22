package com.IceHockeyLeague.LeagueManager.Player;

public class TeamPlayer extends Player implements ITeamPlayer {
    private int teamPlayerID;
    private boolean isCaptain;
    private int teamID;

    public TeamPlayer() {
        setDefaults();
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
    public boolean isValid() {
        return false;
    }

    private void setDefaults() {
        teamPlayerID = -1;
        teamID = -1;
    }
}
