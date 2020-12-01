package com.IceHockeyLeague.StateMachine.States;

import com.AbstractAppFactory;
import com.IO.IAppOutput;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GeneratePlayoffScheduleState extends AbstractState {
    private static final String LEAGUE_AVERAGES_START = "----------- League averages per game after regular season -----------";
    private static final String LEAGUE_AVERAGES_END = "------------------------ League averages end ------------------------";
    private final IStateMachineFactory stateMachineFactory;
    private final IAppOutput appOutput;
    private static final Logger LOGGER = LogManager.getLogger(GeneratePlayoffScheduleState.class);

    public GeneratePlayoffScheduleState(IAppOutput appOutput) {
        this.appOutput = appOutput;
        stateMachineFactory = AbstractAppFactory.getStateMachineFactory();
    }

    @Override
    public AbstractState onRun() {
        ILeague league = getLeague();
        league.getScheduleSystem().generatePlayoffSchedule(league, league.getStandingSystem());
        showLeagueStatistics(league, appOutput, LOGGER);
        return stateMachineFactory.createTrainingState();
    }

    public static void showLeagueStatistics(ILeague league, IAppOutput appOutput, Logger logger) {
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
        logger.info("League average after regular season");
        logger.info("Goals:" + goalsAverage + " Penalties:" + penaltiesAverage + " Shots:" + shotsAverage + " Saves:" + savesAverage);
    }

}
