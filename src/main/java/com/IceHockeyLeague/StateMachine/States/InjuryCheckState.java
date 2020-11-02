package com.IceHockeyLeague.StateMachine.States;

import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.StateMachine.AbstractStateMachineFactory;

public class InjuryCheckState extends AbstractState {

    private ITeam teamA;
    private ITeam teamB;

    public InjuryCheckState(ITeam teamA, ITeam teamB) {
        this.teamA = teamA;
        this.teamB = teamB;
    }

    @Override
    public AbstractState onRun() {
        AbstractState nextState;
        ILeague league = getLeague();

        //TODO: check for injury

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

    @Override
    public void welcomeMessage() {

    }
}
