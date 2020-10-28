package com.IceHockeyLeague.LeagueManager.Player;

import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IInjuryConfig;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PlayerInjuryManager implements IPlayerInjuryManager {
    private final int INJURY_LIKELIHOOD_LOWER = 0;
    private final int INJURY_LIKELIHOOD_HIGHER = 1;
    private final int INJURY_LIKELIHOOD_DECIMAL = 2;

    private final IRandomChance randomChanceGenerator;

    public PlayerInjuryManager(IRandomChance randomChance) {
        randomChanceGenerator = randomChance;
    }

    @Override
    public boolean isInjured(IPlayer player, IInjuryConfig injuryConfig, LocalDate currentDate) {
        float randomInjuryChance = injuryConfig.getRandomInjuryChance();
        int minInjuryDays = injuryConfig.getInjuryDaysLow();
        int maxInjuryDays = injuryConfig.getInjuryDaysHigh();

        if(player.getInjuredStatus()) {
            return true;
        } else {
            float injuryLikelihood = randomChanceGenerator.getRandomFloatNumber(INJURY_LIKELIHOOD_LOWER, INJURY_LIKELIHOOD_HIGHER);
            float roundedInjuryLikelihood = randomChanceGenerator.roundFloatNumber(injuryLikelihood, INJURY_LIKELIHOOD_DECIMAL);
            if(roundedInjuryLikelihood < randomInjuryChance) {
                int daysInjured = randomChanceGenerator.getRandomIntegerNumber(minInjuryDays, maxInjuryDays);
                player.setInjuredStatus(true);
                player.setInjuryDate(currentDate);
                player.setDaysInjured(daysInjured);
                return true;
            }
            return false;
        }
    }

    @Override
    public boolean isRecovered(IPlayer player, LocalDate currentDate) {
        if(player.getInjuredStatus()) {
            long noOfDaysElapsed = ChronoUnit.DAYS.between(player.getInjuryDate(), currentDate);
            if(noOfDaysElapsed == player.getDaysInjured()) {
                resetInjuryStatus(player);
                return true;
            }
            return false;
        } else {
            resetInjuryStatus(player);
            return true;
        }
    }

    private void resetInjuryStatus(IPlayer player) {
        player.setInjuredStatus(false);
        player.setDaysInjured(0);
        player.setInjuryDate(null);
    }
}
