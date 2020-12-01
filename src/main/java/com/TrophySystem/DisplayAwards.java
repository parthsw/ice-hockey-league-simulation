package com.TrophySystem;

import com.IO.IAppInput;
import com.IO.IAppOutput;

public class DisplayAwards implements IAwardDistributed {

    private final IAppOutput appOutput;

    public DisplayAwards(IAppOutput appOutput) {
        this.appOutput = appOutput;
    }

    public void presidentTrophy(String highestPointsTeam) {
        System.out.println(highestPointsTeam);
    }

    public void calderMemorialTrophy(String bestDraftedPlayer) {
        System.out.println(bestDraftedPlayer);
    }

    public void vezinaTrophy(String bestGoalie) {
        System.out.println(bestGoalie);
    }

    public void mauriceRichardTrophy(String topGoalScorer) {
        System.out.println(topGoalScorer);
    }

    public void robHawkeyMemorialCup(String bestDefenseMen) {
        System.out.println(bestDefenseMen);
    }

    public void participationAward(String lowestPointsTeam) {
        System.out.println(lowestPointsTeam);
    }
}
