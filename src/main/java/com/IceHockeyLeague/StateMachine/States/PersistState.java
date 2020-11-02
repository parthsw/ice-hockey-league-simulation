package com.IceHockeyLeague.StateMachine.States;

import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.StateMachine.AbstractStateMachineFactory;

public class PersistState extends AbstractState {

    @Override
    public AbstractState onRun() {
        AbstractState nextState;
        ILeague league = getLeague();
        league.saveCompleteLeague();

        if (league.getScheduleSystem().isStanleyCupWinnerDetermined()) {
            nextState = null;
        }
        else {
            nextState = AbstractStateMachineFactory.getFactory().getAdvanceTimeState();
        }

        return nextState;
    }

    @Override
    public void welcomeMessage() {

    }
}
