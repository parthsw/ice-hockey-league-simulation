package com.IceHockeyLeague.StateMachine.States;

import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Player.IPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.StateMachine.AbstractStateMachineFactory;

public class AgingState extends AbstractState {

    @Override
    public AbstractState onRun() {
        AbstractState nextState;
        ILeague league = getLeague();

        for (IConference conference : league.getConferences()) {
            for (IDivision division : conference.getDivisions()) {
                for (ITeam team : division.getTeams()) {
                    for (IPlayer teamPlayer : team.getPlayers()) {
                        teamPlayer.agePlayerByDays(1);
                    }
                }
            }
        }

        for (IPlayer freeAgent : league.getFreeAgents()) {
            freeAgent.agePlayerByDays(1);
        }

        for (IPlayer retiredFreeAgent : league.getRetiredFreeAgents()) {
            retiredFreeAgent.agePlayerByDays(1);
        }

        for (IPlayer retiredTeamPlayer : league.getRetiredTeamPlayers()) {
            retiredTeamPlayer.agePlayerByDays(1);
        }

        if (league.getScheduleSystem().isStanleyCupWinnerDetermined()) {
            nextState = AbstractStateMachineFactory.getFactory().getAdvanceToNextSeasonState();
        }
        else {
            nextState = AbstractStateMachineFactory.getFactory().getPersistState();
        }
        return nextState;
    }

    @Override
    public void welcomeMessage() {

    }
}
