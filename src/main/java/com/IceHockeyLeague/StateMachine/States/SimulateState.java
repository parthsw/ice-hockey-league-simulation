package com.IceHockeyLeague.StateMachine.States;

import com.AbstractAppFactory;
import com.IO.IAppInput;
import com.IO.IAppOutput;
import com.IceHockeyLeague.StateMachine.IStateMachine;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;

public class SimulateState extends AbstractState {
    private final IStateMachineFactory stateMachineFactory;
    private final int numberOfSeasons;

    public SimulateState(IAppInput appInput, IAppOutput appOutput, int noOfSeasons) {
        this.numberOfSeasons = noOfSeasons;
        stateMachineFactory = AbstractAppFactory.getStateMachineFactory();
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
