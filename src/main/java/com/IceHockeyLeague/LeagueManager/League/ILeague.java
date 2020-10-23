package com.IceHockeyLeague.LeagueManager.League;

import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
import com.IceHockeyLeague.LeagueManager.Coach.ICoachPersistence;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Conference.IConferencePersistence;
import com.IceHockeyLeague.LeagueManager.Manager.IManager;
import com.IceHockeyLeague.LeagueManager.Manager.IManagerPersistence;
import com.IceHockeyLeague.LeagueManager.Player.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.IFreeAgentPersistence;
import com.IceHockeyLeague.LeagueManager.Player.IPlayer;

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

    IPlayer getFreeAgentById(int id);
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

    boolean saveCompleteLeague();
    boolean loadCompleteLeague(int leagueId);
    boolean saveLeague(ILeaguePersistence leagueDB);
    boolean loadLeague(ILeaguePersistence leagueDB);
    boolean loadConferences(IConferencePersistence conferenceDB, List<IConference> conferences);
    boolean loadLeagueManagers(IManagerPersistence managerDB, List<IManager> managers);
    boolean loadLeagueCoaches(ICoachPersistence coachDB, List<ICoach> coaches);
    boolean loadLeagueFreeAgents(IFreeAgentPersistence freeAgentDB, List<IFreeAgent> freeAgents);
}
