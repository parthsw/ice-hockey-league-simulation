package com.IceHockeyLeague.LeagueManager.Coach;

import java.util.List;

public class Coach implements ICoach {
    private int coachId;
    private int teamId;
    private int leagueId;
    private String coachName;
    private ICoachStats coachStats;

    public Coach() {
        setDefaults();
    }

    private void setDefaults() {
        coachId = -1;
        teamId = -1;
        leagueId = -1;
    }

    @Override
    public int getCoachId() {
        return coachId;
    }

    @Override
    public void setCoachId(int coachId) {
        this.coachId = coachId;
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
    public int getTeamId() {
        return teamId;
    }

    @Override
    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Override
    public int getLeagueId() {
        return leagueId;
    }

    @Override
    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    @Override
    public ICoachStats getCoachStats() {
        return coachStats;
    }

    @Override
    public void setCoachStats(ICoachStats stats) {
        if (stats == null) {
            throw new IllegalArgumentException("Please provide a valid concrete implementation of ICoachStats");
        }
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

    @Override
    public boolean isCoachNameExist(List<ICoach> coaches, String coachName) {
        boolean isExist = false;
        for (ICoach coach : coaches) {
            if (coach.getCoachName().equalsIgnoreCase(coachName)) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }

    @Override
    public boolean isNullOrEmpty(String coachName) {
        return (coachName == null || coachName.equals(""));
    }
}
