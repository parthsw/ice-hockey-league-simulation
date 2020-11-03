package com.IceHockeyLeague.LeagueManager.GamePlayConfig;

public interface ITradingConfig {
    void setLossPoint(int lossPoint);
    int getLossPoint();

    void setRandomTradeOfferChance(float tradeOfferChance);
    float getRandomTradeOfferChance();

    void setMaxPlayersPerTrade(int maxPlayers);
    int getMaxPlayersPerTrade();

    void setRandomAcceptanceChance(float acceptanceChance);
    float getRandomAcceptanceChance();
}
