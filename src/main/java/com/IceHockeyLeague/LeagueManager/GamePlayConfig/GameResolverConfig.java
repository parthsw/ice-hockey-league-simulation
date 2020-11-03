package com.IceHockeyLeague.LeagueManager.GamePlayConfig;

public class GameResolverConfig implements IGameResolverConfig {
    private float randomWinChance;

    @Override
    public void setRandomWinChance(float winChance) {
        randomWinChance = winChance;
    }

    @Override
    public float getRandomWinChance() {
        return randomWinChance;
    }
}
