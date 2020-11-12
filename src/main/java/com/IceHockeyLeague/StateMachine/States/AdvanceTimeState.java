package com.IceHockeyLeague.StateMachine.States;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerCareerProgression;
import com.IceHockeyLeague.LeagueManager.Player.IRandomChance;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;

import java.time.LocalDate;

public class AdvanceTimeState extends AbstractState {

    @Override
    public AbstractState onRun() {
        ILeagueManagerFactory leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        IRandomChance randomChance = leagueManagerFactory.createRandomChance();
        IStateMachineFactory stateMachineFactory = AbstractAppFactory.getStateMachineFactory();
        AbstractState nextState;
        ILeague league = getLeague();

        league.incrementLeagueDate();

        LocalDate todayDate = league.getLeagueDate();

        IPlayerCareerProgression playerCareerProgression = leagueManagerFactory.createPlayerCareerProgression(randomChance);
        for (IConference conference: league.getConferences()) {
            for (IDivision division: conference.getDivisions()) {
                for (ITeam team: division.getTeams()) {
                    for (ITeamPlayer teamPlayer: team.getPlayers()) {
                        if (teamPlayer.getInjuredStatus()) {
                            teamPlayer.isRecovered(playerCareerProgression, todayDate);
                        }
                    }
                }
            }
        }

        LocalDate regularSeasonEndDate = league.getScheduleSystem().getRegularSeasonEndDate();
        if (todayDate.isEqual(regularSeasonEndDate)) {
            nextState = stateMachineFactory.createGeneratePlayoffScheduleState();
        }
        else {
            nextState = stateMachineFactory.createTrainingState();
        }

        return nextState;
    }
}
