package com.IceHockeyLeague.StateMachine.States;

import com.AbstractAppFactory;
import com.IO.IAppInput;
import com.IO.IAppOutput;

import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.*;
import com.IceHockeyLeague.LeagueManager.Conference.*;
import com.IceHockeyLeague.LeagueManager.Division.*;
import com.IceHockeyLeague.LeagueManager.League.*;
import com.IceHockeyLeague.LeagueManager.Manager.*;
import com.IceHockeyLeague.LeagueManager.Coach.*;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;

import java.util.*;

public class CreateTeamState extends AbstractState {
    private static final String TEAM_CREATION = "************Welcome to team creation************";

    private final ILeagueManagerFactory leagueManagerFactory;
    private final IStateMachineFactory stateMachineFactory;

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
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        stateMachineFactory = AbstractAppFactory.getStateMachineFactory();
        configureDefaults(appInput, appOutput);
    }

    @Override
    public AbstractState onRun() {
        try {
            this.initializeInMemoryLeague();
            welcomeMessage();
            newTeam = this.constructNewTeam();
            this.addTeamToMemoryLeague(newConference, newDivision, newTeam);
            return stateMachineFactory.createPlayerChoiceState();
        } catch (Exception exception) {
            appOutput.displayError("Throwing exception");
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

    public void welcomeMessage() {
        appOutput.display(TEAM_CREATION);
    }

    private ITeam constructNewTeam() {
        ITeam team;
        this.newConference = this.processConferenceName();
        this.newDivision = this.processDivisionName(this.newConference);
        team = this.processTeamName(this.newDivision);
        team.setIsUserCreated(true);
        IManager manager = this.processManager();
        ICoach coach = this.processCoach();
        this.players = this.processPlayers();
        team.setManager(manager);
        team.setCoach(coach);
        team.setPlayers(players);
        return team;
    }

    private IConference processConferenceName() {
        IConference conference = leagueManagerFactory.createConference();
        String conferenceName;
        while (true) {
            appOutput.display("Please provide the name of the conference");
            conferenceName = appInput.getInput();
            if (conference.isNullOrEmpty(conferenceName)) {
                appOutput.displayError("The conference name cannot be blank");
            }
            if (conference.isConferenceNameExist(inMemoryLeague.getConferences(),conferenceName)) {
                conference.setConferenceName(conferenceName.trim());
                break;
            }
            else{
                appOutput.display("Conference name does not exists");
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
        IDivision division = leagueManagerFactory.createDivision();
        String divisionName;
        while (true) {
            appOutput.display("Please provide the name of the division");
            divisionName = appInput.getInput();
            if (division.isNullOrEmpty(divisionName)) {
                appOutput.displayError("The division name cannot be blank");
            }
            if (division.isDivisionNameExist(matchedConference.getDivisions(),divisionName)) {
                division.setDivisionName((divisionName.trim()));
                break;
            }
            else{
                appOutput.display("Division name does not exists");
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
        ITeam team = leagueManagerFactory.createTeam();
        String teamName;
        while (true) {
            appOutput.display("Please provide the team name");
            teamName = appInput.getInput();
           if (team.isNullOrEmpty(teamName)) {
                appOutput.displayError("The team name cannot be empty");
            } else if (team.isTeamNameExist(matchedDivision.getTeams(),teamName)) {
                appOutput.displayError("The team name already exists");
            } else {
                team.setTeamName(teamName.trim());
                break;
            }
       }
        return team;
    }

    private IManager processManager(){
        IManager manager = leagueManagerFactory.createManager();
        String managerName;
        List<IManager> managers = inMemoryLeague.getManagers();
        appOutput.display("Showing you the list of managers");
        for(IManager m : managers){
            appOutput.display(m.getManagerName());
        }
        while(true) {
            appOutput.display("Please enter the name of the manager form the list of managers");
            managerName = appInput.getInput();
            if (manager.isNullOrEmpty(managerName)) {
                appOutput.displayError("The team name cannot be empty");
            }
            if (manager.isManagerNameExist(managers, managerName)) {
                manager.setManagerName(managerName.trim());
                break;
            }
        }
        return manager;
    }

    private ICoach processCoach(){
        ICoach coach = leagueManagerFactory.createCoach();
        String coachName;
        List<ICoach> coaches = inMemoryLeague.getCoaches();
        appOutput.display("Showing you the list of coaches along with their stats");
        for(ICoach c : coaches){
            appOutput.display("******************************************");
            appOutput.display(c.getCoachName());
            appOutput.display("********************* STATS for "+c.getCoachName()+" *********************");
            appOutput.display("Checking stat for coach "+c.getCoachName()+" is "+c.getCoachStats().getChecking());
            appOutput.display("Saving stat for coach "+c.getCoachName()+" is "+c.getCoachStats().getSaving());
            appOutput.display("Shooting stat for coach "+c.getCoachName()+" is "+c.getCoachStats().getShooting());
            appOutput.display("Skating stat for coach "+c.getCoachName()+" is "+c.getCoachStats().getSkating());
        }
        while(true) {
            appOutput.display("Please provide the name of the coach");
            ICoachStats stats;
            coachName = appInput.getInput();
            for(ICoach c : coaches){
                if(c.getCoachName().equalsIgnoreCase(coachName)){
                    stats = c.getCoachStats();
                    coach.setCoachStats(stats);
                    break;
                }
            }
            if (coach.isNullOrEmpty(coachName)) {
                appOutput.displayError("The team name cannot be empty");
            }
            if (coach.isCoachNameExist(coaches, coachName)) {
                coach.setCoachName(coachName.trim());
                break;
            }
        }
        return coach;
    }

    private List<ITeamPlayer> processPlayers(){
        boolean flagCheck = false;
        List<ITeamPlayer> players = new ArrayList<>();
        appOutput.display("Please select 18 skaters and 2 goalies from the list of free agents");
        freeAgents = inMemoryLeague.getFreeAgents();
        ITeam team = leagueManagerFactory.createTeam();
        for(IFreeAgent f : freeAgents){
            appOutput.display("******************************************");
            appOutput.display(f.getPlayerName());
            appOutput.display("********************* STATS for "+f.getPlayerName()+" *********************");
            appOutput.display("Position for free agent/player "+f.getPlayerName()+" is "+f.getPlayerStats().getPosition());
            appOutput.display("Checking stat for free agent/player "+f.getPlayerName()+" is "+Float.toString(f.getPlayerStats().getChecking()));
            appOutput.display("Saving stat for free agent/player "+f.getPlayerName()+" is "+Float.toString(f.getPlayerStats().getSaving()));
            appOutput.display("Shooting stat for free agent/player "+f.getPlayerName()+" is "+Float.toString(f.getPlayerStats().getShooting()));
            appOutput.display("Skating stat for free agent/player "+f.getPlayerName()+" is "+Float.toString(f.getPlayerStats().getSkating()));
        }
        while(true) {
            appOutput.display("select the players for your team from the list of free agents shown above");
            for (int count = 0; count < 20; count++) {
                player = leagueManagerFactory.createTeamPlayer();
                String playerName = appInput.getInput();
                player.setPlayerName(playerName);
                for(IFreeAgent freeAgent : freeAgents){
                    if(playerName.equalsIgnoreCase(freeAgent.getPlayerName())) {
                        player.setPlayerAgeInfo(freeAgent.getPlayerAgeInfo());
                        player.setPlayerStats(freeAgent.getPlayerStats());
                        break;
                    }
                }
                players.add(player);
            }
            team.setPlayers(players);
            ITeamStrengthCalculator calculator = leagueManagerFactory.createTeamStrengthCalculator();
            float teamStrength = team.calculateTeamStrength(calculator);
            team.setTeamStrength(teamStrength);
            flagCheck = team.checkTeamPlayers();
            if(flagCheck){
                break;
            }
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

