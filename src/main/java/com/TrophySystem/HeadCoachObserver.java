package com.TrophySystem.Observers;

import com.TrophySystem.AwardCeremony;
import com.TrophySystem.Interfaces.IPerformanceObserver;
import com.TrophySystem.Interfaces.ITrophyNominees;

public class HeadCoachObserver implements IPerformanceObserver {

    ITrophyNominees awardTrophy;

    public HeadCoachObserver(){
        awardTrophy = new AwardCeremony();
    }

    public void update(String coachName, int coachPoints){
        awardTrophy.coachNominees(coachName, coachPoints);
    }
}
