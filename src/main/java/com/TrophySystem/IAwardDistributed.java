package com.TrophySystem;

public interface IAwardDistributed {
    void presidentTrophy(String highestPointsTeam);

    void calderMemorialTrophy(String bestDraftedPlayer);

    void vezinaTrophy(String bestGoalie);

    void mauriceRichardTrophy(String topGoalScorer);

    void robHawkeyMemorialCup(String bestDefenseMen);

    void participationAward(String lowestPointsTeam);
}
