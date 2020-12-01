package com.IceHockeyLeague.StateMachine.States;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;
import com.IceHockeyLeague.Trading.ITradingFactory;
import com.IceHockeyLeague.Trading.SimulateTrade;

public class ExecuteTradesState extends AbstractState {
    private final IStateMachineFactory stateMachineFactory;
    private final ITradingFactory tradingFactory;

    public ExecuteTradesState() {
        stateMachineFactory = AbstractAppFactory.getStateMachineFactory();
        tradingFactory = AbstractAppFactory.getTradingFactory();
    }

    @Override
    public AbstractState onRun() {
        ILeague league = getLeague();
        int lossPoint = league.getGamePlayConfig().getTradingConfig().getLossPoint();
        int maxPlayersPerTrade = league.getGamePlayConfig().getTradingConfig().getMaxPlayersPerTrade();
        float randomAcceptChance = league.getGamePlayConfig().getTradingConfig().getRandomAcceptanceChance();

        SimulateTrade simulateTrade = tradingFactory.createSimulateTrade();
        simulateTrade.simulateTrade(league, lossPoint, maxPlayersPerTrade, randomAcceptChance);
        simulateTrade.simulate();

        return stateMachineFactory.createAgingState();
    }
}
