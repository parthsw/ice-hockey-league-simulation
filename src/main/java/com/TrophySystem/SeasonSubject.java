package com.TrophySystem;

import com.TrophySystem.ITeamObserver;

import java.util.ArrayList;
import java.util.List;

public abstract class SeasonSubject {
    private final List<ITeamObserver> observers;
    public SeasonSubject(){
        observers = new ArrayList<>();
    }
    public void attachObserver(ITeamObserver observer){
        this.observers.add(observer);
    }
    public void detachObserver(ITeamObserver observer){
        this.observers.remove(observer);
    }
    public void notifyObserver(){
        for(ITeamObserver observer : observers){
            observer.update();
        }
    }
}
