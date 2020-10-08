package asdc.hl.statemachine.states;

import asdc.hl.statemachine.StateMachine;

public class CreateTeamState extends State {
    // TODO: private Team newTeam = new Team();

    public CreateTeamState(StateMachine stateMachine) {
        super(stateMachine);
    }

    @Override
    public State onRun() {
        this.importWelcomeMessage();
        this.promptForNewTeam();
        // this.initializeNewTeam();
        // this.addTeamToLeague();
        // this.persistImportedLeague();
        return new PlayerChoiceState(stateMachine);
    }

    // TODO: Refactor these ugly repetitive prompts for creating a new team
    // How do I dynamically pass the validation method?
    private void promptForNewTeam() {
        //1. Team Name
        String teamName;
        while(true) {
            StateUtils.printMessage("Please provide name of the team you want to create:");
            teamName = StateUtils.promptForUserInput();
            boolean isTeamNameExist = isTeamNameExist();
            if(isTeamNameExist) {
                StateUtils.printErrorMessage("Please provide a valid team name that does not exist.");
            } else {
                break;
            }
        }

        //2. Conference Name
        String conferenceName;
        while(true) {
            StateUtils.printMessage("Please provide name of the conference where the team belongs to:");
            conferenceName = StateUtils.promptForUserInput();
            boolean isConferenceNameExist = isConferenceNameExist();
            if(isConferenceNameExist) {
                break;
            } else {
                StateUtils.printErrorMessage("The provided conference name does not exist in the imported Hockey league.");
            }
        }

        //3. Division Name
        String divisionName;
        while(true) {
            StateUtils.printMessage("Please provide name of the division where the team belongs to:");
            divisionName = StateUtils.promptForUserInput();
            boolean isDivisionNameExist = isDivisionNameExist();
            if(isDivisionNameExist) {
                break;
            } else {
                StateUtils.printErrorMessage("The provided division name does not exist in the imported Hockey league.");
            }
        }

        //4. General Manager Name
        String generalManagerName;
        while(true) {
            StateUtils.printMessage("Please provide name of team's general manager:");
            generalManagerName = StateUtils.promptForUserInput();
            boolean isGeneralManagerNameValid = isConferenceNameExist();
            if(isGeneralManagerNameValid) {
                break;
            } else {
                StateUtils.printErrorMessage("Please provide valid name for team's general manager.");
            }
        }

        //5. Head Coach Name
        String headCoachName;
        while(true) {
            StateUtils.printMessage("Please provide name of team's head coach:");
            headCoachName = StateUtils.promptForUserInput();
            boolean isHeadCoachNameValid = isConferenceNameExist();
            if(isHeadCoachNameValid) {
                break;
            } else {
                StateUtils.printErrorMessage("Please provide valid name for team's head coach.");
            }
        }
    }

    private boolean isTeamNameExist() {
        // TODO: Check in the league object model whether a team with same name exist + Put check for empty string
        return false;
    }

    private boolean isConferenceNameExist() {
        // TODO: Check in the league object model whether a conference with same name exist + Put check for empty string
        return true;
    }

    private boolean isDivisionNameExist() {
        // TODO: Check in the league object model whether a conference with same name exist + Put check for empty string
        return true;
    }

    private void importWelcomeMessage() {
        StateUtils.printMessage("***Create Team State***");
    }
}
