package com.IceHockeyLeague.LeagueManager.Player;

import java.time.LocalDate;

public interface IPlayerAgeInfo {
    int getAgeInYears();
    void setAgeInYears(int ageInYears);

    int getElapsedDaysFromLastBDay();
    void setElapsedDaysFromLastBDay(int elapsedDaysFromLastBDay);

    LocalDate getBirthDate();
    void setBirthDate(LocalDate birthDate);

    int calculatePlayerAgeInYears(LocalDate currentDate);
    int calculateElapsedDaysFromLastBDay(LocalDate currentDate);
    void agePlayerByDays(int daysToIncrement, LocalDate currentDate);
}
