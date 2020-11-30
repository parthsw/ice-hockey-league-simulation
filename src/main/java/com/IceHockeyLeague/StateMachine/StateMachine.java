package com.IceHockeyLeague.StateMachine;

import com.IceHockeyLeague.StateMachine.States.AbstractState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StateMachine implements IStateMachine {
    private final Logger LOGGER = LogManager.getLogger(StateMachine.class);
    private AbstractState currentState;

    StateMachine(AbstractState state) {
        this.setCurrentState(state);
    }

    @Override
    public void setCurrentState(AbstractState state) {
        currentState = state;
    }

    @Override
    public AbstractState getCurrentState() {
        return currentState;
    }

    @Override
    public void onExecution() {
        while (true) {
            if (currentState == null) {
                break;
            } else {
                LOGGER.info("Running the outer state-machine's " + currentState.getClass().getSimpleName() + "...");
                currentState = currentState.onRun();
            }
        }
    }

}
