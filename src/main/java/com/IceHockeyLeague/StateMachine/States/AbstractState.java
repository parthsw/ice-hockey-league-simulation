package com.IceHockeyLeague.StateMachine.States;

import com.IceHockeyLeague.LeagueManager.League.ILeague;

public abstract class AbstractState {
    private static ILeague league;

    public ILeague getLeague() {
        return league;
    }

    public void setLeague(ILeague league) {
        AbstractState.league = league;
    }

    public abstract AbstractState onRun();
}
