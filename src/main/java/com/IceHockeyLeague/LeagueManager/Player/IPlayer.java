package com.IceHockeyLeague.LeagueManager.Player;

import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IInjuryConfig;

import java.time.LocalDate;

public interface IPlayer {

    String getPlayerName();
    void setPlayerName(String name);

    int getPlayerAge();
    void setPlayerAge(int age);

    int getElapsedDaysFromLastBDay();

    boolean getInjuredStatus();
    void setInjuredStatus(boolean isInjured);

    int getDaysInjured();
    void setDaysInjured(int days);

    LocalDate getInjuryDate();
    void setInjuryDate(LocalDate injuryDate);

    LocalDate getRetirementDate();
    void setRetirementDate(LocalDate retirementDate);

    boolean getRetiredStatus();
    void setRetiredStatus(boolean isRetired);

    IPlayerStats getPlayerStats();
    void setPlayerStats(IPlayerStats playerStats);

    float calculateStrength(IPlayerStats stats);

    boolean isInjured(IPlayerInjuryManager playerInjuryManager, IInjuryConfig injuryConfig, LocalDate currentDate);
    boolean isRecovered(IPlayerInjuryManager playerInjuryManager, LocalDate currentDate);

    void agePlayerByDays(int days);

    boolean isValid();
}
