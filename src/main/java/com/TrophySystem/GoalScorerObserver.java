package com.TrophySystem;

import com.AbstractAppFactory;

public class GoalScorerObserver implements IPerformanceObserver {
        ITrophyContenders awardTrophy;

        public GoalScorerObserver(){
            ITrophySystemFactory trophySystemFactory = AbstractAppFactory.getTrophySystemFactory();
            awardTrophy = trophySystemFactory.trophyDistribution();
        }

        public void update(String goalScorerName, int goalPoints){
            awardTrophy.goalScorerContenders(goalScorerName, goalPoints);
        }
}
