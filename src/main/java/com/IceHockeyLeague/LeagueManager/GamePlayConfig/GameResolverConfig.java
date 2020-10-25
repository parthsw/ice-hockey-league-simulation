package com.IceHockeyLeague.LeagueManager.GamePlayConfig;

public class GameResolverConfig implements IGameResolverConfig {
    private double randomWinChance;

    @Override
    public void setRandomWinChance(double winChance) {
        randomWinChance = winChance;
    }

    @Override
    public double getRandomWinChance() {
        return randomWinChance;
    }
}
