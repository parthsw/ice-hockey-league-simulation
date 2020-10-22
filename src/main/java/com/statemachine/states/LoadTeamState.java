package com.statemachine.states;

import com.statemachine.StateMachine;
import com.leaguemodel.models.*;

public class LoadTeamState extends State {
    private final String VALID = "VALID";


    public LoadTeamState(StateMachine stateMachine) {
        super(stateMachine);
    }

    @Override
    public State onRun() {
        try {
            this.importWelcomeMessage();
            this.loadTeam();
            return new PlayerChoiceState(stateMachine);
        }
        catch(Exception exception) {
            StateUtils.printErrorMessage(exception.getMessage());
            return null;
        }
    }

    private void loadTeam() {
        this.processConferenceName();
        this.processDivisionName();
        this.processTeamName();
    }


    private IConference processConferenceName() {
        IConference conference = new Conference();
        String conferenceName;
        while(true) {
            StateUtils.printMessage("Please provide name of the conference where the team belongs to:");
            conferenceName = StateUtils.promptForUserInput();
            conference.setConferenceName(conferenceName.trim());
            String conferenceNameValidation = conference.validateNameDuringLoad();
            if(conferenceNameValidation.equalsIgnoreCase(VALID)) {
                break;
            } else {
                StateUtils.printErrorMessage(conferenceNameValidation);
            }
        }
        return conference;
    }

    private IDivision processDivisionName() {
        IDivision division = new Division();
        String divisionName;
        while(true) {
            StateUtils.printMessage("Please provide name of the division where the team belongs to:");
            divisionName = StateUtils.promptForUserInput();
            division.setDivisionName(divisionName.trim());

            String divisionNameValidation = division.validateNameDuringLoad();
            if(divisionNameValidation.equalsIgnoreCase(VALID)) {
                break;
            } else {
                StateUtils.printErrorMessage(divisionNameValidation);
            }
        }
        return division;
    }

    private ITeam processTeamName() {
        ITeam team = new Team();
        String teamName;
        while(true) {
            StateUtils.printMessage("Please provide name of the team you want to create:");
            teamName = StateUtils.promptForUserInput();
            team.setTeamName(teamName);
            String teamNameValidation = team.validateNameDuringLoad();
            if(teamNameValidation.equalsIgnoreCase(VALID)) {
                break;
            } else {
                StateUtils.printErrorMessage(teamNameValidation);
            }
        }
        return team;
    }

    private void importWelcomeMessage() {
        StateUtils.printMessage("***Load Team State***");
    }
}
