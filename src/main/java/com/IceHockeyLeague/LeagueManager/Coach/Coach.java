package com.IceHockeyLeague.LeagueManager.Coach;

public class Coach implements ICoach {
    private int coachID;
    private String coachName;
    private int teamID;
    private int leagueID;
    private ICoachStats coachStats;

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
    public ICoachStats getCoachStats() {
        return coachStats;
    }

    @Override
    public void setCoachStats(ICoachStats stats) {
        coachStats = stats;
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
