package com.TrophySystem;

import com.AbstractAppFactory;

public class DefenceMenObserver implements IPerformanceObserver {
    ITrophyContenders awardTrophy;

    public DefenceMenObserver(){
        ITrophySystemFactory trophySystemFactory = AbstractAppFactory.getTrophySystemFactory();
        awardTrophy = trophySystemFactory.trophyDistribution();
    }

    public void update(String defenceMenName, int defenceMenPoints){
        awardTrophy.defenceMenContenders(defenceMenName, defenceMenPoints);
    }
}
