package com.TrophySystem;

public interface IPerformance {
    void attachObserver(IPerformanceObserver observer);

    void detachObserver(IPerformanceObserver observer);

    void notifyObserver();
}

