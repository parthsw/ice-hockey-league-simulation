package com.IceHockeyLeague.LeagueManager.Manager;

import java.util.Hashtable;
import java.util.List;

public class Manager implements IManager {
    private int managerID;
    private String managerName;
    private int teamID;
    private int leagueID;
    private Hashtable<String, Float> managerPersonality;

    public Manager() {
        setDefaults();
    }

    private void setDefaults() {
        managerID = -1;
        teamID = -1;
        leagueID = -1;
        this.managerPersonality = new Hashtable<>();
        this.managerPersonality.put("shrewd", -0.1f);
        this.managerPersonality.put("gambler", 0.1f);
        this.managerPersonality.put("normal", 0.0f);
    }

    @Override
    public Hashtable<String, Float> getManagerPersonality() {
        return this.managerPersonality;
    }

    @Override
    public void setManagerPersonality(Hashtable<String, Float> personality) {
        managerPersonality = personality;
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

    @Override
    public boolean saveTeamManager(IManagerPersistence managerDB) {
        return managerDB.saveTeamManager(this);
    }

    @Override
    public boolean saveLeagueManager(IManagerPersistence managerDB) {
        return managerDB.saveLeagueManager(this);
    }

    @Override
    public boolean loadTeamManager(IManagerPersistence managerDB, IManager manager) {
        return managerDB.loadTeamManager( teamID,this);
    }

    @Override
    public boolean isNullOrEmpty(String managerName) {
        return (managerName == null || managerName.equals(""));
    }

    @Override
    public boolean isManagerNameExist(List<IManager> managers, String managerName) {
        boolean isExist = false;
        for(IManager m : managers){
            if(m.getManagerName().equalsIgnoreCase(managerName)){
                isExist = true;
                break;
            }
        }
        return isExist;
    }
}
