package com.IceHockeyLeague.StateMachine.States;

import com.IO.IAppInput;
import com.IO.IAppOutput;
import com.IceHockeyLeague.StateMachine.AbstractStateMachineFactory;

public class PlayerChoiceState extends AbstractState {
    private static final String NUMBER_OF_SEASONS = "Please provide the number of seasons to simulate";
    private static final String INVALID_FORMAT = "Invalid format for a number";

    private final IAppInput appInput;
    private final IAppOutput appOutput;

    public PlayerChoiceState(IAppInput appInput, IAppOutput appOutput) {
        this.appInput = appInput;
        this.appOutput = appOutput;
    }

    @Override
    public AbstractState onRun() {
        int noOfSeasons;
        while (true) {
            appOutput.display(NUMBER_OF_SEASONS);
            String seasons = appInput.getInput();
            try {
                noOfSeasons = Integer.parseInt(seasons);
                break;
            }
            catch (NumberFormatException e) {
                appOutput.displayError(INVALID_FORMAT);
            }
        }

        return AbstractStateMachineFactory.getFactory().getSimulateState(noOfSeasons);
    }

    @Override
    public void welcomeMessage() {

    }
}
