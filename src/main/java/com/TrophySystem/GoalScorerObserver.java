package com.TrophySystem;

import com.TrophySystem.AwardCeremony;
import com.TrophySystem.IPerformanceObserver;
import com.TrophySystem.ITrophyNominees;

public class GoalScorerObserver implements IPerformanceObserver {
        ITrophyNominees awardTrophy;

        public GoalScorerObserver(){
            awardTrophy = new AwardCeremony();
        }

        public void update(String goalScorerName, int goalPoints){
            awardTrophy.goalScorerNominee(goalScorerName, goalPoints);
        }
}
