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

    void setStrength(double strength);
    double getStrength();
    double calculateStrength();
}
