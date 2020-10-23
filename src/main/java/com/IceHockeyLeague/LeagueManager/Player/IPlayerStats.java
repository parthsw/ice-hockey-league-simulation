package com.IceHockeyLeague.LeagueManager.Player;

public interface IPlayerStats {
    String getPosition();
    void setPosition(String position);

    int getSkating();
    void setSkating(int skating);

    int getShooting();
    void setShooting(int shooting);

    int getChecking();
    void setChecking(int checking);

    int getSaving();
    void setSaving(int saving);

    void setStrength(int strength);
    int getStrength();
    int calculateStrength();
}
