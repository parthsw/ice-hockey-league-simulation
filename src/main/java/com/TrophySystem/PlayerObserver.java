package com.TrophySystem;


import com.AbstractAppFactory;

public class PlayerObserver implements IPerformanceObserver {
    ITrophyContenders awardTrophy;

    public PlayerObserver() {
        ITrophySystemFactory trophySystemFactory = AbstractAppFactory.getTrophySystemFactory();
        awardTrophy = trophySystemFactory.trophyDistribution();
    }

    public void update(String playerName, int playerPoints) {
        awardTrophy.playerContenders(playerName, playerPoints);
    }
}
