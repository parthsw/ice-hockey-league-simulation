package com.IceHockeyLeague.StateMachine.States;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;

public class PersistState extends AbstractState {
    private final IStateMachineFactory stateMachineFactory = AbstractAppFactory.getStateMachineFactory();

    @Override
    public AbstractState onRun() {
        AbstractState nextState;
        ILeague league = getLeague();

        if (league.getScheduleSystem().isStanleyCupWinnerDetermined()) {
            league.saveCompleteLeague();
            nextState = null;
        }
        else {
            nextState = stateMachineFactory.createAdvanceTimeState();
        }

        return nextState;
    }
}
