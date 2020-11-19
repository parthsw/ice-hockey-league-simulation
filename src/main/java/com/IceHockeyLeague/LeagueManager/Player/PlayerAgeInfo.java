package com.IceHockeyLeague.LeagueManager.Player;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class PlayerAgeInfo implements IPlayerAgeInfo {
    private int ageInYears;
    private int elapsedDaysFromLastBDay;
    private LocalDate birthDate;

    @Override
    public int getAgeInYears() {
        return ageInYears;
    }

    @Override
    public void setAgeInYears(int ageInYears) {
        if(ageInYears > 0) {
            this.ageInYears = ageInYears;
        }
    }

    @Override
    public int getElapsedDaysFromLastBDay() {
        return elapsedDaysFromLastBDay;
    }

    @Override
    public void setElapsedDaysFromLastBDay(int elapsedDaysFromLastBDay) {
        if(elapsedDaysFromLastBDay > 0) {
            this.elapsedDaysFromLastBDay = elapsedDaysFromLastBDay;
        }
    }

    @Override
    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean isPlayerBirthDay(LocalDate currentDate) {
        return ((currentDate.getDayOfMonth() == birthDate.getDayOfMonth()) && (currentDate.getMonth() == birthDate.getMonth()));
    }

    @Override
    public LocalDate getBirthDateForGivenYear(int year) {
        LocalDate currentYearDate = LocalDate.of(year, Month.JANUARY, 1);
        if(currentYearDate.isLeapYear()) {
            return LocalDate.of(year, birthDate.getMonth(), birthDate.getDayOfMonth());
        }
        if(birthDate.getMonth() == Month.FEBRUARY && birthDate.getDayOfMonth() == 29) {
            return LocalDate.of(year, birthDate.getMonth(), birthDate.getDayOfMonth() - 1);
        }
        return LocalDate.of(year, birthDate.getMonth(), birthDate.getDayOfMonth());
    }

    @Override
    public int calculatePlayerAgeInYears(LocalDate currentDate) {
        Period timePeriod = Period.between(birthDate, currentDate);
        return timePeriod.getYears();
    }

    @Override
    public int calculateElapsedDaysFromLastBDay(LocalDate currentDate) {
        LocalDate currentYearBirthDate = getBirthDateForGivenYear(currentDate.getYear());
        LocalDate lastYearBirthDate = getBirthDateForGivenYear(currentDate.getYear() - 1);
        int elapsedDays;

        if(currentDate.isEqual(currentYearBirthDate)) {
            return 0;
        }

        if(currentYearBirthDate.isBefore(currentDate)) {
            elapsedDays = (int) ChronoUnit.DAYS.between(currentYearBirthDate, currentDate);
        }
        else {
            elapsedDays = (int) ChronoUnit.DAYS.between(lastYearBirthDate, currentDate);
        }
        // Adding 1 as the end date is exclusive in ChronoUnit.DAYS.between
        return elapsedDays + 1;
    }

    @Override
    public void agePlayerByDays(int daysToIncrement, LocalDate currentDate) {
        if(daysToIncrement > 0) {
            elapsedDaysFromLastBDay += daysToIncrement;
            handlePlayerAgingInYears(currentDate);
        }
    }

    private void handlePlayerAgingInYears(LocalDate currentDate) {
        LocalDate currentYearBirthDate = getBirthDateForGivenYear(currentDate.getYear());
        LocalDate lastYearBirthDate = getBirthDateForGivenYear(currentDate.getYear() - 1);
        int daysBetweenBirthDates = (int) ChronoUnit.DAYS.between(lastYearBirthDate, currentYearBirthDate);
        int remainderDays;

        if(elapsedDaysFromLastBDay >= daysBetweenBirthDates) {
            remainderDays = elapsedDaysFromLastBDay % daysBetweenBirthDates;

            if(remainderDays == 0) {
                ageInYears += 1;
                elapsedDaysFromLastBDay = 0;
            }
            else {
                ageInYears += (elapsedDaysFromLastBDay / daysBetweenBirthDates);
                elapsedDaysFromLastBDay = remainderDays;
            }
        }
    }
}
