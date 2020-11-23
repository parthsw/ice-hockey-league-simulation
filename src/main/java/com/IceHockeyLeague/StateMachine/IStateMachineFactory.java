package com.IceHockeyLeague.StateMachine;

import com.IceHockeyLeague.LeagueManager.Draft.IDraftManager;
import com.IceHockeyLeague.LeagueManager.Player.IRandomPlayersGenerator;
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

    AbstractState createDraftingState(IRandomPlayersGenerator randomPlayersGenerator, IDraftManager draftManager);

    AbstractState createAdvanceToNextSeasonState();

    AbstractState createPersistState();
}
