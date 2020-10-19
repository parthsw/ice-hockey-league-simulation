package com.IceHockeyLeague.LeagueManager.Division;

import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.List;

public class Division implements IDivision {
    private int divisionID;
    private String divisionName;
    private int conferenceID;
    private List<ITeam> teams;

    @Override
    public int getDivisionID() {
        return 0;
    }

    @Override
    public void setDivisionID(int id) {

    }

    @Override
    public String getDivisionName() {
        return null;
    }

    @Override
    public void setDivisionName(String name) {

    }

    @Override
    public int getConferenceID() {
        return 0;
    }

    @Override
    public void setConferenceID(int id) {

    }

    @Override
    public ITeam getTeamById(int id) {
        return null;
    }

    @Override
    public void addTeam(ITeam team) {

    }

    @Override
    public List<ITeam> getTeams() {
        return null;
    }

    @Override
    public void setTeams(List<ITeam> teams) {

    }

    @Override
    public boolean isValid() {
        return false;
    }
}
