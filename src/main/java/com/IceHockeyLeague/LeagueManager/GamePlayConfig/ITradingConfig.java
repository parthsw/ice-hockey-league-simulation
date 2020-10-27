package com.IceHockeyLeague.LeagueManager.GamePlayConfig;

public interface ITradingConfig {
    void setLossPoint(int lossPoint);
    int getLossPoint();

    void setRandomTradeOfferChance(double tradeOfferChance);
    double getRandomTradeOfferChance();

    void setMaxPlayersPerTrade(int maxPlayers);
    int getMaxPlayersPerTrade();

    void setRandomAcceptanceChance(double acceptanceChance);
    double getRandomAcceptanceChance();
}
