package com.IceHockeyLeague.StateMachine;

import com.IceHockeyLeague.StateMachine.States.AbstractState;

public abstract class AbstractStateMachineFactory {
    private static AbstractStateMachineFactory abstractStateMachineFactory;

    public static AbstractStateMachineFactory getFactory() {
        return abstractStateMachineFactory;
    }

    public static void setFactory(AbstractStateMachineFactory stateMachineFactory) {
        abstractStateMachineFactory = stateMachineFactory;
    }

    public abstract IStateMachine getStateMachine(AbstractState abstractState);
    public abstract AbstractState getImportState();
    public abstract AbstractState getCreateTeamState();
    public abstract AbstractState getLoadTeamState();
    public abstract AbstractState getPlayerChoiceState();
}
