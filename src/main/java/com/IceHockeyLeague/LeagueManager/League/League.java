package com.IceHockeyLeague.LeagueManager.League;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Coach.Coach;
import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
import com.IceHockeyLeague.LeagueManager.Coach.ICoachPersistence;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Conference.IConferencePersistence;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IGamePlayConfig;
import com.IceHockeyLeague.LeagueManager.LeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Manager.IManager;
import com.IceHockeyLeague.LeagueManager.Manager.IManagerPersistence;
import com.IceHockeyLeague.LeagueManager.Manager.Manager;
import com.IceHockeyLeague.LeagueManager.Player.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.IFreeAgentPersistence;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueScheduler.IScheduleSystem;
import com.IceHockeyLeague.LeagueScheduler.ScheduleSystem;
import com.IceHockeyLeague.LeagueStandings.IStandingSystem;
import com.IceHockeyLeague.LeagueStandings.StandingSystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class League implements ILeague {
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
        this.scheduleSystem = new ScheduleSystem();
        this.standingSystem = new StandingSystem();
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
        this.saveLeague(LeagueManagerFactory.getFactory().getLeagueDB());

        gamePlayConfig.setLeagueID(leagueID);
        gamePlayConfig.saveGamePlayConfig(LeagueManagerFactory.getFactory().getGamePlayConfigDB());

        for(IConference conference: conferences) {
            conference.setLeagueID(leagueID);
            conference.saveConference(LeagueManagerFactory.getFactory().getConferenceDB());

            for(IDivision division: conference.getDivisions()) {
                division.setConferenceID(conference.getConferenceID());
                division.saveDivision(LeagueManagerFactory.getFactory().getDivisionDB());

                for(ITeam team: division.getTeams()) {
                    team.setDivisionID(division.getDivisionID());
                    team.saveTeam(LeagueManagerFactory.getFactory().getTeamDB());

                    for(ITeamPlayer player: team.getPlayers()) {
                        player.setTeamID(team.getTeamID());
                        player.saveTeamPlayer(LeagueManagerFactory.getFactory().getTeamPlayerDB());
                    }

                    IManager manager = team.getManager();
                    manager.setTeamID(team.getTeamID());
                    manager.setLeagueID(leagueID);
                    manager.saveTeamManager(LeagueManagerFactory.getFactory().getManagerDB());

                    ICoach coach = team.getCoach();
                    coach.setTeamID(team.getTeamID());
                    coach.setLeagueID(leagueID);
                    coach.saveTeamCoach(LeagueManagerFactory.getFactory().getCoachDB());
                }
            }
        }

        for(IFreeAgent freeAgent: freeAgents) {
            freeAgent.setLeagueID(leagueID);
            freeAgent.saveFreeAgent(LeagueManagerFactory.getFactory().getFreeAgentDB());
        }

        for(IManager manager: managers) {
            manager.setLeagueID(leagueID);
            manager.saveLeagueManager(LeagueManagerFactory.getFactory().getManagerDB());
        }

        for(ICoach coach: coaches) {
            coach.setLeagueID(leagueID);
            coach.saveLeagueCoach(LeagueManagerFactory.getFactory().getCoachDB());
        }

        return true;
    }

    @Override
    public boolean loadCompleteLeague(int id) {
        leagueID = id;
        this.loadLeague(LeagueManagerFactory.getFactory().getLeagueDB());

        List<IConference> conferences = new ArrayList<>();
        this.loadConferences(LeagueManagerFactory.getFactory().getConferenceDB(), conferences);

        for(IConference conference: conferences) {
            this.addConference(conference);

            List<IDivision> divisions = new ArrayList<>();
            conference.loadDivisions(LeagueManagerFactory.getFactory().getDivisionDB(), divisions);

            for(IDivision division: divisions) {
                conference.addDivision(division);

                List<ITeam> teams = new ArrayList<>();
                division.loadTeams(LeagueManagerFactory.getFactory().getTeamDB(), teams);

                for(ITeam team: teams) {
                    division.addTeam(team);

                    List<ITeamPlayer> teamPlayers = new ArrayList<>();
                    team.loadPlayers(LeagueManagerFactory.getFactory().getTeamPlayerDB(), teamPlayers);

                    for(ITeamPlayer teamPlayer: teamPlayers) {
                        team.addPlayer(teamPlayer);
                    }

                    IManager manager = new Manager();
                    manager.setLeagueID(leagueID);
                    manager.setTeamID(team.getTeamID());
                    manager.loadTeamManager(LeagueManagerFactory.getFactory().getManagerDB(), manager);
                    team.setManager(manager);

                    ICoach coach = new Coach();
                    coach.setLeagueID(leagueID);
                    coach.setTeamID(team.getTeamID());
                    coach.loadTeamCoach(LeagueManagerFactory.getFactory().getCoachDB(), coach);
                    team.setCoach(coach);
                }
            }
        }

        List<IFreeAgent> freeAgents = new ArrayList<>();
        this.loadLeagueFreeAgents(LeagueManagerFactory.getFactory().getFreeAgentDB(), freeAgents);
        this.setFreeAgents(freeAgents);

        List<IManager> managers = new ArrayList<>();
        this.loadLeagueManagers(LeagueManagerFactory.getFactory().getManagerDB(), managers);
        this.setManagers(managers);

        List<ICoach> coaches = new ArrayList<>();
        this.loadLeagueCoaches(LeagueManagerFactory.getFactory().getCoachDB(), coaches);
        this.setCoaches(coaches);

        gamePlayConfig = AbstractLeagueManagerFactory.getFactory().getGamePlayConfig();
        gamePlayConfig.setLeagueID(leagueID);
        gamePlayConfig.loadGamePlayConfig(LeagueManagerFactory.getFactory().getGamePlayConfigDB(), gamePlayConfig);

        return true;
    }

    @Override
    public boolean saveLeague(ILeaguePersistence leagueDB) {
        return leagueDB.saveLeague(this);
    }

    @Override
    public boolean loadLeague(ILeaguePersistence leagueDB) {
        return leagueDB.loadLeague(leagueID, this);
    }

    @Override
    public boolean loadConferences(IConferencePersistence conferenceDB, List<IConference> conferences) {
        return conferenceDB.loadConferences(leagueID, conferences);
    }

    @Override
    public boolean loadLeagueManagers(IManagerPersistence managerDB, List<IManager> managers) {
        return managerDB.loadLeagueManagers(leagueID, managers);
    }

    @Override
    public boolean loadLeagueCoaches(ICoachPersistence coachDB, List<ICoach> coaches) {
        return coachDB.loadLeagueCoaches(leagueID, coaches);
    }

    @Override
    public boolean loadLeagueFreeAgents(IFreeAgentPersistence freeAgentDB, List<IFreeAgent> freeAgents) {
        return freeAgentDB.loadFreeAgents(leagueID, freeAgents);
    }
}
