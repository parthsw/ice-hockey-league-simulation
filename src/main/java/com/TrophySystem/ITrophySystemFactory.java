package com.TrophySystem;

public interface ITrophySystemFactory {

    IPerformanceObserver defenceMenObserver();

    IPerformanceObserver goalieObserver();

    IPerformanceObserver goalScorerObserver();

    IPerformance bestPerformanceSubject();

    IPerformanceObserver playerObserver();

    ITeamObserver seasonObserver();

    IAwardDistributed displayAwards();

    TrophyDistribution trophyDistribution();

    PerformanceParameter performanceParameter();
}
