package com.IceHockeyLeague.LeagueManager.GamePlayConfig;

public class TradingConfig implements ITradingConfig {
    private int lossPoint;
    private float randomTradeOfferChance;
    private int maxPlayersPerTrade;
    private float randomAcceptanceChance;

    @Override
    public void setLossPoint(int lossPoint) {
        this.lossPoint = lossPoint;
    }

    @Override
    public int getLossPoint() {
        return lossPoint;
    }

    @Override
    public void setRandomTradeOfferChance(float randomTradeOfferChance) {
        this.randomTradeOfferChance = randomTradeOfferChance;
    }

    @Override
    public float getRandomTradeOfferChance() {
        return randomTradeOfferChance;
    }

    @Override
    public void setMaxPlayersPerTrade(int maxPlayersPerTrade) {
        this.maxPlayersPerTrade = maxPlayersPerTrade;
    }

    @Override
    public int getMaxPlayersPerTrade() {
        return maxPlayersPerTrade;
    }

    @Override
    public void setRandomAcceptanceChance(float randomAcceptanceChance) {
        this.randomAcceptanceChance = randomAcceptanceChance;
    }

    @Override
    public float getRandomAcceptanceChance() {
        return randomAcceptanceChance;
    }

}
