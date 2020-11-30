package com.IceHockeyLeague.LeagueManager.Player;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IGamePlayConfig;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IAgingConfig;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IInjuryConfig;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class PlayerCareerProgression implements IPlayerCareerProgression {
    private final IRandomChance randomChanceGenerator;

    public PlayerCareerProgression(IRandomChance randomChance) {
        randomChanceGenerator = randomChance;
    }

    @Override
    public boolean isInjured(IPlayer player, IInjuryConfig injuryConfig, LocalDate currentDate) {
        float randomInjuryChance = injuryConfig.getRandomInjuryChance();
        int minInjuryDays = injuryConfig.getInjuryDaysLow();
        int maxInjuryDays = injuryConfig.getInjuryDaysHigh();

        if (player.getInjuredStatus()) {
            return true;
        } else {
            int injuryLikelihoodLower = 0, injuryLikelihoodHigher = 1, injuryLikelihoodDecimal = 2;
            float injuryLikelihood = randomChanceGenerator.getRandomFloatNumber(injuryLikelihoodLower, injuryLikelihoodHigher);
            float roundedInjuryLikelihood = randomChanceGenerator.roundFloatNumber(injuryLikelihood, injuryLikelihoodDecimal);
            if (roundedInjuryLikelihood < randomInjuryChance) {
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
        if (player.getInjuredStatus()) {
            long noOfDaysElapsed = ChronoUnit.DAYS.between(player.getInjuryDate(), currentDate);
            if (noOfDaysElapsed == player.getDaysInjured()) {
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
        IPlayerAgeInfo playerAgeInfo = player.getPlayerAgeInfo();
        int DAYS_IN_YEAR = 365;
        long currentAgeInDays = ChronoUnit.DAYS.between(playerAgeInfo.getBirthDate(), currentDate);
        int maximumAgeInDays = (agingConfig.getMaximumAge() * DAYS_IN_YEAR);
        int averageRetirementAgeInDays = (agingConfig.getAverageRetirementAge() * DAYS_IN_YEAR);
        float randomRetirementChance = randomChanceGenerator.getRandomFloatNumber(0, agingConfig.getMaximumAge());
        float retirementFactor;

        if (currentAgeInDays >= maximumAgeInDays) {
            setPlayerRetirementFields(player, currentDate);
            return true;
        }

        if (currentAgeInDays < averageRetirementAgeInDays) {
            retirementFactor = 1 / ((float) (averageRetirementAgeInDays - currentAgeInDays) / (float) DAYS_IN_YEAR) * ((float) agingConfig.getAverageRetirementAge() / 10);
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
        if (isRemoved) {
            league.addRetiredFreeAgent(freeAgent);
            return true;
        }
        return false;
    }

    @Override
    public boolean handleTeamPlayerRetirement(ITeamPlayer teamPlayer, ITeam team, ILeague league) {
        ILeagueManagerFactory leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        IFreeAgent freeAgent = leagueManagerFactory.createFreeAgent();
        boolean isRemoved = team.removePlayer(teamPlayer);
        IFreeAgent bestFreeAgent;

        if (isRemoved) {
            league.addRetiredTeamPlayer(teamPlayer);
        } else {
            return false;
        }

        bestFreeAgent = freeAgent.bestFreeAgentForPosition(league.getFreeAgents(), teamPlayer.getPlayerStats().getPosition());
        if (bestFreeAgent == null) {
            return false;
        } else {
            boolean isFreeAgentRemoved = league.removeFreeAgent(bestFreeAgent);
            if (isFreeAgentRemoved) {
                ITeamPlayer newTeamPlayer = leagueManagerFactory.createTeamPlayer();
                bestFreeAgent.convertToTeamPlayer(newTeamPlayer);
                newTeamPlayer.setTeamId(team.getTeamID());
                team.addPlayer(newTeamPlayer);
            }
        }
        return team.checkTeamPlayers();
    }

    @Override
    public void performLeaguePlayersRetirement(ILeague league) {
        IGamePlayConfig gamePlayConfig = league.getGamePlayConfig();

        for (IConference conference : league.getConferences()) {
            for (IDivision division : conference.getDivisions()) {
                for (ITeam team : division.getTeams()) {
                    List<ITeamPlayer> teamPlayers = team.getPlayers();
                    // using simple for loop instead of enhanced as I need to modify ArrayList during the iteration
                    for (int l = 0; l < teamPlayers.size(); l++) {
                        ITeamPlayer currentTeamPlayer = teamPlayers.get(l);
                        boolean isRetired = currentTeamPlayer.isRetired(this, gamePlayConfig.getAgingConfig(), league.getLeagueDate());
                        if (isRetired) {
                            this.handleTeamPlayerRetirement(currentTeamPlayer, team, league);
                            l--;
                        }
                    }
                }
            }
        }

        List<IFreeAgent> freeAgents = league.getFreeAgents();
        for (int i = 0; i < freeAgents.size(); i++) {
            IFreeAgent currentFreeAgent = freeAgents.get(i);
            boolean isRetired = currentFreeAgent.isRetired(this, gamePlayConfig.getAgingConfig(), league.getLeagueDate());
            if (isRetired) {
                this.handleFreeAgentRetirement(currentFreeAgent, league);
                i--;
            }
        }
    }

    @Override
    public void adjustLeaguePlayersAge(ILeague league, LocalDate newDate) {
        LocalDate currentLeagueDate = league.getLeagueDate();
        int numberOfDaysElapsed = (int) DAYS.between(currentLeagueDate, newDate);

        for (IConference conference : league.getConferences()) {
            for (IDivision division : conference.getDivisions()) {
                for (ITeam team : division.getTeams()) {
                    for (ITeamPlayer teamPlayer : team.getPlayers()) {
                        teamPlayer.agePlayerByDays(numberOfDaysElapsed, currentLeagueDate);
                    }
                }
            }
        }

        for (IFreeAgent freeAgent : league.getFreeAgents()) {
            freeAgent.agePlayerByDays(numberOfDaysElapsed, currentLeagueDate);
        }

        for (ITeamPlayer retiredTeamPlayer : league.getRetiredTeamPlayers()) {
            retiredTeamPlayer.agePlayerByDays(numberOfDaysElapsed, currentLeagueDate);
        }

        for (IFreeAgent retiredFreeAgent : league.getRetiredFreeAgents()) {
            retiredFreeAgent.agePlayerByDays(numberOfDaysElapsed, currentLeagueDate);
        }
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
