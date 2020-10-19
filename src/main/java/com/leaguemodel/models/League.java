package com.leaguemodel.models;

import com.leaguemodel.IPersistence;

import java.util.ArrayList;
import java.util.List;

public class League implements ILeague{
    private int leagueID;
    private String leagueName;
    private List<IConference> conferences;
    private List<IPlayer> freeAgents;

    private IPersistence db;

    public League(IPersistence p) {
        this();
        db = p;
    }

    public League() {
        leagueID = -1;
        leagueName = null;
        conferences = new ArrayList<IConference>();
    }

    @Override
    public String getLeagueName() {
        return leagueName;
    }

    @Override
    public void setLeagueName(String name) {
        leagueName = name;
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
    public IConference getConferenceByName(String name) {
        IConference conf = null;
        for (IConference c: conferences) {
            if (c.getConferenceName().equalsIgnoreCase(name)) {
                conf = c;
                break;
            }
        }
        return conf;
    }

    @Override
    public void addConference(IConference conference) {
        conferences.add(conference);
    }

    @Override
    public List<IConference> getConferences() {
        return conferences;
    }

    @Override
    public void setConferences(List<IConference> conferences) {
        this.conferences = conferences;
    }

    @Override
    public List<IPlayer> getFreeAgents() {
        return freeAgents;
    }

    @Override
    public void setFreeAgents(List<IPlayer> freeAgents) {
        this.freeAgents = freeAgents;
    }

    private boolean isNullOrEmpty(String s) {
        return s == null || s.equals("");
    }

    private boolean checkIfLeagueNameExists() {
        db.loadLeagueWithName(leagueName,this);
        if (leagueID == -1) return false;
        else return true;
    }

    private boolean IsOddNumberOfConferences() {
        return conferences.size() % 2 == 1;
    }

    @Override
    public String validateNameDuringImport() {
        String validity = "Valid";
        if (isNullOrEmpty(leagueName)) {
            validity = "League Name is blank";
        }
        else if (!checkIfLeagueNameExists()) {
            validity = "The given league already exists, please enter a valid league name";
        }
        return validity;
    }

    @Override
    public String validateNameDuringCreate() {
        String validity = "Valid";
        if (isNullOrEmpty(leagueName)) {
            validity = "League Name is blank";
        }
        else if (!checkIfLeagueNameExists()) {
            validity = "The given league already exists, please enter a valid league name";
        }
        return validity;
    }

    @Override
    public String validateNameDuringLoad() {
        return validateNameDuringCreate();
    }

    @Override
    public String validateBusinessRules() {
        String validity = "Valid";

        return validity;
    }

    @Override
    public void save() {
        db.saveLeague(this);
    }

    @Override
    public void load() {
        db.loadLeagueWithName(leagueName, this);
    }
}
