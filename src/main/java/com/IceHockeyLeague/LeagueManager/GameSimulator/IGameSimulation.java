package com.IceHockeyLeague.LeagueManager.GameSimulator;

public interface IGameSimulation {
    IGameStats getGameStats();
    void setGameStats(IGameStats gameStats);
    void simulateGame();
}
