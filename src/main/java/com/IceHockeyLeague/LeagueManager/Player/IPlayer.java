package com.IceHockeyLeague.LeagueManager.Player;

public interface IPlayer {

    String getPlayerName();
    void setPlayerName(String name);

    int getPlayerAge();
    void setPlayerAge(int age);

    IPlayerStats getPlayerStats();
    void setPlayerStats(IPlayerStats playerStats);

    boolean isValid();
}
