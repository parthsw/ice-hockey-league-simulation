package com.IceHockeyLeague.StateMachine.States;

import com.AbstractAppFactory;
import com.IO.IAppInput;
import com.IO.IAppOutput;
import com.IceHockeyLeague.StateMachine.IStateMachine;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;

public class SimulateState extends AbstractState {
    private final IStateMachineFactory stateMachineFactory = AbstractAppFactory.getStateMachineFactory();
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
            AbstractState initializeSeasonState = stateMachineFactory.createInitializeSeasonState();
            IStateMachine simulationStateMachine = stateMachineFactory.createStateMachine(initializeSeasonState);
            simulationStateMachine.onExecution();
        }
        return null;
    }
}
