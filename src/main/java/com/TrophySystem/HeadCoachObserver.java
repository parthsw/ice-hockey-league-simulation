package com.TrophySystem;

import com.TrophySystem.AwardCeremony;
import com.TrophySystem.IPerformanceObserver;
import com.TrophySystem.ITrophyNominees;

public class HeadCoachObserver implements IPerformanceObserver {

    ITrophyNominees awardTrophy;

    public HeadCoachObserver(){
        awardTrophy = new AwardCeremony();
    }

    public void update(String coachName, int coachPoints){
        awardTrophy.coachNominees(coachName, coachPoints);
    }
}