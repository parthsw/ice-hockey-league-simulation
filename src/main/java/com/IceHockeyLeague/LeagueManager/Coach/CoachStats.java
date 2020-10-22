package com.IceHockeyLeague.LeagueManager.Coach;

public class CoachStats implements ICoachStats {
    private float skating;
    private float shooting;
    private float checking;
    private float saving;

    @Override
    public float getSkating() {
        return skating;
    }

    @Override
    public void setSkating(float value) {
        skating = value;
    }

    @Override
    public float getShooting() {
        return shooting;
    }

    @Override
    public void setShooting(float value) {
        shooting = value;
    }

    @Override
    public float getChecking() {
        return checking;
    }

    @Override
    public void setChecking(float value) {
        checking = value;
    }

    @Override
    public float getSaving() {
        return saving;
    }

    @Override
    public void setSaving(float value) {
        saving = value;
    }
}
