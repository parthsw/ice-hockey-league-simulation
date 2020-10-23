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
    private IConference newConference;
    private IDivision newDivision;

    public CreateTeamState(IAppInput appInput, IAppOutput appOutput) {
        configureDefaults(appInput, appOutput);
    }

    @Override
    public AbstractState onRun() {
        try {
            this.initializeInMemoryLeague();
            welcomeMessage();
            newTeam = this.constructNewTeam();
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
                appOutput.displayError("The conference name already exists");
            } else {
                break;
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
                appOutput.displayError("The division name is already exists");
            } else {
                break;
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

            } else if (team.isTeamNameExist(matchedDivision.getTeams())) {
                appOutput.displayError("The team name already exists");
            } else {
                break;
            }
        }
        return team;
    }
}

