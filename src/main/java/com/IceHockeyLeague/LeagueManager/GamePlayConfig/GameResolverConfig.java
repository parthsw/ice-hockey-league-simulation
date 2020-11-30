package com.IceHockeyLeague.LeagueManager.GamePlayConfig;

public class GameResolverConfig implements IGameResolverConfig {
    private float randomWinChance;

    @Override
    public void setRandomWinChance(float randomWinChance) {
        this.randomWinChance = randomWinChance;
    }

    @Override
    public float getRandomWinChance() {
        return randomWinChance;
    }

}
