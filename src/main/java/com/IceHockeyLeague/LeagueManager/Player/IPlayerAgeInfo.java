package com.IceHockeyLeague.LeagueManager.Player;

import java.time.LocalDate;

public interface IPlayerAgeInfo {
    int getAgeInYears();

    void setAgeInYears(int ageInYears);

    int getElapsedDaysFromLastBDate();

    void setElapsedDaysFromLastBDate(int elapsedDaysFromLastBDate);

    LocalDate getBirthDate();

    void setBirthDate(LocalDate birthDate);

    boolean isPlayerBirthDate(LocalDate currentDate);

    LocalDate getBirthDateForGivenYear(int year);

    int calculatePlayerAgeInYears(LocalDate currentDate);

    int calculateElapsedDaysFromLastBDate(LocalDate currentDate);

    void agePlayerByDays(int daysToIncrement, LocalDate currentDate);
}
