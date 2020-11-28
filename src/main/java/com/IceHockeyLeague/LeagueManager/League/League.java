package com.IceHockeyLeague.LeagueManager.League;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IGamePlayConfig;
import com.IceHockeyLeague.LeagueManager.Manager.IManager;
import com.IceHockeyLeague.LeagueManager.Player.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Scheduler.IScheduleSystem;
import com.IceHockeyLeague.LeagueManager.Standings.IStandingSystem;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.SerializeDeserializeLeagueObject.ISerialize;
import com.IceHockeyLeague.SerializeDeserializeLeagueObject.Serialize;
import com.Persistence.ILeaguePersistence;
import com.Persistence.PersistenceFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class League implements ILeague {
    private ILeagueManagerFactory leagueManagerFactory;
    private int leagueID;
    private String leagueName;
    private LocalDate leagueDate;
    private int daysSinceLastStatIncrease;
    private IGamePlayConfig gamePlayConfig;
    private List<IConference> conferences;
    private List<IFreeAgent> freeAgents;
    private List<ICoach> coaches;
    private List<IManager> managers;
    private List<ITeamPlayer> retiredTeamPlayers;
    private List<IFreeAgent> retiredFreeAgents;
    private IScheduleSystem scheduleSystem;
    private IStandingSystem standingSystem;
    private static PersistenceFactory persistenceFactory;

    public League() {
        setDefaults();
    }

    private void setDefaults() {
        leagueID = -1;
        this.conferences = new ArrayList<>();
        this.freeAgents = new ArrayList<>();
        this.coaches = new ArrayList<>();
        this.managers = new ArrayList<>();
        this.retiredFreeAgents = new ArrayList<>();
        this.retiredTeamPlayers = new ArrayList<>();
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        this.scheduleSystem = leagueManagerFactory.createScheduleSystem();
        this.standingSystem = leagueManagerFactory.createStandingSystem();
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
    public String getLeagueName() {
        return leagueName;
    }

    @Override
    public void setLeagueName(String name) {
        leagueName = name;
    }

    @Override
    public LocalDate getLeagueDate() {
        return leagueDate;
    }

    @Override
    public void setLeagueDate(LocalDate date) {
        leagueDate = date;
    }

    @Override
    public int getDaysSinceLastStatIncrease() {
        return daysSinceLastStatIncrease;
    }

    @Override
    public void setDaysSinceLastStatIncrease(int daysSinceLastStatIncrease) {
        this.daysSinceLastStatIncrease = daysSinceLastStatIncrease;
    }

    @Override
    public void incrementDaysSinceLastStatIncrease() {
        this.daysSinceLastStatIncrease = this.daysSinceLastStatIncrease + 1;
    }

    @Override
    public void resetDaysSinceLastStatIncrease() {
        this.daysSinceLastStatIncrease = 0;
    }

    @Override
    public void incrementLeagueDate() {
        leagueDate = leagueDate.plusDays(1);
    }

    @Override
    public IGamePlayConfig getGamePlayConfig() {
        return gamePlayConfig;
    }

    @Override
    public void setGamePlayConfig(IGamePlayConfig gamePlayConfig) {
        this.gamePlayConfig = gamePlayConfig;
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
    public boolean removeFreeAgent(IFreeAgent freeAgent) {
        if(freeAgents.size() > 0) {
            return freeAgents.remove(freeAgent);
        }
        return false;
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
    public void addRetiredTeamPlayer(ITeamPlayer teamPlayer) {
        retiredTeamPlayers.add(teamPlayer);
    }

    @Override
    public List<ITeamPlayer> getRetiredTeamPlayers() {
        return retiredTeamPlayers;
    }

    @Override
    public void addRetiredFreeAgent(IFreeAgent freeAgent) {
        retiredFreeAgents.add(freeAgent);
    }

    @Override
    public List<IFreeAgent> getRetiredFreeAgents() {
        return retiredFreeAgents;
    }

    @Override
    public IScheduleSystem getScheduleSystem() {
        return scheduleSystem;
    }

    @Override
    public IStandingSystem getStandingSystem() {
        return standingSystem;
    }

    @Override
    public boolean saveCompleteLeague() {
        ILeaguePersistence leaguePersistence = persistenceFactory.createLeaguePersistence();
        leaguePersistence.saveLeague(this);
        return true;
    }

    @Override
    public ILeague loadCompleteLeague(String filePath) {
        ILeaguePersistence leaguePersistence = persistenceFactory.createLeaguePersistence();
        ILeague league = leaguePersistence.loadLeague(filePath);
        return league;
    }
}
