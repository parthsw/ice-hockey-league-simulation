package com.TrophySystem;

public class PerformanceParameter implements IPerformanceParameter {

    String performerName;
    int performerPoints;

    @Override
    public void setPerformerName(String performerName) {
        this.performerName = performerName;
    }

    @Override
    public String getPerformerName() {
        return performerName;
    }

    @Override
    public void setPerformerPoints(int performerPoints) {
        this.performerPoints = performerPoints;
    }

    @Override
    public int getPerformerPoints() {
        return performerPoints;
    }
}
