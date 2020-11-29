package com.TrophySystem;

import com.TrophySystem.IPerformance;
import com.TrophySystem.IPerformanceObserver;

import java.util.ArrayList;
import java.util.List;

public class BestPerformanceSubject implements IPerformance {
    protected int score;
    protected String performerName;
    private final List<IPerformanceObserver> observers;

    public BestPerformanceSubject(){
        this.observers = new ArrayList<>();
    }

    public void attachObserver(IPerformanceObserver observer){
        this.observers.add(observer);
    }

    public void detachObserver(IPerformanceObserver observer){
        this.observers.remove(observer);
    }

    public void notifyObserver(){
        for(IPerformanceObserver observer : observers){
            observer.update(performerName,score);
        }
    }
}


