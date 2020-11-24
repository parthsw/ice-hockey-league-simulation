package com.IceHockeyLeague.StateMachine;

import com.IceHockeyLeague.StateMachine.States.AbstractState;

public interface IStateMachine {
    void setCurrentState(AbstractState abstractState);

    AbstractState getCurrentState();

    void onExecution();
}
