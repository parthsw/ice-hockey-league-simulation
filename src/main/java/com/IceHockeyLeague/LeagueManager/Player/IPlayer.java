package com.IceHockeyLeague.LeagueManager.Player;

import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IAgingConfig;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IInjuryConfig;

import java.time.LocalDate;

public interface IPlayer {
    String getPlayerName();

    void setPlayerName(String name);

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

    void performStatDecay(IAgingConfig agingConfig, IRandomChance randomChance);

    IPlayerAgeInfo getPlayerAgeInfo();

    void setPlayerAgeInfo(IPlayerAgeInfo playerAgeInfo);

    boolean isBirthDate(LocalDate currentDate);

    void convertBetweenPlayerTypes(IPlayer player);

    void agePlayerByDays(int daysToIncrement, LocalDate currentDate);

    boolean isInjured(IPlayerCareerProgression playerCareerProgression, IInjuryConfig injuryConfig, LocalDate currentDate);

    boolean isRecovered(IPlayerCareerProgression playerCareerProgression, LocalDate currentDate);

    boolean isRetired(IPlayerCareerProgression playerCareerProgression, IAgingConfig agingConfig, LocalDate currentDate);
}
