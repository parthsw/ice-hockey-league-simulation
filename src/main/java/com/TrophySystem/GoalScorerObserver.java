package com.TrophySystem.Observers;

import com.TrophySystem.AwardCeremony;
import com.TrophySystem.Interfaces.IPerformanceObserver;
import com.TrophySystem.Interfaces.ITrophyNominees;

public class GoalScorerObserver implements IPerformanceObserver {
        ITrophyNominees awardTrophy;

        public GoalScorerObserver(){
            awardTrophy = new AwardCeremony();
        }

        public void update(String goalScorerName, int goalPoints){
            awardTrophy.goalScorerNominee(goalScorerName, goalPoints);
        }
}
