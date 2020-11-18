package com.IceHockeyLeague.LeagueManager.Player;

import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IAgingConfig;

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

    void setStrength(float strength);
    float getStrength();
    float calculateStrength();

    void performStatDecay(IAgingConfig agingConfig, IRandomChance randomChance);
}
