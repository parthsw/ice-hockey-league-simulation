package com.IceHockeyLeague.StateMachine;

import com.IceHockeyLeague.LeagueManager.Team.ITeam;
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
    public abstract AbstractState getSimulateState(int numberOfSeasons);
    public abstract AbstractState getInitializeSeasonState();
    public abstract AbstractState getAdvanceTimeState();
    public abstract AbstractState getGeneratePlayoffScheduleState();
    public abstract AbstractState getTrainingState();
    public abstract AbstractState getSimulateGameState();
    public abstract AbstractState getInjuryCheckState(ITeam teamA, ITeam teamB);
    public abstract AbstractState getExecuteTradesState();
    public abstract AbstractState getAgingState();
    public abstract AbstractState getAdvanceToNextSeasonState();
    public abstract AbstractState getPersistState();
}
