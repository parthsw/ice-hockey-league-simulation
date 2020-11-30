package com.IceHockeyLeague.StateMachine.States;

import com.AbstractAppFactory;
import com.IO.IAppOutput;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Player.*;
import com.IceHockeyLeague.LeagueManager.Scheduler.IScheduleSystem;
import com.IceHockeyLeague.StateMachine.IStateMachine;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;

import java.time.LocalDate;
import java.time.Month;

public class AdvanceToNextSeasonState extends AbstractState {
    private static final String RETIRED_PLAYERS_START = "--------------- Retired Players -----------------";
    private static final String RETIRED_PLAYERS_END = "--------------- Retired Players end -------------";
    private static final String STANLEY_CUP_WINNER_START = "********** STANLEY CUP WINNER ************";
    private static final String STANLEY_CUP_WINNER_END = "******************************************";

    private final IAppOutput appOutput;
    private final IPlayerCareerProgression playerCareerProgression;
    private final IStateMachineFactory stateMachineFactory;

    public AdvanceToNextSeasonState(IAppOutput appOutput, IPlayerCareerProgression playerCareerProgression) {
        this.appOutput = appOutput;
        this.playerCareerProgression = playerCareerProgression;
        stateMachineFactory = AbstractAppFactory.getStateMachineFactory();
    }

    @Override
    public AbstractState onRun() {
        ILeague league = getLeague();
        IScheduleSystem scheduleSystem = league.getScheduleSystem();
        LocalDate regularSeasonStartDate = scheduleSystem.getRegularSeasonStartDate();
        int nextSeasonStartYear = regularSeasonStartDate.getYear() + 1;

        LocalDate retirementCheckingDate = LocalDate.of(nextSeasonStartYear, Month.MAY, 20);
        LocalDate draftingDate = LocalDate.of(nextSeasonStartYear, Month.JULY, 15);
        LocalDate newSeasonStartDate = LocalDate.of(nextSeasonStartYear, Month.SEPTEMBER, 29);

        playerCareerProgression.adjustLeaguePlayersAge(league, retirementCheckingDate);
        league.setLeagueDate(retirementCheckingDate);

        appOutput.display(RETIRED_PLAYERS_START);
        playerCareerProgression.performLeaguePlayersRetirement(league);
        appOutput.display(RETIRED_PLAYERS_END);

        playerCareerProgression.adjustLeaguePlayersAge(league, draftingDate);
        league.setLeagueDate(draftingDate);

        AbstractState draftingState = stateMachineFactory.createDraftingState();
        IStateMachine draftingSimulation = stateMachineFactory.createStateMachine(draftingState);
        draftingSimulation.onExecution();

        playerCareerProgression.adjustLeaguePlayersAge(league, newSeasonStartDate);
        league.setLeagueDate(newSeasonStartDate);

        appOutput.display(STANLEY_CUP_WINNER_START);
        appOutput.display(league.getScheduleSystem().getStanleyCupWinner().getTeamName());
        appOutput.display(STANLEY_CUP_WINNER_END);

        return stateMachineFactory.createPersistState();
    }

}
