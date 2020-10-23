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
    public boolean isValid() {
        return false;
    }

    @Override
    public String validateNameDuringCreate(List<IConference> conferences) {
        String validity = "VALID";
        if(isNullOrEmpty(conferenceName)){
            validity = "Conference name is blank";
        }
        else if(!isConferenceNameExist(conferences)){
            validity = conferenceName + " - The given conference does not exist, please enter a valid conference name";
        }
        return validity;
    }

    private boolean isNullOrEmpty(String s){
        return s == null || s.equals("");
    }

    private boolean isConferenceNameExist(List<IConference> conferences){
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
