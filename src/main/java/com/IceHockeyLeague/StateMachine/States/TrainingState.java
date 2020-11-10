package com.IceHockeyLeague.StateMachine.States;

import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Team.ITeamTraining;
import com.IceHockeyLeague.LeagueManager.Team.TeamTraining;
import com.IceHockeyLeague.StateMachine.AbstractStateMachineFactory;

public class TrainingState extends AbstractState {

    @Override
    public AbstractState onRun() {
        AbstractState nextState;
        ILeague league = getLeague();
        league.incrementDaysSinceLastStatIncrease();
        int daysUntilStatIncreaseCheck = league.getGamePlayConfig().getTrainingConfig().getDaysUntilStatIncreaseCheck();
        int daysSinceLastStatIncrease = league.getDaysSinceLastStatIncrease();
        if (daysSinceLastStatIncrease > daysUntilStatIncreaseCheck) {
            ITeamTraining teamTraining = new TeamTraining();

            for (IConference conference : league.getConferences()) {
                for (IDivision division : conference.getDivisions()) {
                    for (ITeam team : division.getTeams()) {
                        teamTraining.trainTeam(league, team.getPlayers(), team.getCoach().getCoachStats());
                    }
                }
            }
        }

        if (league.getScheduleSystem().anyUnplayedGamesOnThisDate(league.getLeagueDate())) {
            nextState = AbstractStateMachineFactory.getFactory().getSimulateGameState();
        }
        else if (league.getLeagueDate().isAfter(league.getScheduleSystem().getTradeDeadline())) {
            nextState = AbstractStateMachineFactory.getFactory().getAgingState();
        }
        else {
            nextState = AbstractStateMachineFactory.getFactory().getExecuteTradesState();
        }

        return nextState;
    }
}
