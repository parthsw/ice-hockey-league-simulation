package com.IceHockeyLeague.LeagueManager.Player;

import java.util.List;

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

    private void setDefaults() {
        teamPlayerID = -1;
        teamID = -1;
    }

    @Override
    public boolean saveTeamPlayer(ITeamPlayerPersistence teamPlayerDB) {
        return teamPlayerDB.saveTeamPlayer(this);
    }

    @Override
    public boolean checkTeamPlayers(List<ITeamPlayer> players) {
        int skaterCounter = 0;
        int goalieCounter = 0;
        boolean listIsPerfect = false;
        for(ITeamPlayer p : players){
            if(p.getPlayerStats().getPosition() == "goalie"){
                goalieCounter++;
            }
            else{
                skaterCounter++;
            }
        }
        if(skaterCounter == 1 && goalieCounter == 1){
            listIsPerfect = true;
        }
        return listIsPerfect;
    }
}
