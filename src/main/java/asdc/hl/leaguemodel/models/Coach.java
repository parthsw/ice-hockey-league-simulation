package asdc.hl.leaguemodel.models;

import asdc.hl.leaguemodel.IPersistence;

public class Coach implements ICoach{

    private int coachID;
    private String coachName;
    private int teamID;

    private IPersistence db;

    public Coach(IPersistence p, int teamId) {
        this();
        teamID = teamId;
        db = p;
    }

    public Coach() {
        coachID = -1;
        coachName = null;
    }

    @Override
    public int getCoachID() {
        return coachID;
    }

    @Override
    public void setCoachID(int id) {
        coachID = id;
    }

    @Override
    public String getCoachName() {
        return coachName;
    }

    @Override
    public void setCoachName(String name) {
        coachName = name;
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
        if (isNullOrEmpty(coachName)) {
            validity = "Coach name is blank";
        }
        return validity;
    }

    @Override
    public void save() {
        db.saveCoach(this);
    }

    @Override
    public void load() {
        db.loadCoachWithName(coachName, this);
    }
}
