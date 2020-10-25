package com.IceHockeyLeague.LeagueManager.GamePlayConfig;

public class TradingConfig implements ITradingConfig {
    private int lossPoint;
    private double randomTradeOfferChance;
    private int maxPlayersPerTrade;
    private double randomAcceptanceChance;

    @Override
    public void setLossPoint(int lossPoint) {
        this.lossPoint = lossPoint;
    }

    @Override
    public int getLossPoint() {
        return lossPoint;
    }

    @Override
    public void setRandomTradeOfferChance(double tradeOfferChance) {
        randomTradeOfferChance = tradeOfferChance;
    }

    @Override
    public double getRandomTradeOfferChance() {
        return randomTradeOfferChance;
    }

    @Override
    public void setMaxPlayersPerTrade(int maxPlayers) {
        maxPlayersPerTrade = maxPlayers;
    }

    @Override
    public int getMaxPlayersPerTrade() {
        return maxPlayersPerTrade;
    }

    @Override
    public void setRandomAcceptanceChance(double acceptanceChance) {
        randomAcceptanceChance = acceptanceChance;
    }

    @Override
    public double getRandomAcceptanceChance() {
        return randomAcceptanceChance;
    }
}
