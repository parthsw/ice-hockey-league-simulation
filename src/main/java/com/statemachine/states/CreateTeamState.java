package com.statemachine.states;

import com.Database.Persistence;
import com.leaguemodel.IPersistence;
import com.statemachine.StateMachine;
import com.leaguemodel.models.*;

public class CreateTeamState extends State {
    // TODO: to create a singleton persistence object
    IPersistence dbPersistence = new Persistence();
    private ITeam newTeam;
    private ILeague inMemoryLeague;
    private IConference newTeamConference;
    private IDivision newTeamDivision;

    private final String VALID = "VALID";

    public CreateTeamState(StateMachine stateMachine) {
        super(stateMachine);
    }

    @Override
    public State onRun() {
        try {
            this.initializeInMemoryLeague();
            this.importWelcomeMessage();
            newTeam = this.constructNewTeam();
            this.addTeamToInMemoryLeague(newTeamConference, newTeamDivision, newTeam);
            persistLeagueToDatabase(inMemoryLeague);

            return new PlayerChoiceState(stateMachine);
        }
        catch(Exception exception) {
            StateUtils.printErrorMessage(exception.getMessage());
            return null;
        }
    }

    private void initializeInMemoryLeague() {
        this.inMemoryLeague = stateMachine.getLeague();
    }

    private ITeam constructNewTeam() {
        ITeam team;
        this.newTeamConference = this.processConferenceName();
        this.newTeamDivision = this.processDivisionName(this.newTeamConference);
        team = this.processTeamName(this.newTeamDivision);
        IManager manager = this.processManager();
        ICoach coach = this.processCoach();
        team.addManager(manager);
        team.addCoach(coach);
        return team;    
    }

    private void addTeamToInMemoryLeague(IConference conference, IDivision division, ITeam team) {
        for(IConference matchedConference: inMemoryLeague.getConferences()) {
            if(matchedConference.getConferenceName().equalsIgnoreCase(conference.getConferenceName())) {
                for(IDivision matchedDivision: conference.getDivisions()) {
                    if(matchedDivision.getDivisionName().equalsIgnoreCase(division.getDivisionName())) {
                        matchedDivision.addTeam(team);
                        break;
                    }
                }
            }
        }
    }

    private void persistLeagueToDatabase(ILeague league) {
        league.save(); // Should return leagueID
//        for(IPlayer freeAgent: league.getFreeAgents()) {
//            // freeAgent.setLeagueID();
//            freeAgent.save();
//        }
        // persistConferenceToDatabase()
        for(IConference conference: league.getConferences()) {
            conference.setLeagueID(league.getLeagueID());
            conference.save(); // should return conferenceId

            for(IDivision division: conference.getDivisions()) {
                division.setConferenceID(conference.getConferenceID());
                division.save(); // should return divisionId
                division.setDivisionID(division.getDivisionID());

                for(ITeam team: division.getTeams()) {
                    team.setDivisionID(division.getDivisionID());
                    team.save(); // should return teamId

                    for(IPlayer player: team.getPlayers()) {
                        player.setTeamID(team.getTeamID());
                        player.save(); // should return playerId
                    }
                    for(IManager manager: team.getManagers()) {
                        manager.setTeamID(team.getTeamID());
                        manager.save(); // should return managerId
                    }
                    for(ICoach coach: team.getCoaches()) {
                        coach.setTeamID(team.getTeamID());
                        coach.save(); // should return coachId
                    }
                }
            }
        }
    }

    private IConference processConferenceName() {
        IConference conference = new Conference(dbPersistence);
        String conferenceName;
        while(true) {
            StateUtils.printMessage("Please provide name of the conference where the team belongs to:");
            conferenceName = StateUtils.promptForUserInput();
            conference.setConferenceName(conferenceName.trim());
            String conferenceNameValidation = conference.validateNameDuringCreate(inMemoryLeague.getConferences());
            if(conferenceNameValidation.equalsIgnoreCase(VALID)) {
                break;
            } else {
                StateUtils.printErrorMessage(conferenceNameValidation);
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
        IDivision division = new Division(dbPersistence);
        String divisionName;
        while(true) {
            StateUtils.printMessage("Please provide name of the division where the team belongs to:");
            divisionName = StateUtils.promptForUserInput();
            division.setDivisionName(divisionName.trim());

            String divisionNameValidation = division.validateNameDuringCreate(matchedConference.getDivisions());
            if(divisionNameValidation.equalsIgnoreCase(VALID)) {
                break;
            } else {
                StateUtils.printErrorMessage(divisionNameValidation);
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
        ITeam team = new Team(dbPersistence);
        String teamName;
        while(true) {
            StateUtils.printMessage("Please provide name of the team you want to create:");
            teamName = StateUtils.promptForUserInput();
            team.setTeamName(teamName);
            String teamNameValidation = team.validateNameDuringCreate(matchedDivision.getTeams());
            if(teamNameValidation.equalsIgnoreCase(VALID)) {
                break;
            } else {
                StateUtils.printErrorMessage(teamNameValidation);
            }
        }
        return team;
    }

    private IManager processManager() {
        IManager manager = new Manager(dbPersistence);
        String generalManagerName;
        while(true) {
            StateUtils.printMessage("Please provide name of team's general manager:");
            generalManagerName = StateUtils.promptForUserInput();
            manager.setManagerName(generalManagerName.trim());

            String generalManagerNameValidation = manager.validateName();
            if(generalManagerNameValidation.equalsIgnoreCase(VALID)) {
                break;
            } else {
                StateUtils.printErrorMessage(generalManagerNameValidation);
            }
        }
        return manager;
    }

    private ICoach processCoach() {
        ICoach coach = new Coach(dbPersistence);
        String headCoachName;
        while(true) {
            StateUtils.printMessage("Please provide name of team's head coach:");
            headCoachName = StateUtils.promptForUserInput();
            coach.setCoachName(headCoachName.trim());

            String headCoachNameValidation = coach.validateName();
            if(headCoachNameValidation.equalsIgnoreCase(VALID)) {
                break;
            } else {
                StateUtils.printErrorMessage(headCoachNameValidation);
            }
        }
        return coach;
    }

    private void importWelcomeMessage() {
        StateUtils.printMessage("***Create Team State***");
    }
}
