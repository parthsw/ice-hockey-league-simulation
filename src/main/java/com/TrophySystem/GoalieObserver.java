package com.TrophySystem;

import com.AbstractAppFactory;
import com.TrophySystem.TrophyDistribution;
import com.TrophySystem.IPerformanceObserver;
import com.TrophySystem.ITrophyContenders;

public class GoalieObserver implements IPerformanceObserver {
    ITrophyContenders awardTrophy;

    public GoalieObserver(){
        ITrophySystemFactory trophySystemFactory = AbstractAppFactory.getTrophySystemFactory();
        awardTrophy = trophySystemFactory.trophyDistribution();
    }

    public void update(String goalieName, int goaliePoints){
        awardTrophy.goalieContenders(goalieName, goaliePoints);
    }
}
