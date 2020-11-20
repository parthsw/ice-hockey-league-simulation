package com.Trading;

import com.IceHockeyLeague.LeagueManager.League.ILeague;

public interface ISimulateTrade {
    void simulateTrade(ILeague league, int lossPointValue, int maxPlayersPerTrade, float randomAcceptChance);

    void simulate();
}
