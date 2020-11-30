package com.TrophySystem;

import com.TrophySystem.AwardCeremony;
import com.TrophySystem.IPerformanceObserver;
import com.TrophySystem.ITrophyNominees;

public class DefenceMenObserver implements IPerformanceObserver {
    ITrophyNominees awardTrophy;

    public DefenceMenObserver(){
        awardTrophy = new AwardCeremony();
    }

    public void update(String defenceMenName, int defenceMenPoints){
        awardTrophy.defenceMenNominees(defenceMenName, defenceMenPoints);
    }
}
