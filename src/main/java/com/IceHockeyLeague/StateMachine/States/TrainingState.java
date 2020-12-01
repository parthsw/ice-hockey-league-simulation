package com.IceHockeyLeague.StateMachine.States;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Team.ITeamTraining;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;

public class TrainingState extends AbstractState {
    private final IStateMachineFactory stateMachineFactory;
    private final ILeagueManagerFactory leagueManagerFactory;

    public TrainingState() {
        stateMachineFactory = AbstractAppFactory.getStateMachineFactory();
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
    }

    @Override
    public AbstractState onRun() {
        AbstractState nextState;
        ILeague league = getLeague();
        league.incrementDaysSinceLastStatIncrease();
        int daysUntilStatIncreaseCheck = league.getGamePlayConfig().getTrainingConfig().getDaysUntilStatIncreaseCheck();
        int daysSinceLastStatIncrease = league.getDaysSinceLastStatIncrease();
        if (daysSinceLastStatIncrease > daysUntilStatIncreaseCheck) {
            ITeamTraining teamTraining = leagueManagerFactory.createTeamTraining();

            for (IConference conference : league.getConferences()) {
                for (IDivision division : conference.getDivisions()) {
                    for (ITeam team : division.getTeams()) {
                        teamTraining.trainTeam(league, team.getPlayers(), team.getCoach().getCoachStats());
                    }
                }
            }
        }

        if (league.getScheduleSystem().anyUnplayedGamesOnThisDate(league.getLeagueDate())) {
            nextState = stateMachineFactory.createSimulateGameState();
        }
        else if (league.getLeagueDate().isAfter(league.getScheduleSystem().getTradeDeadline())) {
            nextState = stateMachineFactory.createAgingState();
        }
        else {
            nextState = stateMachineFactory.createExecuteTradesState();
        }

        return nextState;
    }
}
