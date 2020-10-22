package com.IceHockeyLeague.LeagueManager.League;

import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Manager.IManager;
import com.IceHockeyLeague.LeagueManager.Player.IFreeAgent;

import java.util.List;

public class League implements ILeague {
    private int leagueID;
    private String leagueName;
    private List<IConference> conferences;
    private List<IFreeAgent> freeAgents;
    private List<ICoach> coaches;
    private List<IManager> managers;

    @Override
    public int getLeagueID() {
        return leagueID;
    }

    @Override
    public void setLeagueID(int id) {
        leagueID = id;
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
    public IConference getConferenceById(int id) {
        return null;
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
    public IFreeAgent getFreeAgentById(int id) {
        return null;
    }

    @Override
    public void addFreeAgent(IFreeAgent freeAgent) {
        freeAgents.add(freeAgent);
    }

    @Override
    public List<IFreeAgent> getFreeAgents() {
        return freeAgents;
    }

    @Override
    public void setFreeAgents(List<IFreeAgent> freeAgents) {
        this.freeAgents = freeAgents;
    }

    @Override
    public ICoach getCoachById(int id) {
        return null;
    }

    @Override
    public void addCoach(ICoach coach) {
        coaches.add(coach);
    }

    @Override
    public List<ICoach> getCoaches() {
        return coaches;
    }

    @Override
    public void setCoaches(List<ICoach> coaches) {
        this.coaches = coaches;
    }

    @Override
    public IManager getManagerById(int id) {
        return null;
    }

    @Override
    public void addManager(IManager manager) {
        managers.add(manager);
    }

    @Override
    public List<IManager> getManagers() {
        return managers;
    }

    @Override
    public void setManagers(List<IManager> managers) {
        this.managers = managers;
    }

    @Override
    public boolean isValid() {
        return false;
    }
}
