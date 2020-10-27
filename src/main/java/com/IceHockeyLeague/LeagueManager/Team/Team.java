package com.IceHockeyLeague.LeagueManager.Team;

import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
import com.IceHockeyLeague.LeagueManager.Manager.IManager;
import com.IceHockeyLeague.LeagueManager.Player.IPlayer;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayerPersistence;

import java.util.ArrayList;
import java.util.List;

public class Team implements ITeam {
    private int teamID;
    private String teamName;
    private boolean isUserCreated;
    private float teamStrength;
    private int divisionID;
    private List<ITeamPlayer> players;
    private IManager manager;
    private ICoach coach;

    public Team() {
        setDefaults();
    }

    private void setDefaults() {
        teamID = -1;
        divisionID = -1;
        teamStrength = 0f;
        isUserCreated = false;
        players = new ArrayList<>();
    }

    @Override
    public int getTeamID() {
        return teamID;
    }

    @Override
    public void setTeamID(int id) {
        teamID = id;
    }

    @Override
    public String getTeamName() {
        return teamName;
    }

    @Override
    public void setTeamName(String name) {
        teamName = name;
    }

    @Override
    public boolean getIsUserCreated() {
        return isUserCreated;
    }

    @Override
    public void setIsUserCreated(boolean userCreated) {
        isUserCreated = userCreated;
    }

    @Override
    public float getTeamStrength() {
        return teamStrength;
    }

    @Override
    public void setTeamStrength(float strength) {
        teamStrength = strength;
    }

    @Override
    public int getDivisionID() {
        return divisionID;
    }

    @Override
    public void setDivisionID(int id) {
        divisionID = id;
    }

    @Override
    public IPlayer getPlayerById(int id) {
        return null;
    }

    @Override
    public void addPlayer(ITeamPlayer player) {
        players.add(player);
    }

    @Override
    public List<ITeamPlayer> getPlayers() {
        return players;
    }

    @Override
    public void setPlayers(List<ITeamPlayer> players) {
        this.players = players;
    }

    @Override
    public ICoach getCoach() {
        return coach;
    }

    @Override
    public void setCoach(ICoach coach) {
        this.coach = coach;
    }

    @Override
    public IManager getManager() {
        return manager;
    }

    @Override
    public void setManager(IManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean saveTeam(ITeamPersistence teamDB) {
        return teamDB.saveTeam(this);
    }

    @Override
    public boolean loadPlayers(ITeamPlayerPersistence teamPlayerDB, List<ITeamPlayer> teamPlayers) {
        return teamPlayerDB.loadTeamPlayers(teamID, teamPlayers);
    }

    @Override
    public boolean isNullOrEmpty(String teamName) {
        if(teamName == null || teamName.equals("")){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean isTeamNameExist(List<ITeam> teams) {
            boolean isExist = false;
            for(ITeam t : teams){
                if(t.getTeamName().equalsIgnoreCase(teamName)) {
                    isExist = true;
                    break;
                }
            }
            return isExist;
    }

    public float calculateTeamStrength(ITeamStrengthCalculator teamStrength) {
        return teamStrength.calculate(players);
    }

    @Override
    public void setTeamStrength(float strength) {

    }
}
