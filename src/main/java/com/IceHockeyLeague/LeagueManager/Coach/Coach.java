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
        return 0;
    }

    @Override
    public void setCoachID(int id) {

    }

    @Override
    public String getCoachName() {
        return null;
    }

    @Override
    public void setCoachName(String name) {

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
    public float getSkating() {
        return 0;
    }

    @Override
    public void setSkating(float value) {

    }

    @Override
    public float getShooting() {
        return 0;
    }

    @Override
    public void setShooting(float value) {

    }

    @Override
    public float getChecking() {
        return 0;
    }

    @Override
    public void setChecking(float value) {

    }

    @Override
    public float getSaving() {
        return 0;
    }

    @Override
    public void setSaving(float value) {

    }

    @Override
    public boolean isValid() {
        return false;
    }
}
