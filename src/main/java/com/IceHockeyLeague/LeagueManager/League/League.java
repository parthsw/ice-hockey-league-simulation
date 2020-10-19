package com.IceHockeyLeague.LeagueManager.League;

import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Manager.IManager;

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
        return 0;
    }

    @Override
    public void setLeagueID(int id) {

    }

    @Override
    public String getLeagueName() {
        return null;
    }

    @Override
    public void setLeagueName(String name) {

    }

    @Override
    public IConference getConferenceById(int id) {
        return null;
    }

    @Override
    public void addConference(IConference conference) {

    }

    @Override
    public List<IConference> getConferences() {
        return null;
    }

    @Override
    public void setConferences(List<IConference> conferences) {

    }

    @Override
    public IFreeAgent getFreeAgentById(int id) {
        return null;
    }

    @Override
    public void addFreeAgent(IFreeAgent freeAgent) {

    }

    @Override
    public List<IFreeAgent> getFreeAgents() {
        return null;
    }

    @Override
    public void setFreeAgents(List<IFreeAgent> freeAgents) {

    }

    @Override
    public ICoach getCoachById(int id) {
        return null;
    }

    @Override
    public void addCoach(ICoach coach) {

    }

    @Override
    public List<ICoach> getCoaches() {
        return null;
    }

    @Override
    public void setCoaches(List<ICoach> coaches) {

    }

    @Override
    public IManager getManagerById(int id) {
        return null;
    }

    @Override
    public void addManager(IManager manager) {

    }

    @Override
    public List<IManager> getManagers() {
        return null;
    }

    @Override
    public void setManagers(List<IManager> managers) {

    }

    @Override
    public boolean isValid() {
        return false;
    }
}
