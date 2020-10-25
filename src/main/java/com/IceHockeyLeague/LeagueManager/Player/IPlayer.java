package com.IceHockeyLeague.LeagueManager.Player;

public interface IPlayer {

    String getPlayerName();
    void setPlayerName(String name);

    int getPlayerAge();
    void setPlayerAge(int age);

    boolean getIsInjured();
    void setIsInjured(boolean isInjured);

    boolean getIsRetired();
    void setIsRetired(boolean isRetired);

    IPlayerStats getPlayerStats();
    void setPlayerStats(IPlayerStats playerStats);

    int calculateStrength(IPlayerStats stats);

    String getPlayerPosition();
    void setPlayerPosition(String playerPosition);

    boolean isValid();
}
