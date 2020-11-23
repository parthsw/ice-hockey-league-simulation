package com.IceHockeyLeague.StateMachine.States;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IGamePlayConfig;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Player.*;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;

import java.time.LocalDate;

public class AdvanceTimeState extends AbstractState {
    private final ILeagueManagerFactory leagueManagerFactory;
    private final IStateMachineFactory stateMachineFactory;
    private ILeague league;

    public AdvanceTimeState() {
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        stateMachineFactory = AbstractAppFactory.getStateMachineFactory();
    }

    @Override
    public AbstractState onRun() {
        IRandomChance randomChance = leagueManagerFactory.createRandomChance();
        IPlayerCareerProgression playerCareerProgression = leagueManagerFactory.createPlayerCareerProgression(randomChance);
        AbstractState nextState;

        league = getLeague();
        league.incrementLeagueDate();
        LocalDate todayDate = league.getLeagueDate();

        for (IConference conference: league.getConferences()) {
            for (IDivision division: conference.getDivisions()) {
                for (ITeam team: division.getTeams()) {
                    for (ITeamPlayer teamPlayer: team.getPlayers()) {
                        if (teamPlayer.getInjuredStatus()) {
                            teamPlayer.isRecovered(playerCareerProgression, todayDate);
                            handlePlayerStatDecay(teamPlayer, randomChance);
                        }
                    }
                }
            }
        }

        for (IFreeAgent freeAgent: league.getFreeAgents()) {
            handlePlayerStatDecay(freeAgent, randomChance);
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

    private void handlePlayerStatDecay(IPlayer player, IRandomChance randomChance) {
        IGamePlayConfig gamePlayConfig = league.getGamePlayConfig();

        if (player.isBirthDay(league.getLeagueDate())) {
            player.performStatDecay(gamePlayConfig.getAgingConfig(), randomChance);
        }
    }
}
