package com.IceHockeyLeague.LeagueManager.Manager;

public class Manager implements IManager {
    private int managerID;
    private String managerName;
    private int teamID;
    private int leagueID;

    public Manager() {
        setDefaults();
    }

    @Override
    public int getManagerID() {
        return managerID;
    }

    @Override
    public void setManagerID(int id) {
        managerID = id;
    }

    @Override
    public String getManagerName() {
        return managerName;
    }

    @Override
    public void setManagerName(String name) {
        managerName = name;
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
    public int getLeagueID() {
        return leagueID;
    }

    @Override
    public void setLeagueID(int id) {
        leagueID = id;
    }

    private void setDefaults() {
        managerID = -1;
        teamID = -1;
        leagueID = -1;
    }

    @Override
    public boolean saveManager(IManagerPersistence managerDB) {
        return managerDB.saveManager(this);
    }

    @Override
    public boolean loadTeamManager(IManagerPersistence managerDB, IManager manager) {
        return managerDB.loadTeamManager(leagueID, teamID,this);
    }
}
