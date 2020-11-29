package com.TrophySystem.Observers;

import com.TrophySystem.AwardCeremony;
import com.TrophySystem.IPerformanceObserver;
import com.TrophySystem.ITrophyNominees;

public class GoalieObserver implements IPerformanceObserver {
    ITrophyNominees awardTrophy;

    public GoalieObserver(){
        awardTrophy = new AwardCeremony();
    }

    public void update(String goalieName, int goaliePoints){
        awardTrophy.goalieNominee(goalieName, goaliePoints);
    }
}
