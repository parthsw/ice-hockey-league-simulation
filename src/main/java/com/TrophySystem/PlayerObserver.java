package com.TrophySystem;

import com.TrophySystem.AwardCeremony;
import com.TrophySystem.IPerformanceObserver;


public class PlayerObserver implements IPerformanceObserver {
    ITrophyNominees awardTrophy;

    public PlayerObserver(){
        awardTrophy = new AwardCeremony();
    }

    public void update(String playerName, int playerPoints){
        awardTrophy.playerNominee(playerName, playerPoints);
    }
}
