package com.TrophySystem.Observers;

import com.TrophySystem.TrophyDistribution;
import com.TrophySystem.IPerformanceObserver;
import com.TrophySystem.ITrophyContenders;

public class GoalieObserver implements IPerformanceObserver {
    ITrophyContenders awardTrophy;

    public GoalieObserver(){
        awardTrophy = new TrophyDistribution();
    }

    public void update(String goalieName, int goaliePoints){
        awardTrophy.goalieContenders(goalieName, goaliePoints);
    }
}
