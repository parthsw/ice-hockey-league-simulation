package com.IceHockeyLeague.StateMachine.States;

import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Player.IPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.StateMachine.AbstractStateMachineFactory;

import java.time.LocalDate;
import java.time.Month;

import static java.time.temporal.ChronoUnit.DAYS;

public class AdvanceToNextSeasonState extends AbstractState {

    @Override
    public AbstractState onRun() {
        ILeague league = getLeague();

        int seasonStartYear = league.getScheduleSystem().getRegularSeasonStartDate().getYear();
        LocalDate newDateToSet = LocalDate.of(seasonStartYear + 1, Month.SEPTEMBER, 29);
        int numberOfDaysElapsed = (int)DAYS.between(league.getLeagueDate(), newDateToSet);

        for (IConference conference : league.getConferences()) {
            for (IDivision division : conference.getDivisions()) {
                for (ITeam team : division.getTeams()) {
                    for (IPlayer teamPlayer : team.getPlayers()) {
                        teamPlayer.agePlayerByDays(numberOfDaysElapsed);
                    }
                }
            }
        }

        for (IPlayer freeAgent : league.getFreeAgents()) {
            freeAgent.agePlayerByDays(numberOfDaysElapsed);
        }

        for (IPlayer retiredFreeAgent : league.getRetiredFreeAgents()) {
            retiredFreeAgent.agePlayerByDays(numberOfDaysElapsed);
        }

        for (IPlayer retiredTeamPlayer : league.getRetiredTeamPlayers()) {
            retiredTeamPlayer.agePlayerByDays(numberOfDaysElapsed);
        }

        return AbstractStateMachineFactory.getFactory().getPersistState();
    }

    @Override
    public void welcomeMessage() {

    }
}
