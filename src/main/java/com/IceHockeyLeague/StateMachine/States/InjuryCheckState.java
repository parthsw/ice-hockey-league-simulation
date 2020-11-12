package com.IceHockeyLeague.StateMachine.States;

import com.AbstractAppFactory;
import com.IO.IAppOutput;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IInjuryConfig;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerCareerProgression;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;

public class InjuryCheckState extends AbstractState {
    private final IStateMachineFactory stateMachineFactory = AbstractAppFactory.getStateMachineFactory();
    private final ILeagueManagerFactory leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
    private final ITeam teamA;
    private final ITeam teamB;
    private final IAppOutput appOutput;

    public InjuryCheckState(IAppOutput appOutput, ITeam teamA, ITeam teamB) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.appOutput = appOutput;
    }

    @Override
    public AbstractState onRun() {
        AbstractState nextState;
        ILeague league = getLeague();
        IPlayerCareerProgression playerCareerProgression = leagueManagerFactory.createPlayerCareerProgression(leagueManagerFactory.createRandomChance());
        IInjuryConfig injuryConfig = league.getGamePlayConfig().getInjuryConfig();

        appOutput.display("Players injured during game: ");
        for (ITeamPlayer teamPlayer: teamA.getPlayers()) {
            if (teamPlayer.isInjured(playerCareerProgression, injuryConfig, league.getLeagueDate())) {
                appOutput.display(teamPlayer.getPlayerName() + " injured for " + teamPlayer.getDaysInjured() + " days");
            }
        }

        for (ITeamPlayer teamPlayer: teamB.getPlayers()) {
            if (teamPlayer.isInjured(playerCareerProgression, injuryConfig, league.getLeagueDate())) {
                appOutput.display(teamPlayer.getPlayerName() + " injured for " + teamPlayer.getDaysInjured() + " days");
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
