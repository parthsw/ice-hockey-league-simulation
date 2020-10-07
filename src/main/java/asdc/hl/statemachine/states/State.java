package asdc.hl.statemachine.states;

import asdc.hl.statemachine.StateMachine;

public abstract class State {
    static StateMachine stateMachine;

    State(StateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    public abstract State onRun();
}
