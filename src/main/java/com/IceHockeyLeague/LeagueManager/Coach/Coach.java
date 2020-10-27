package com.IceHockeyLeague.LeagueManager.Coach;

import java.util.List;

public class Coach implements ICoach {
    private int coachID;
    private String coachName;
    private int teamID;
    private int leagueID;
    private ICoachStats coachStats;

    public Coach() {
        setDefaults();
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
    public boolean isCoachNameExist(List<ICoach> coaches, String coachName) {
        boolean isExist = false;
        for(ICoach c : coaches){
            if(c.getCoachName().equalsIgnoreCase(coachName)){
                isExist = true;
                break;
            }
        }
        return isExist;
    }

    @Override
    public boolean isNullOrEmpty(String coachName) {
        if(coachName == null || coachName.equals("")){
            return true;
        }
        else{
            return false;
        }
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


    private void setDefaults() {
        coachID = -1;
        teamID = -1;
        leagueID = -1;
    }

    @Override
    public boolean saveCoach(ICoachPersistence coachDB) {
        return coachDB.saveCoach(this);
    }

    @Override
    public boolean loadTeamCoach(ICoachPersistence coachDB, ICoach coach) {
        return coachDB.loadTeamCoach(leagueID, teamID, this);
    }
}
