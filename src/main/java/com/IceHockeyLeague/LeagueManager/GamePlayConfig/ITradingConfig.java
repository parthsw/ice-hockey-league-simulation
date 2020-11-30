package com.IceHockeyLeague.LeagueManager.GamePlayConfig;

public interface ITradingConfig {
    void setLossPoint(int lossPoint);

    int getLossPoint();

    void setRandomTradeOfferChance(float randomTradeOfferChance);

    float getRandomTradeOfferChance();

    void setMaxPlayersPerTrade(int maxPlayersPerTrade);

    int getMaxPlayersPerTrade();

    void setRandomAcceptanceChance(float randomAcceptanceChance);

    float getRandomAcceptanceChance();
}
