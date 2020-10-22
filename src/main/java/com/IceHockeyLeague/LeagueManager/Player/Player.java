package com.IceHockeyLeague.LeagueManager.Player;

public class Player implements IPlayer {
    private String name;
    private int age;
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
    public IPlayerStats getPlayerStats() {
        return playerStats;
    }

    @Override
    public void setPlayerStats(IPlayerStats playerStats) {
        this.playerStats = playerStats;
    }

    @Override
    public boolean isValid() {
        return false;
    }

}
