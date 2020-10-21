package com.IceHockeyLeague.LeagueManager.Coach;

public class Coach implements ICoach {
    private int coachID;
    private String coachName;
    private int teamID;
    private int leagueID;
    private float skating;
    private float shooting;
    private float checking;
    private float saving;

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

    @Override
    public int getLeagueID() {
        return leagueID;
    }

    @Override
    public void setLeagueID(int id) {
        leagueID = id;
    }

    @Override
    public float getSkating() {
        return skating;
    }

    @Override
    public void setSkating(float value) {
        skating = value;
    }

    @Override
    public float getShooting() {
        return shooting;
    }

    @Override
    public void setShooting(float value) {
        shooting = value;
    }

    @Override
    public float getChecking() {
        return checking;
    }

    @Override
    public void setChecking(float value) {
        checking = value;
    }

    @Override
    public float getSaving() {
        return saving;
    }

    @Override
    public void setSaving(float value) {
        saving = value;
    }

    @Override
    public boolean isValid() {
        boolean valid = true;
        if (isNullOrEmpty(coachName)) {
            valid = false;
        }
        return valid;
    }

    private boolean isNullOrEmpty(String s) {
        return s == null || s.equals("");
    }

    @Override
    public boolean saveCoach(ICoachPersistence coachDB) {
        return coachDB.saveCoach(this);
    }
}
