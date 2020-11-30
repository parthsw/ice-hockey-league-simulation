package com.TrophySystem;

public class HeadCoachObserver implements IPerformanceObserver {

    ITrophyContenders awardTrophy;

    public HeadCoachObserver(){
        awardTrophy = new TrophyDistribution();
    }

    public void update(String coachName, int coachPoints){
        awardTrophy.goalScorerContenders(coachName, coachPoints);
    }
}
