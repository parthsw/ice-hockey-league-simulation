package com.TrophySystem;

public class DefenceMenObserver implements IPerformanceObserver {
    ITrophyContenders awardTrophy;

    public DefenceMenObserver(){
        awardTrophy = new TrophyDistribution();
    }

    public void update(String defenceMenName, int defenceMenPoints){
        awardTrophy.defenceMenContenders(defenceMenName, defenceMenPoints);
    }
}
