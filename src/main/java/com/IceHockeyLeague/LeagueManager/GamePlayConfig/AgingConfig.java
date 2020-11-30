package com.IceHockeyLeague.LeagueManager.GamePlayConfig;

public class AgingConfig implements IAgingConfig {
    private int averageRetirementAge;
    private int maximumAge;
    private float statDecayChance;

    @Override
    public void setAverageRetirementAge(int averageRetirementAge) {
        this.averageRetirementAge = averageRetirementAge;
    }

    @Override
    public int getAverageRetirementAge() {
        return averageRetirementAge;
    }

    @Override
    public void setMaximumAge(int maximumAge) {
        this.maximumAge = maximumAge;
    }

    @Override
    public int getMaximumAge() {
        return maximumAge;
    }

    @Override
    public void setStatDecayChance(float statDecayChance) {
        this.statDecayChance = statDecayChance;
    }

    @Override
    public float getStatDecayChance() {
        return statDecayChance;
    }

}
