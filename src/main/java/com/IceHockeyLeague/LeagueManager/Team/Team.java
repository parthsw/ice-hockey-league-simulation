package com.IceHockeyLeague.LeagueManager.Team;

import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
import com.IceHockeyLeague.LeagueManager.Manager.IManager;
import com.IceHockeyLeague.LeagueManager.Player.IPlayer;

import java.util.List;

public class Team implements ITeam {
    private int teamID;
    private String teamName;
    private int divisionID;
    private List<IPlayer> players;
    private IManager manager;
    private ICoach coach;

    @Override
    public int getTeamID() {
        return 0;
    }

    @Override
    public void setTeamID(int id) {

    }

    @Override
    public String getTeamName() {
        return null;
    }

    @Override
    public void setTeamName(String name) {

    }

    @Override
    public int getDivisionID() {
        return 0;
    }

    @Override
    public void setDivisionID(int id) {

    }

    @Override
    public IPlayer getPlayerById(int id) {
        return null;
    }

    @Override
    public void addPlayer(IPlayer player) {

    }

    @Override
    public List<IPlayer> getPlayers() {
        return null;
    }

    @Override
    public void setPlayers(List<IPlayer> players) {

    }

    @Override
    public ICoach getCoach() {
        return null;
    }

    @Override
    public void setCoach(ICoach coach) {

    }

    @Override
    public IManager getManager() {
        return null;
    }

    @Override
    public void setManager(IManager manager) {

    }

    @Override
    public boolean isValid() {
        return false;
    }
}
