package com.IceHockeyLeague.StateMachine.States;

import com.AbstractAppFactory;
import com.IO.IAppOutput;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Scheduler.ISchedule;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SimulateGameState extends AbstractState {

    private final IAppOutput appOutput;
    private final IStateMachineFactory stateMachineFactory;
    private static final Logger LOGGER = LogManager.getLogger(SimulateGameState.class);

    public SimulateGameState(IAppOutput appOutput) {
        this.appOutput = appOutput;
        stateMachineFactory = AbstractAppFactory.getStateMachineFactory();
    }

    @Override
    public AbstractState onRun() {
        ILeague league = getLeague();
        IConference winningTeamConference;
        IDivision winningTeamDivision;
        ITeam winningTeam;
        IConference losingTeamConference;
        IDivision losingTeamDivision;
        ITeam losingTeam;

        ISchedule schedule = league.getScheduleSystem().getScheduledMatchOnThisDate(league.getLeagueDate());
        ITeam teamA = schedule.getFirstTeam();
        ITeam teamB = schedule.getSecondTeam();

        winningTeam = league.getGameSimulationSystem().simulateGameAndGetWinner(teamA, teamB);

        if (winningTeam == teamA) {
            winningTeamConference = schedule.getFirstConference();
            winningTeamDivision = schedule.getFirstDivision();
            losingTeam = teamB;
            losingTeamConference = schedule.getSecondConference();
            losingTeamDivision = schedule.getSecondDivision();
        }
        else {
            winningTeam = teamB;
            winningTeamConference = schedule.getSecondConference();
            winningTeamDivision = schedule.getSecondDivision();
            losingTeam = teamA;
            losingTeamConference = schedule.getFirstConference();
            losingTeamDivision = schedule.getFirstDivision();
        }

        schedule.setWinningTeam(winningTeam);
        league.getScheduleSystem().updateScheduleAfterGamePlayed(schedule);
        league.getStandingSystem().updateStatsForWinningTeam(winningTeamConference, winningTeamDivision, winningTeam);
        league.getStandingSystem().updateStatsForLosingTeam(losingTeamConference, losingTeamDivision, losingTeam);
        winningTeam.decrementLossPointValue();
        losingTeam.incrementLossPointValue();


        LOGGER.info("Game: " + teamA.getTeamName() + " vs " + teamB.getTeamName());
        LOGGER.info("Winner: " + winningTeam.getTeamName());
        appOutput.display("Game: " + teamA.getTeamName() + " vs " + teamB.getTeamName());
        appOutput.display("Winner: " + winningTeam.getTeamName());

        return stateMachineFactory.createInjuryCheckState(teamA, teamB);
    }
}
