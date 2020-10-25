package com.IceHockeyLeague.LeagueManager.GamePlayConfig;

public class InjuryConfig implements IInjuryConfig {
    private double randomInjuryChance;
    private int injuryDaysLow;
    private int injuryDaysHigh;

    @Override
    public void setRandomInjuryChance(double injuryChance) {
        randomInjuryChance = injuryChance;
    }

    @Override
    public double getRandomInjuryChance() {
        return randomInjuryChance;
    }

    @Override
    public void setInjuryDaysLow(int minInjuryDays) {
        injuryDaysLow = minInjuryDays;
    }

    @Override
    public int getInjuryDaysLow() {
        return injuryDaysLow;
    }

    @Override
    public void setInjuryDaysHigh(int maxInjuryDays) {
        injuryDaysHigh = maxInjuryDays;
    }

    @Override
    public int getInjuryDaysHigh() {
        return injuryDaysHigh;
    }
}
