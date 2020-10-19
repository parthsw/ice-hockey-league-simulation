package com.statemachine;

import com.leaguemodel.models.ILeague;
import com.statemachine.states.ImportState;
import com.statemachine.states.State;

public class StateMachine {
    private State state;
    private ILeague league;
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

    public void setLeague(ILeague league) {
        this.league = league;
    }

    public ILeague getLeague() {
        return league;
    }
}
