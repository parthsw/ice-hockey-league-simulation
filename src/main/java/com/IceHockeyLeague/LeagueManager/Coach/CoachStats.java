package com.IceHockeyLeague.LeagueManager.Coach;

public class CoachStats implements ICoachStats {
    private static final float STATS_LOWER_VALUE = 0.0f;
    private static final float STATS_HIGHER_VALUE = 1.0f;
    private static final float DEFAULT_STAT = 0.0f;

    private float skating;
    private float shooting;
    private float checking;
    private float saving;

    public CoachStats() {
        setDefaults();
    }

    private void setDefaults() {
        skating = DEFAULT_STAT;
        shooting = DEFAULT_STAT;
        checking = DEFAULT_STAT;
        saving = DEFAULT_STAT;
    }

    @Override
    public float getSkating() {
        return skating;
    }

    @Override
    public void setSkating(float statValue) {
        if (isStatValid(statValue)) {
            skating = statValue;
        }
    }

    @Override
    public float getShooting() {
        return shooting;
    }

    @Override
    public void setShooting(float statValue) {
        if (isStatValid(statValue)) {
            shooting = statValue;
        }
    }

    @Override
    public float getChecking() {
        return checking;
    }

    @Override
    public void setChecking(float statValue) {
        if (isStatValid(statValue)) {
            checking = statValue;
        }
    }

    @Override
    public float getSaving() {
        return saving;
    }

    @Override
    public void setSaving(float statValue) {
        if (isStatValid(statValue)) {
            saving = statValue;
        }
    }

    private boolean isStatValid(float statValue) {
        return (statValue >= STATS_LOWER_VALUE && statValue <= STATS_HIGHER_VALUE);
    }

}
