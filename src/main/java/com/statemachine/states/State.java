package com.statemachine.states;

import com.statemachine.StateMachine;

public abstract class State {
    static StateMachine stateMachine;

    State(StateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    public abstract State onRun();
}
