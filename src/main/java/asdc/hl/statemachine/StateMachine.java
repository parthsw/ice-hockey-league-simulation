package asdc.hl.statemachine;

import asdc.hl.statemachine.states.ImportState;
import asdc.hl.statemachine.states.State;

public class StateMachine {
    private State state;
    private int noOfSeasons;

    public StateMachine() {
        this.state = new ImportState(this);
    }

    public void run() {
        do {
            state = state.onRun();
        } while(state != null);
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setNoOfSeasons(int noOfSeasons) {
        this.noOfSeasons = noOfSeasons;
    }

    public int getNoOfSeasons() {
        return this.noOfSeasons;
    }
}
