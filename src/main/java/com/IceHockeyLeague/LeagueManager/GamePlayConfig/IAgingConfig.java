package com.IceHockeyLeague.LeagueManager.GamePlayConfig;

public interface IAgingConfig {
    void setAverageRetirementAge(int averageRetirementAge);

    int getAverageRetirementAge();

    void setMaximumAge(int maximumAge);

    int getMaximumAge();

    void setStatDecayChance(float statDecayChance);

    float getStatDecayChance();
}
