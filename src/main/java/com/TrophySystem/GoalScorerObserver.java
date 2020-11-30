package com.TrophySystem;

public class GoalScorerObserver implements IPerformanceObserver {
        ITrophyContenders awardTrophy;

        public GoalScorerObserver(){
            awardTrophy = new TrophyDistribution();
        }

        public void update(String goalScorerName, int goalPoints){
            awardTrophy.goalScorerContenders(goalScorerName, goalPoints);
        }
}
