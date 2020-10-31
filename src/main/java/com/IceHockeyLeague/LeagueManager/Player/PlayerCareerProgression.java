package com.IceHockeyLeague.LeagueManager.Player;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IAgingConfig;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IInjuryConfig;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PlayerCareerProgression implements IPlayerCareerProgression {
    private final int INJURY_LIKELIHOOD_LOWER = 0;
    private final int INJURY_LIKELIHOOD_HIGHER = 1;
    private final int INJURY_LIKELIHOOD_DECIMAL = 2;
    private final int DAYS_IN_YEAR = 365;

    private final IRandomChance randomChanceGenerator;

    public PlayerCareerProgression(IRandomChance randomChance) {
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

    @Override
    public boolean isRetired(IPlayer player, IAgingConfig agingConfig, LocalDate currentDate) {

        int currentAgeInDays = (player.getPlayerAge() * DAYS_IN_YEAR) + player.getElapsedDaysFromLastBDay();
        int maximumAgeInDays = (agingConfig.getMaximumAge() * DAYS_IN_YEAR);

        if(currentAgeInDays >= maximumAgeInDays) {
            setPlayerRetirementFields(player, currentDate);
            return true;
        }

        int averageRetirementAgeInDays = (agingConfig.getAverageRetirementAge() * DAYS_IN_YEAR);
        float randomRetirementChance = randomChanceGenerator.getRandomFloatNumber(0, agingConfig.getMaximumAge());

        float retirementFactor;
        if(currentAgeInDays < averageRetirementAgeInDays) {
            retirementFactor = 1 / ((float) (averageRetirementAgeInDays - currentAgeInDays) / (float) DAYS_IN_YEAR) * ((float)agingConfig.getAverageRetirementAge() / 10);
        } else {
            retirementFactor = 1 / ((float) (currentAgeInDays - averageRetirementAgeInDays) / (float) DAYS_IN_YEAR) * agingConfig.getAverageRetirementAge();
        }
        if (randomRetirementChance < retirementFactor) {
            setPlayerRetirementFields(player, currentDate);
            return true;
        }
        return false;
    }

    @Override
    public boolean handleFreeAgentRetirement(IFreeAgent freeAgent, ILeague league) {
        boolean isRemoved = league.removeFreeAgent(freeAgent);
        if(isRemoved) {
            league.addRetiredFreeAgent(freeAgent);
            return true;
        }
        return false;
    }

    @Override
    public boolean handleTeamPlayerRetirement(ITeamPlayer teamPlayer, ITeam team, ILeague league) {
        // 1. remove provided teamPlayer from the respected team inside of given league
        boolean isRemoved = team.removePlayer(teamPlayer);
        if(isRemoved) {
            // 2. Push the removed teamPlayer to retiredPlayers list available at league level
            league.addRetiredTeamPlayer(teamPlayer);
        } else {
            // Error while removing teamPlayer from associated team
            return false;
        }

        // 3. Find the best player from freeAgent's list. If found return it else return false; Break the flow;
        IFreeAgent bestFreeAgent = new FreeAgent(); // Best FreeAgent for given position
        boolean isFreeAgentFound = true;
        if(isFreeAgentFound) {
            // 4. Remove the found freeAgent from league's freeAgent list
            boolean isFreeAgentRemoved = league.removeFreeAgent(bestFreeAgent);
            if(isFreeAgentRemoved) {
                // 5. Convert the found freeAgent to TeamPlayer
                ITeamPlayer newTeamPlayer = AbstractLeagueManagerFactory.getFactory().getTeamPlayer();
                bestFreeAgent.convertToTeamPlayer(newTeamPlayer);
                newTeamPlayer.setTeamID(team.getTeamID());

                // 6. Push the converted teamPlayer to appropriate team
                team.addPlayer(newTeamPlayer);
            } else {
                // Error removing freeAgent from league's freeAgent list
                return false;
            }

        } else {
            // FreeAgent list does not have appropriate player
            return false;
        }

        // 7. Check the team combination is okay [Including player count and captain count]
        // If no captain, assign one by finding best player
        // If less more or less players, break the flow;
        return team.checkTeamPlayers();
    }

    private void resetInjuryStatus(IPlayer player) {
        player.setInjuredStatus(false);
        player.setDaysInjured(0);
        player.setInjuryDate(null);
    }

    private void setPlayerRetirementFields(IPlayer player, LocalDate currentDate) {
        player.setRetiredStatus(true);
        player.setRetirementDate(currentDate);
    }

}
