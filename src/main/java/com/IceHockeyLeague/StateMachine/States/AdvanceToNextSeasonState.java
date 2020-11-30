package com.IceHockeyLeague.StateMachine.States;

import com.AbstractAppFactory;
import com.IO.IAppOutput;
import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Player.*;
import com.IceHockeyLeague.LeagueManager.Scheduler.IScheduleSystem;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.StateMachine.IStateMachine;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.Month;

public class AdvanceToNextSeasonState extends AbstractState {
    private static final String RETIRED_PLAYERS_START = "--------------- Retired Players -----------------";
    private static final String RETIRED_PLAYERS_END = "--------------- Retired Players end -------------";
    private static final String STANLEY_CUP_WINNER_START = "********** STANLEY CUP WINNER ************";
    private static final String STANLEY_CUP_WINNER_END = "******************************************";
    private static final String LEAGUE_AVERAGES_START = "----------- League averages per game after playoff -----------";
    private static final String LEAGUE_AVERAGES_END = "--------------------- League averages end --------------------";
    private static final Logger LOGGER = LogManager.getLogger(AdvanceToNextSeasonState.class);

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

        LOGGER.info("Adjusting age of all players in the league " + league.getLeagueName() + " as per the new date " + retirementCheckingDate.toString());
        playerCareerProgression.adjustLeaguePlayersAge(league, retirementCheckingDate);
        league.setLeagueDate(retirementCheckingDate);

        appOutput.display(RETIRED_PLAYERS_START);
        LOGGER.info("Performing retirement check on all players in the league " + league.getLeagueName() + " on " + retirementCheckingDate.toString());
        playerCareerProgression.performLeaguePlayersRetirement(league);
        for (ITeamPlayer teamPlayer : league.getRetiredTeamPlayers()) {
            appOutput.display(teamPlayer.getPlayerName());
        }
        for (IFreeAgent freeAgent : league.getRetiredFreeAgents()) {
            appOutput.display(freeAgent.getPlayerName());
        }
        appOutput.display(RETIRED_PLAYERS_END);

        LOGGER.info("Adjusting age of all players in the league " + league.getLeagueName() + " as per the new date " + draftingDate.toString());
        playerCareerProgression.adjustLeaguePlayersAge(league, draftingDate);
        league.setLeagueDate(draftingDate);

        AbstractState draftingState = stateMachineFactory.createDraftingState();
        LOGGER.info("Creating the inner drafting state-machine with starting state as " + draftingDate.getClass().getSimpleName() + "...");
        IStateMachine draftingSimulation = stateMachineFactory.createStateMachine(draftingState);
        LOGGER.info("Executing the inner drafting state-machine...");
        draftingSimulation.onExecution();

        LOGGER.info("Adjusting age of all players in the league " + league.getLeagueName() + " as per the new date " + newSeasonStartDate.toString());
        playerCareerProgression.adjustLeaguePlayersAge(league, newSeasonStartDate);
        league.setLeagueDate(newSeasonStartDate);

        appOutput.display(STANLEY_CUP_WINNER_START);
        String stanleyCupWinner = league.getScheduleSystem().getStanleyCupWinner().getTeamName();
        LOGGER.info("Stanley cup winner for season " + regularSeasonStartDate.getYear() + "-" + newSeasonStartDate.getYear() + " is: " + stanleyCupWinner);
        appOutput.display(stanleyCupWinner);
        appOutput.display(STANLEY_CUP_WINNER_END);

        float goalsAverage, penaltiesAverage, shotsAverage, savesAverage;
        int totalGoals = league.getGameSimulationSystem().getTotalGoals();
        int totalPenalties = league.getGameSimulationSystem().getTotalPenalties();
        int totalShots = league.getGameSimulationSystem().getTotalShots();
        int totalSaves = league.getGameSimulationSystem().getTotalSaves();
        int totalGames = league.getGameSimulationSystem().getNumberOfGamesPlayed();
        goalsAverage = (float) totalGoals / totalGames;
        penaltiesAverage = (float) totalPenalties / totalGames;
        shotsAverage = (float) totalShots / totalGames;
        savesAverage = (float) totalSaves / totalGames;

        appOutput.display(LEAGUE_AVERAGES_START);
        appOutput.display("Goals: " + goalsAverage);
        appOutput.display("Penalties: " + penaltiesAverage);
        appOutput.display("Shots: " + shotsAverage);
        appOutput.display("Saves: " + savesAverage);
        appOutput.display(LEAGUE_AVERAGES_END);
        LOGGER.info("League average after regular season");
        LOGGER.info("Goals:" + goalsAverage + " Penalties:" + penaltiesAverage + " Shots:" + shotsAverage + " Saves:" + savesAverage);

        return stateMachineFactory.createPersistState();
    }

}
