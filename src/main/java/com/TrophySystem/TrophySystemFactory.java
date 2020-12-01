package com.TrophySystem;

public class TrophySystemFactory implements ITrophySystemFactory{

    private TrophyDistribution trophyDistribution =null;

    @Override
    public IPerformanceObserver headCoachObserver() {
        return new HeadCoachObserver();
    }

    @Override
    public IPerformanceObserver defenceMenObserver() {
        return new DefenceMenObserver();
    }

    @Override
    public IPerformanceObserver goalieObserver() {
        return new GoalieObserver();
    }

    @Override
    public IPerformanceObserver goalScorerObserver() {
        return new GoalScorerObserver();
    }

    @Override
    public IPerformance bestPerformanceSubject() {
        return new BestPerformanceSubject();
    }

    @Override
    public IPerformanceObserver playerObserver() {
        return new PlayerObserver();
    }

    @Override
    public ITeamObserver seasonObserver() {
        return new SeasonObserver();
    }

    @Override
    public IAwardDistributed displayAwards() {
        return new DisplayAwards();
    }

    @Override
    public TrophyDistribution trophyDistribution() {
        if (trophyDistribution == null){
            trophyDistribution = new TrophyDistribution();
            return trophyDistribution;
        }
        return trophyDistribution;
    }

    @Override
    public PerformanceParameter performanceParameter() {
        return new PerformanceParameter();
    }
}
