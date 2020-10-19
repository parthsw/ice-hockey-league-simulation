package com.IceHockeyLeague.LeagueManager.League;

import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Manager.IManager;

import java.util.List;

public interface ILeague {
    int getLeagueID();
    void setLeagueID(int id);

    String getLeagueName();
    void setLeagueName(String name);

    IConference getConferenceById(int id);
    void addConference(IConference conference);
    List<IConference> getConferences();
    void setConferences(List<IConference> conferences);

    IFreeAgent getFreeAgentById(int id);
    void addFreeAgent(IFreeAgent freeAgent);
    List<IFreeAgent> getFreeAgents();
    void setFreeAgents(List<IFreeAgent> freeAgents);

    ICoach getCoachById(int id);
    void addCoach(ICoach coach);
    List<ICoach> getCoaches();
    void setCoaches(List<ICoach> coaches);

    IManager getManagerById(int id);
    void addManager(IManager manager);
    List<IManager> getManagers();
    void setManagers(List<IManager> managers);

    boolean isValid();
}
