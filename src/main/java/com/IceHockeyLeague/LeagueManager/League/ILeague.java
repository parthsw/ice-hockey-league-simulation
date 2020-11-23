package com.IceHockeyLeague.LeagueManager.League;

import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
import com.IceHockeyLeague.LeagueManager.Coach.ICoachPersistence;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Conference.IConferencePersistence;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IGamePlayConfig;
import com.IceHockeyLeague.LeagueManager.Manager.IManager;
import com.IceHockeyLeague.LeagueManager.Manager.IManagerPersistence;
import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgentPersistence;
import com.IceHockeyLeague.LeagueManager.Player.IPlayer;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Scheduler.IScheduleSystem;
import com.IceHockeyLeague.LeagueManager.Standings.IStandingSystem;

import java.time.LocalDate;
import java.util.List;

public interface ILeague {
    int getLeagueID();
    void setLeagueID(int id);

    String getLeagueName();
    void setLeagueName(String name);

    LocalDate getLeagueDate();
    void setLeagueDate(LocalDate date);
    void incrementLeagueDate();

    int getDaysSinceLastStatIncrease();
    void setDaysSinceLastStatIncrease(int daysSinceLastStatIncrease);
    void incrementDaysSinceLastStatIncrease();
    void resetDaysSinceLastStatIncrease();

    IGamePlayConfig getGamePlayConfig();
    void setGamePlayConfig(IGamePlayConfig gamePlayConfig);

    IConference getConferenceById(int id);
    void addConference(IConference conference);
    List<IConference> getConferences();
    void setConferences(List<IConference> conferences);

    IPlayer getFreeAgentById(int id);
    void addFreeAgent(IFreeAgent freeAgent);
    boolean removeFreeAgent(IFreeAgent freeAgent);
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

    void addRetiredTeamPlayer(ITeamPlayer teamPlayer);
    List<ITeamPlayer> getRetiredTeamPlayers();
    void addRetiredFreeAgent(IFreeAgent freeAgent);
    List<IFreeAgent> getRetiredFreeAgents();

    IScheduleSystem getScheduleSystem();
    IStandingSystem getStandingSystem();

    boolean saveCompleteLeague();
    boolean loadCompleteLeague(int leagueId);
    boolean saveLeague(ILeaguePersistence leagueDB);
    boolean loadLeague(ILeaguePersistence leagueDB);
    boolean loadConferences(IConferencePersistence conferenceDB, List<IConference> conferences);
    boolean loadLeagueManagers(IManagerPersistence managerDB, List<IManager> managers);
    boolean loadLeagueCoaches(ICoachPersistence coachDB, List<ICoach> coaches);
    boolean loadLeagueFreeAgents(IFreeAgentPersistence freeAgentDB, List<IFreeAgent> freeAgents);
}
