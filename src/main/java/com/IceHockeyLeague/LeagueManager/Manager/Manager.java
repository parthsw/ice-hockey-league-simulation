package com.IceHockeyLeague.LeagueManager.Manager;

public class Manager implements IManager {
    private int managerID;
    private String managerName;
    private int teamID;
    private int leagueID;

    @Override
    public int getManagerID() {
        return 0;
    }

    @Override
    public void setManagerID(int id) {

    }

    @Override
    public String getManagerName() {
        return null;
    }

    @Override
    public void setManagerName(String name) {

    }

    @Override
    public int getTeamID() {
        return 0;
    }

    @Override
    public void setTeamID(int id) {

    }

    @Override
    public int getLeagueID() {
        return 0;
    }

    @Override
    public void setLeagueID(int id) {

    }

    @Override
    public boolean isValid() {
        return false;
    }
}
