package com.TrophySystem;


public class PlayerObserver implements IPerformanceObserver {
    ITrophyContenders awardTrophy;

    public PlayerObserver(){
        awardTrophy = new TrophyDistribution();
    }

    public void update(String playerName, int playerPoints){
        awardTrophy.playerNominee(playerName, playerPoints);
    }
}
