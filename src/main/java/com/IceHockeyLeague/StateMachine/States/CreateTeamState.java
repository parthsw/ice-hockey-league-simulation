package com.IceHockeyLeague.StateMachine.States;

import com.IO.IAppInput;
import com.IO.IAppOutput;

import com.IceHockeyLeague.LeagueManager.Team.*;
import com.IceHockeyLeague.LeagueManager.Conference.*;
import com.IceHockeyLeague.LeagueManager.Division.*;
import com.IceHockeyLeague.LeagueManager.League.*;

public class CreateTeamState extends AbstractState {

    private static final String TEAM_CREATION = "************Welcome to team creation************";
    private final String VALID = "VALID";
    private IAppInput appInput;
    private IAppOutput appOutput;
    private ITeam newTeam;
    private ILeague inMemoryLeague;
    private Conference newConference;
    private Division newDivision;

    public CreateTeamState(IAppInput appInput, IAppOutput appOutput) {
        configureDefaults(appInput,appOutput);
    }

    @Override
    public AbstractState onRun() {
        try{
            welcomeMessage();
            newTeam = this.constructNewTeam();
            return null;
        }
        catch(Exception exception){
            StateUtils.printErrorMessage(exception.getMessage());
            return null;
        }
    }

    private void configureDefaults(IAppInput appInput, IAppOutput appOutput){
        this.appInput = appInput;
        this.appOutput = appOutput;
    }

    @Override
    public void welcomeMessage() {
        appOutput.display(TEAM_CREATION);
    }

    private ITeam constructNewTeam(){
        ITeam team=null;
        this.newConference = this.processConferenceName();
        this.newDivision = this.processDivisionName();
        return team;
    }

    private Conference processConferenceName() {
        Conference conference = new Conference();
        String conferenceName;
        while (true) {
            StateUtils.printMessage("Please provide the name of the conference");
            conferenceName = StateUtils.promptForUserInput();
            conference.setConferenceName(conferenceName.trim());
            String conferenceNameValidation = conference.validateNameDuringCreate(inMemoryLeague.getConferences());
            if (conferenceNameValidation.equalsIgnoreCase(VALID)) {
                break;
            } else {
                StateUtils.promptForUserInput();
            }
        }

        for(Conference matchedConference : inMemoryLeague.getConferences()) {

        }

        return conference;
    }

    private void processDivisionName(){
        Division division = new Division();
        String divisionName;
        while(true){
            StateUtils.printMessage("Please enter the name of the division");
            divisionName = StateUtils.promptForUserInput();
            division.setDivisionName(divisionName.trim());
            String divisionNameValidation = division.validateNameDuringCreate();
        }
    }
}
    private Division processDivisionName(){
        Division division = new Division();
        return division;
    }
}
