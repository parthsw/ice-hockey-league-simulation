package com.IceHockeyLeague.LeagueManager.Conference;

import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.Division.IDivisionPersistence;

import java.util.List;

public class Conference implements IConference {
    private int conferenceID;
    private String conferenceName;
    private int leagueID;
    private List<IDivision> divisions;

    @Override
    public int getConferenceID() {
        return conferenceID;
    }

    @Override
    public void setConferenceID(int id) {
        conferenceID = id;
    }

    @Override
    public String getConferenceName() {
        return conferenceName;
    }

    @Override
    public void setConferenceName(String name) {
        conferenceName = name;
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
    public IDivision getDivisionById(int id) {
        return null;
    }

    @Override
    public void addDivision(IDivision division) {
        divisions.add(division);
    }

    @Override
    public List<IDivision> getDivisions() {
        return divisions;
    }

    @Override
    public void setDivisions(List<IDivision> divisions) {
        this.divisions = divisions;
    }

    @Override
    public boolean saveConference(IConferencePersistence conferenceDB) {
        return conferenceDB.saveConference(this);
    }

    @Override
    public boolean loadDivisions(IDivisionPersistence divisionDB, List<IDivision> divisions) {
        return divisionDB.loadDivisions(conferenceID, divisions);
    }

    @Override
    public boolean isNullOrEmpty(String conferenceName) {
        if(conferenceName == null || conferenceName.equals("")){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean isConferenceNameExist(List<IConference> conferences,String conferenceName) {
        boolean isExist = false;
        for(IConference c : conferences){
            if(c.getConferenceName().equalsIgnoreCase(conferenceName)){
                isExist = true;
                break;
            }
        }
        return isExist;
    }
}
