package asdc.hl.statemachine.states;

import asdc.hl.statemachine.StateMachine;

public class LoadTeamState extends State {

    String teamName;

    public LoadTeamState(StateMachine stateMachine) {
        super(stateMachine);
    }

    @Override
    public State onRun() {
        this.promptForTeamName();
        return new PlayerChoiceState(stateMachine);
    }

    private void promptForTeamName() {
        while(true) {
            StateUtils.printMessage("Please provide name for an existing team:");
            teamName = StateUtils.promptForUserInput();

            boolean isValid = true;
            if(isValid) {
                break;
            } else {
                StateUtils.printErrorMessage("Please enter a valid team name to load.");
            }
        }
    }
}
