package com.IceHockeyLeague.LeagueManager.GamePlayConfig;

public class AgingConfig implements IAgingConfig {
    private int averageRetirementAge;
    private int maximumAge;

    @Override
    public void setAverageRetirementAge(int age) {
        averageRetirementAge = age;
    }

    @Override
    public int getAverageRetirementAge() {
        return averageRetirementAge;
    }

    @Override
    public void setMaximumAge(int age) {
        maximumAge = age;
    }

    @Override
    public int getMaximumAge() {
        return maximumAge;
    }
}
