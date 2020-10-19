package com.statemachine.states;

import com.statemachine.StateMachine;

public class SimulateState extends State {
    private final int noOfSeasons;

    public SimulateState(StateMachine stateMachine) {
        super(stateMachine);
        this.noOfSeasons = stateMachine.getNoOfSeasons();
    }

    @Override
    public State onRun() {
        // TODO: Spawn off a nested state machine in milestone 2 by creating a new instance of StateMachine context class
        // TODO: and setting an initial state to a particular state
        this.importWelcomeMessage();
        this.runNestedLeagueSimulationStateMachine(this.noOfSeasons);
        return null;
    }

    private void runNestedLeagueSimulationStateMachine(int noOfSeasons) {
        for(int i=1; i<= noOfSeasons; i++) {
            new DummyState(stateMachine, i).onRun();
        }
    }

    private void importWelcomeMessage() {
        StateUtils.printMessage("***Simulation State***");
    }
}
