package com.IceHockeyLeague.LeagueManager.Team;

import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
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
    private int lossPoint;

    public Team() {
        setDefaults();
    }

    private static final String positionToCheck = "goalie";

    private void setDefaults() {
        teamID = -1;
        divisionID = -1;
        teamStrength = 0f;
        isUserCreated = false;
        players = new ArrayList<>();
        lossPoint = 0;
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
    public boolean removePlayer(ITeamPlayer player) {
        if(players.size() > 0) {
            return players.remove(player);
        }
        return false;
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
    public boolean checkTeamPlayers() {
        int skaterCounter = 0;
        int goalieCounter = 0;
        boolean listIsPerfect = false;
        for(ITeamPlayer p : players){
            if(p.getPlayerStats().getPosition().equalsIgnoreCase(positionToCheck)){
                goalieCounter++;
            }
            else{
                skaterCounter++;
            }
        }
        if(skaterCounter == 18 && goalieCounter == 2){
            listIsPerfect = true;
        }
        return listIsPerfect;
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
    public int getLossPointValue() {
        return this.lossPoint;
    }

    @Override
    public void setLossPointValue(int value) {
        this.lossPoint = value;
    }

    @Override
    public void incrementLossPointValue() {
        this.lossPoint = this.lossPoint + 1;
    }

    @Override
    public void decrementLossPointValue() {
        if (this.lossPoint > 0) {
            this.lossPoint = this.lossPoint - 1;
        }
    }

    @Override
    public boolean checkIfTeamNameExists(ITeamPersistence teamDB, String teamName, List<ILeague> leagues) {
        return teamDB.checkIfTeamNameExists(teamName, leagues);
    }

    @Override
    public boolean isNullOrEmpty(String teamName) {
        return (teamName == null || teamName.equals(""));
    }

    @Override
    public boolean isTeamNameExist(List<ITeam> teams,String teamName) {
        boolean isExist = false;
        for(ITeam t : teams) {
            if (t.getTeamName().equalsIgnoreCase(teamName)) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }

    @Override
    public float calculateTeamStrength(ITeamStrengthCalculator teamStrength) {
        return teamStrength.calculate(players);
    }

}
