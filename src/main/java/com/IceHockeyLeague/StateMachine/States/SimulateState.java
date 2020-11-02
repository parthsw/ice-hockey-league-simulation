package com.IceHockeyLeague.StateMachine.States;

import com.IO.IAppInput;
import com.IO.IAppOutput;
import com.IceHockeyLeague.StateMachine.AbstractStateMachineFactory;

public class SimulateState extends AbstractState {

    private final IAppInput appInput;
    private final IAppOutput appOutput;
    private final int numberOfSeasons;

    public SimulateState(IAppInput appInput, IAppOutput appOutput, int noOfSeasons) {
        this.appInput = appInput;
        this.appOutput = appOutput;
        this.numberOfSeasons = noOfSeasons;
    }

    @Override
    public AbstractState onRun() {
        for (int i = 0; i < numberOfSeasons; i++) {
            AbstractState initializeSeasonState = AbstractStateMachineFactory.getFactory().getInitializeSeasonState();
            AbstractStateMachineFactory.getFactory().getStateMachine(initializeSeasonState).onExecution();
        }
        return null;
    }

    @Override
    public void welcomeMessage() {

    }
}
