package com.IceHockeyLeague.LeagueManager.Conference;

import com.IceHockeyLeague.LeagueManager.Division.IDivision;

import java.util.List;

public class Conference implements IConference {
    private int conferenceID;
    private String conferenceName;
    private int leagueID;
    private List<IDivision> divisions;

    @Override
    public int getConferenceID() {
        return 0;
    }

    @Override
    public void setConferenceID(int id) {

    }

    @Override
    public String getConferenceName() {
        return null;
    }

    @Override
    public void setConferenceName(String name) {

    }

    @Override
    public int getLeagueID() {
        return 0;
    }

    @Override
    public void setLeagueID(int id) {

    }

    @Override
    public IDivision getDivisionById(int id) {
        return null;
    }

    @Override
    public void addDivision(IDivision division) {

    }

    @Override
    public List<IDivision> getDivisions() {
        return null;
    }

    @Override
    public void setDivisions(List<IDivision> divisions) {

    }

    @Override
    public boolean isValid() {
        return false;
    }
}
