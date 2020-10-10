package asdc.hl.leaguemodel.models;

import asdc.hl.leaguemodel.IPersistence;

public class Manager implements IManager{
    private int managerID;
    private String managerName;
    private int teamID;

    private IPersistence db;

    public Manager(IPersistence p) {
        this();
        db = p;
        // teamID = teamId;
    }

    public Manager() {
        managerID = -1;
        managerName = null;
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

    private boolean isNullOrEmpty(String s) {
        return s == null || s.equals("");
    }

    @Override
    public String validateName() {
        String validity = "Valid";
        if (isNullOrEmpty(managerName)) {
            validity = "Manager name is blank";
        }
        return validity;
    }

    @Override
    public void save() {
        db.saveManager(this);
    }

    @Override
    public void load() {
        db.loadManagerWithName(managerName, this);
    }
}
