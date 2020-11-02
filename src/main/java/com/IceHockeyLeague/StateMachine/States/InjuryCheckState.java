package com.IceHockeyLeague.StateMachine.States;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IInjuryConfig;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerCareerProgression;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.StateMachine.AbstractStateMachineFactory;

public class InjuryCheckState extends AbstractState {

    private final ITeam teamA;
    private final ITeam teamB;

    public InjuryCheckState(ITeam teamA, ITeam teamB) {
        this.teamA = teamA;
        this.teamB = teamB;
    }

    @Override
    public AbstractState onRun() {
        AbstractState nextState;
        ILeague league = getLeague();
        IPlayerCareerProgression playerCareerProgression = AbstractLeagueManagerFactory.getFactory().getPlayerCareerProgression();
        IInjuryConfig injuryConfig = league.getGamePlayConfig().getInjuryConfig();

        for (ITeamPlayer teamPlayer: teamA.getPlayers()) {
            teamPlayer.isInjured(playerCareerProgression, injuryConfig, league.getLeagueDate());
        }

        for (ITeamPlayer teamPlayer: teamB.getPlayers()) {
            teamPlayer.isInjured(playerCareerProgression, injuryConfig, league.getLeagueDate());
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
