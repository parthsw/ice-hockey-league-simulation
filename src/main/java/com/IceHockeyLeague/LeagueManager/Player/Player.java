package com.IceHockeyLeague.LeagueManager.Player;

public class Player implements IPlayer {
    private String name;
    private int age;
    private boolean isInjured;
    private boolean isRetired;
    private IPlayerStats playerStats;

    @Override
    public String getPlayerName() {
        return name;
    }

    @Override
    public void setPlayerName(String name) {
        this.name = name;
    }

    @Override
    public int getPlayerAge() {
        return age;
    }

    @Override
    public void setPlayerAge(int age) {
        this.age = age;
    }

    @Override
    public boolean getIsInjured() {
        return isInjured;
    }

    @Override
    public void setIsInjured(boolean isInjured) {
        this.isInjured = isInjured;
    }

    @Override
    public boolean getIsRetired() {
        return isRetired;
    }

    @Override
    public void setIsRetired(boolean isRetired) {
        this.isRetired = isRetired;
    }

    @Override
    public IPlayerStats getPlayerStats() {
        return playerStats;
    }

    @Override
    public void setPlayerStats(IPlayerStats playerStats) {
        this.playerStats = playerStats;
    }

    @Override
    public int calculateStrength(IPlayerStats stats) {
        return stats.calculateStrength();
    }

    @Override
    public String getPlayerPosition() {
        return null;
    }

    public void setPlayerPosition(String playerPosition){ }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public int getStrength() {
        return 0;
    }

}
