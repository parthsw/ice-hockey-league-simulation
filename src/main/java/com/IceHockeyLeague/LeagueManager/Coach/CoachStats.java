package com.IceHockeyLeague.LeagueManager.Coach;

public class CoachStats implements ICoachStats {
    private static final double STATS_LOWER_VALUE = 0.0;
    private static final double STATS_HIGHER_VALUE = 1.0;

    private double skating;
    private double shooting;
    private double checking;
    private double saving;

    public CoachStats() {
        setDefaults();
    }

    private void setDefaults() {
        skating = 0.0;
        shooting = 0.0;
        checking = 0.0;
        saving = 0.0;
    }

    @Override
    public double getSkating() {
        return skating;
    }

    @Override
    public void setSkating(double value) {
        if(isStatValid(value)) {
            skating = value;
        }
    }

    @Override
    public double getShooting() {
        return shooting;
    }

    @Override
    public void setShooting(double value) {
        if(isStatValid(value)) {
            shooting = value;
        }
    }

    @Override
    public double getChecking() {
        return checking;
    }

    @Override
    public void setChecking(double value) {
        if(isStatValid(value)) {
            checking = value;
        }
    }

    @Override
    public double getSaving() {
        return saving;
    }

    @Override
    public void setSaving(double value) {
        if(isStatValid(value)) {
            saving = value;
        }
    }

    private boolean isStatValid(double statValue) {
        return (statValue >= STATS_LOWER_VALUE && statValue <= STATS_HIGHER_VALUE);
    }
}
