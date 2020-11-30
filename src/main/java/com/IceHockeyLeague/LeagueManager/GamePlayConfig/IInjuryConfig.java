package com.IceHockeyLeague.LeagueManager.GamePlayConfig;

public interface IInjuryConfig {
    void setRandomInjuryChance(float randomInjuryChance);

    float getRandomInjuryChance();

    void setInjuryDaysLow(int minInjuryDays);

    int getInjuryDaysLow();

    void setInjuryDaysHigh(int maxInjuryDays);

    int getInjuryDaysHigh();
}
