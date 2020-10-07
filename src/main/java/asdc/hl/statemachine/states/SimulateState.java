package asdc.hl.statemachine.states;

import asdc.hl.statemachine.StateMachine;

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
        this.runNestedLeagueSimulationStateMachine(this.noOfSeasons);
        return null;
    }

    private void runNestedLeagueSimulationStateMachine(int noOfSeasons) {
        for(int i=1; i<= noOfSeasons; i++) {
            new DummyState(stateMachine, i).onRun();
        }
    }

}
