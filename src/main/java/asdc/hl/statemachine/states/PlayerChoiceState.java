package asdc.hl.statemachine.states;

import asdc.hl.statemachine.StateMachine;

public class PlayerChoiceState extends State {

    public PlayerChoiceState(StateMachine stateMachine) {
        super(stateMachine);
    }

    @Override
    public State onRun() {
        stateMachine.setNoOfSeasons(this.promptForNumberOfSeasons());
        return new SimulateState(stateMachine);
    }

    private int promptForNumberOfSeasons() {
        int noOfSeasons = 0;
        try {
            StateUtils.printMessage("How many seasons do you want to simulate?");
            noOfSeasons = Integer.parseInt(StateUtils.promptForUserInput().trim());
        }
        catch(NumberFormatException exception) {
            StateUtils.printErrorMessage("Please provide a valid number of seasons to simulate.\n");
            int seasons = this.promptForNumberOfSeasons();
            return seasons;
        }
        return noOfSeasons;
    }

}
