package com.IceHockeyLeague.LeagueManager.Player;

public interface IPlayer {
    int getPlayerID();
    void setPlayerID(int id);

    String getPlayerName();
    void setPlayerName(String name);

    boolean getIsCaptain();
    void setIsCaptain(boolean isCaptain);

    String getPosition();
    void setPosition(String name);

    int getTeamID();
    void setTeamID(int id);

    int getAge();
    void setAge(int age);

    int getSkating();
    void setSkating(int value);

    int getShooting();
    void setShooting(int value);

    int getChecking();
    void setChecking(int value);

    int getSaving();
    void setSaving(int value);

    boolean isValid();
}
