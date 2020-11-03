package com.IceHockeyLeague.StateMachine.States;

import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.StateMachine.AbstractStateMachineFactory;
import com.Trading.SimulateTrade;

public class ExecuteTradesState extends AbstractState {
    @Override
    public AbstractState onRun() {
        ILeague league = getLeague();
        int lossPoint = league.getGamePlayConfig().getTradingConfig().getLossPoint();
        int maxPlayersPerTrade = league.getGamePlayConfig().getTradingConfig().getMaxPlayersPerTrade();
        float randomAcceptChance = league.getGamePlayConfig().getTradingConfig().getRandomAcceptanceChance();

        SimulateTrade simulateTrade = new SimulateTrade();
        simulateTrade.simulateTrade(league, lossPoint, maxPlayersPerTrade, randomAcceptChance);
        simulateTrade.simulate();

        return AbstractStateMachineFactory.getFactory().getAgingState();
    }
}
