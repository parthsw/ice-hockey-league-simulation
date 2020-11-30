package com.TrophySystem;

public interface ITrophySystemFactory {

        IPerformanceObserver headCoachObserver();

        IPerformanceObserver defenceMenObserver();

        IPerformanceObserver goalieObserver();

        IPerformanceObserver goalScorerObserver();

        IPerformance bestPerformanceSubject();

        IPerformanceObserver playerObserver();

        ITeamObserver seasonObserver();

        IAwardDistributed displayAwards();

        AwardCeremony awardCeremony();
}
