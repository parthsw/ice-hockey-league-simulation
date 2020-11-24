package com.IceHockeyLeague.StateMachine;

import com.IceHockeyLeague.StateMachine.States.AbstractState;

public class StateMachine implements IStateMachine {
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
        while(true) {
            if(currentState == null) {
                break;
            } else {
                currentState = currentState.onRun();
            }
        }
    }

}
