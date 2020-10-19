package com.leaguemodel.models;

import com.leaguemodel.IPersistence;

import java.util.ArrayList;
import java.util.List;

public class Conference implements IConference {

    private int conferenceID;
    private String conferenceName;
    private int leagueID;
    private List<IDivision> divisions;

    private IPersistence db;

    public Conference(IPersistence p) {
        this();
        // leagueID = leagueId;
        db = p;
    }

    public Conference() {
        conferenceID = -1;
        conferenceName = null;
        leagueID = -1;
        divisions = new ArrayList<IDivision>();
    }

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
    public IDivision getDivisionByName(String name) {
        IDivision div = null;
        for (IDivision d: divisions) {
            if (d.getDivisionName().equalsIgnoreCase(name)) {
                div = d;
                break;
            }
        }
        return div;
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

    private boolean isNullOrEmpty(String s) {
        return s == null || s.equals("");
    }

    private boolean checkIfConferenceNameExists() {
        db.loadConferenceWithName(conferenceName,this);
        if (conferenceID == -1) return false;
        else return true;
    }

    private boolean IsOddNumberOfDivisions() {
        return divisions.size() % 2 == 1;
    }

    @Override
    public String validateNameDuringImport() {
        String validity = "Valid";
        if (isNullOrEmpty(conferenceName)) {
            validity = "Conference name is blank";
        }
        return validity;
    }

    private boolean isConferenceNameExist(List<IConference> confList) {
        boolean isExist = false;
        for (IConference c: confList) {
            if (c.getConferenceName().equalsIgnoreCase(conferenceName)) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }

    @Override
    public String validateNameDuringCreate(List<IConference> confList) {
        String validity = "Valid";
        if (isNullOrEmpty(conferenceName)) {
            validity = "Conference name is blank";
        }
        else if (!isConferenceNameExist(confList)) {
            validity = conferenceName + " - The given conference does not exist, please enter a valid conference name";
        }
        return validity;
    }

    @Override
    public String validateNameDuringLoad() {
        String validity = "Valid";
        if (isNullOrEmpty(conferenceName)) {
            validity = "Conference name is blank";
        }
        else if (!checkIfConferenceNameExists()) {
            validity = conferenceName + " - The given conference does not exist, please enter a valid conference name";
        }
        return validity;
    }

    @Override
    public String validateBusinessRules() {
        String validity = "Valid";

        return validity;
    }

    @Override
    public void save() {
        db.saveConference(this);
    }

    @Override
    public void load() {
        db.loadConferenceWithName(conferenceName, this);
    }

}

