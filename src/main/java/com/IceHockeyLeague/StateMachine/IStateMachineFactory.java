package com.IceHockeyLeague.StateMachine;

import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.StateMachine.States.AbstractState;

public interface IStateMachineFactory {
    IStateMachine createStateMachine(AbstractState abstractState);
    AbstractState createImportState();
    AbstractState createCreateTeamState();
    AbstractState createLoadTeamState();
    AbstractState createPlayerChoiceState();
    AbstractState createSimulateState(int numberOfSeasons);
    AbstractState createInitializeSeasonState();
    AbstractState createAdvanceTimeState();
    AbstractState createGeneratePlayoffScheduleState();
    AbstractState createTrainingState();
    AbstractState createSimulateGameState();
    AbstractState createInjuryCheckState(ITeam teamA, ITeam teamB);
    AbstractState createExecuteTradesState();
    AbstractState createAgingState();
    AbstractState createAdvanceToNextSeasonState();
    AbstractState createPersistState();
    AbstractState createTrophyState();
}
