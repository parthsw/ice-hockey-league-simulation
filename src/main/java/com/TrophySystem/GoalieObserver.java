package com.TrophySystem;

public class GoalieObserver implements IPerformanceObserver {
    ITrophyContenders awardTrophy;

    public GoalieObserver(){
        awardTrophy = new TrophyDistribution();
    }

    public void update(String goalieName, int goaliePoints){
        awardTrophy.goalieContenders(goalieName, goaliePoints);
    }
}
