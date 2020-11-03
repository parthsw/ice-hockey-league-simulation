package com.IceHockeyLeague.LeagueManager.GamePlayConfig;

public class TrainingConfig implements ITrainingConfig {
    private int daysUntilStatIncreaseCheck;

    @Override
    public void setDaysUntilStatIncreaseCheck(int days) {
        daysUntilStatIncreaseCheck = days;
    }

    @Override
    public int getDaysUntilStatIncreaseCheck() {
        return daysUntilStatIncreaseCheck;
    }
}
