package com.IceHockeyLeague.StateMachine.States;

import com.IO.IAppInput;
import com.IO.IAppOutput;

import com.IceHockeyLeague.LeagueManager.Player.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.*;
import com.IceHockeyLeague.LeagueManager.Conference.*;
import com.IceHockeyLeague.LeagueManager.Division.*;
import com.IceHockeyLeague.LeagueManager.League.*;
import com.IceHockeyLeague.LeagueManager.Manager.*;
import com.IceHockeyLeague.LeagueManager.Coach.*;
import java.util.*;

import java.util.ArrayList;

public class CreateTeamState extends AbstractState {

    private static final String TEAM_CREATION = "************Welcome to team creation************";
    private final String VALID = "VALID";
    private IAppInput appInput;
    private IAppOutput appOutput;
    private ITeam newTeam;
    private ILeague inMemoryLeague;
    private IConference newConference;
    private IDivision newDivision;
    private List<ITeamPlayer> players;
    private ITeamPlayer player;
    private List<IFreeAgent> freeAgents;

    public CreateTeamState(IAppInput appInput, IAppOutput appOutput) {
        configureDefaults(appInput, appOutput);
    }

    @Override
    public AbstractState onRun() {
        try {
            this.initializeInMemoryLeague();
            welcomeMessage();
            newTeam = this.constructNewTeam();
            this.addTeamToMemoryLeague(newConference,newDivision,newTeam);
            //persistLeagueToDatabase(inMemoryLeague);
            return null;
        } catch (Exception exception) {
            appOutput.displayError("");
            return null;
        }
    }

    private void initializeInMemoryLeague() {
        inMemoryLeague = this.getLeague();
    }

    private void configureDefaults(IAppInput appInput, IAppOutput appOutput) {
        this.appInput = appInput;
        this.appOutput = appOutput;
    }

    @Override
    public void welcomeMessage() {
        appOutput.display(TEAM_CREATION);
    }

    private ITeam constructNewTeam() {
        ITeam team = null;
        this.newConference = this.processConferenceName();
        this.newDivision = this.processDivisionName(this.newConference);
        team = this.processTeamName(this.newDivision);
        IManager manager = this.processManager();
        ICoach coach = this.processCoach();
        this.players = this.processPlayers();
        team.setManager(manager);
        team.setCoach(coach);
        return team;
    }

    private IConference processConferenceName() {
        IConference conference = new Conference();
        String conferenceName;
        while (true) {
            appOutput.display("Please provide the name of the conference");
            conferenceName = appInput.getInput();
            conference.setConferenceName(conferenceName.trim());
            if (conference.isNullOrEmpty(conferenceName)) {
                appOutput.displayError("The conference name cannot be blank");
            } else if (conference.isConferenceNameExist(inMemoryLeague.getConferences())) {
                break;
            } else {
                appOutput.displayError("The conference name already exists");
            }
        }
        for (IConference matchedConference : inMemoryLeague.getConferences()) {
            if (matchedConference.getConferenceName().equalsIgnoreCase(conference.getConferenceName())) {
                conference = matchedConference;
                break;
            }
        }
        return conference;
    }

    private IDivision processDivisionName(IConference matchedConference) {
        IDivision division = new Division();
        String divisionName;
        while (true) {
            appOutput.display("Please provide the name of the division");
            divisionName = appInput.getInput();
            division.setDivisionName((divisionName.trim()));
            if (division.isNullOrEmpty(divisionName)) {
                appOutput.displayError("The division name cannot be blank");
            } else if (division.isDivisionNameExist(matchedConference.getDivisions())) {
                break;
            } else {
                appOutput.displayError("The division name is already exists");

            }
        }
        for (IDivision matchedDivision : matchedConference.getDivisions()) {
            if (matchedDivision.getDivisionName().equalsIgnoreCase(division.getDivisionName())) {
                division = matchedDivision;
                break;
            }
        }
        return division;
    }

    private ITeam processTeamName(IDivision matchedDivision) {
        ITeam team = new Team();
        String teamName;
        while (true) {
            appOutput.display("Please provide the team name");
            teamName = appInput.getInput();
            team.setTeamName(teamName.trim());
            if (team.isNullOrEmpty(teamName)) {
                appOutput.displayError("The team name canot be empty");
            } else if (team.isTeamNameExist(matchedDivision.getTeams())) {
                appOutput.displayError("The team name already exists");
            } else {
                break;
            }
        }
        return team;
    }

    private IManager processManager(){
        IManager manager = new Manager();
        String managerName;
        List<IManager> managers = inMemoryLeague.getManagers();
        appOutput.display("Showing you the list of managers");
        for(IManager m : managers){
            appOutput.display(m.getManagerName());
        }
        appOutput.display("Please enter the name of the manager form the list of managers");
        managerName = appInput.getInput();
        appOutput.display(managerName);
        manager.setManagerName(managerName.trim());
        return manager;
    }

    private ICoach processCoach(){
        ICoach coach = new Coach();
        String coachName;
        List<ICoach> coaches = inMemoryLeague.getCoaches();
        appOutput.display("Showing you the list of coaches along with their stats");
        for(ICoach c : coaches){
            appOutput.display(c.getCoachName());
            appOutput.display(Float.toString(c.getCoachStats().getChecking()));
            appOutput.display(Float.toString(c.getCoachStats().getSaving()));
            appOutput.display(Float.toString(c.getCoachStats().getShooting()));
            appOutput.display(Float.toString(c.getCoachStats().getSkating()));
        }
        coachName = appInput.getInput();
        appOutput.display(coachName);
        coach.setCoachName(coachName.trim());
        return coach;
    }

    private List<ITeamPlayer> processPlayers(){
        List<ITeamPlayer> players = null;
        appOutput.display("Please select the players from the list of free agents");
        freeAgents = inMemoryLeague.getFreeAgents();
        for(IFreeAgent f : freeAgents){
            appOutput.display(f.getPlayerName());
            appOutput.display(f.getPlayerStats().getPosition());
            appOutput.display(Float.toString(f.getPlayerStats().getChecking()));
            appOutput.display(Float.toString(f.getPlayerStats().getSaving()));
            appOutput.display(Float.toString(f.getPlayerStats().getShooting()));
            appOutput.display(Float.toString(f.getPlayerStats().getSkating()));
        }
        return players;
    }

    private void addTeamToMemoryLeague(IConference conference,IDivision division, ITeam team){
        for(IConference matchedConference: inMemoryLeague.getConferences()){
            if(matchedConference.getConferenceName().equalsIgnoreCase(conference.getConferenceName())){
                for(IDivision matchedDivision : conference.getDivisions()){
                    if(matchedDivision.getDivisionName().equalsIgnoreCase(division.getDivisionName())){
                        matchedDivision.addTeam(team);
                        break;
                    }
                }
            }
        }
    }

}

