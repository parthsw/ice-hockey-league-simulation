package com.IceHockeyLeague.LeagueManager.Player;

import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IAgingConfig;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IInjuryConfig;

import java.time.LocalDate;

public class Player implements IPlayer {
    private String name;
    private IPlayerStats playerStats;
    private IPlayerAgeInfo playerAgeInfo;
    private boolean isInjured;
    private int daysInjured;
    private LocalDate injuryDate;
    private boolean isRetired;
    private LocalDate retirementDate;

    public Player() {
        setDefaults();
    }

    private void setDefaults() {
        isInjured = false;
        isRetired = false;
    }

    @Override
    public String getPlayerName() {
        return name;
    }

    @Override
    public void setPlayerName(String name) {
        this.name = name;
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
    public boolean getInjuredStatus() {
        return isInjured;
    }

    @Override
    public void setInjuredStatus(boolean isInjured) {
        this.isInjured = isInjured;
    }

    @Override
    public int getDaysInjured() {
        return daysInjured;
    }

    @Override
    public void setDaysInjured(int days) {
        daysInjured = days;
    }

    @Override
    public LocalDate getInjuryDate() {
        return injuryDate;
    }

    @Override
    public void setInjuryDate(LocalDate injuryDate) {
        this.injuryDate = injuryDate;
    }

    @Override
    public LocalDate getRetirementDate() {
        return retirementDate;
    }

    @Override
    public void setRetirementDate(LocalDate retirementDate) {
        this.retirementDate = retirementDate;
    }

    @Override
    public boolean getRetiredStatus() {
        return isRetired;
    }

    @Override
    public void setRetiredStatus(boolean isRetired) {
        this.isRetired = isRetired;
    }

    @Override
    public IPlayerAgeInfo getPlayerAgeInfo() {
        return playerAgeInfo;
    }

    @Override
    public void setPlayerAgeInfo(IPlayerAgeInfo playerAgeInfo) {
        this.playerAgeInfo = playerAgeInfo;
    }

    @Override
    public void convertBetweenPlayerTypes(IPlayer player) {
        player.setPlayerName(this.getPlayerName());
        player.setInjuredStatus(this.getInjuredStatus());
        player.setDaysInjured(this.getDaysInjured());
        player.setInjuryDate(this.getInjuryDate());
        player.setRetiredStatus(this.getRetiredStatus());
        player.setRetirementDate(this.getRetirementDate());
        player.setPlayerStats(this.getPlayerStats());
        player.setPlayerAgeInfo(this.getPlayerAgeInfo());
    }

    @Override
    public float calculateStrength(IPlayerStats stats) {
        return stats.calculateStrength();
    }

    @Override
    public boolean isInjured(IPlayerCareerProgression playerCareerProgression, IInjuryConfig injuryConfig, LocalDate currentDate) {
        return playerCareerProgression.isInjured(this, injuryConfig, currentDate);
    }

    @Override
    public boolean isRecovered(IPlayerCareerProgression playerCareerProgression, LocalDate currentDate) {
        return playerCareerProgression.isRecovered(this, currentDate);
    }

    @Override
    public boolean isRetired(IPlayerCareerProgression playerCareerProgression, IAgingConfig agingConfig, LocalDate currentDate) {
        return playerCareerProgression.isRetired(this, agingConfig, currentDate);
    }

    @Override
    public void agePlayerByDays(int daysToIncrement, LocalDate currentDate) {
        playerAgeInfo.agePlayerByDays(daysToIncrement, currentDate);
    }
}
