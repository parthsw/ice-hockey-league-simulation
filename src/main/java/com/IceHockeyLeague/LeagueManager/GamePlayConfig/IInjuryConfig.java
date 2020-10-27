package com.IceHockeyLeague.LeagueManager.GamePlayConfig;

public interface IInjuryConfig {
    void setRandomInjuryChance(double injuryChance);
    double getRandomInjuryChance();

    void setInjuryDaysLow(int minInjuryDays);
    int getInjuryDaysLow();

    void setInjuryDaysHigh(int maxInjuryDays);
    int getInjuryDaysHigh();
}
