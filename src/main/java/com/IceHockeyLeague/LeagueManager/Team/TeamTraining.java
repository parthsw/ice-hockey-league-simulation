package com.IceHockeyLeague.LeagueManager.Team;

import com.AbstractAppFactory;
import com.AppFactory;
import com.IO.IAppOutput;
import com.IceHockeyLeague.LeagueManager.Coach.ICoachStats;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerCareerProgression;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerStats;
import com.IceHockeyLeague.LeagueManager.Player.IRandomChance;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;

import java.util.List;
import java.util.Random;

public class TeamTraining implements ITeamTraining {
    private final IAppOutput appOutput = AppFactory.getIOFactory().createCommandLineOutput();

    @Override
    public void trainTeam(ILeague league, List<ITeamPlayer> teamPlayers, ICoachStats coachStats) {
        ILeagueManagerFactory leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        IRandomChance randomChance = leagueManagerFactory.createRandomChance();

        appOutput.display("Training initiated for the team");
        for (ITeamPlayer teamPlayer : teamPlayers) {
            appOutput.display("Training initiated for the player " + teamPlayer.getPlayerName());
            double randomChecking = generateRandomNumber();
            double randomSkating = generateRandomNumber();
            double randomShooting = generateRandomNumber();
            double randomSaving = generateRandomNumber();

            if (randomChecking < coachStats.getChecking()) {
                IPlayerStats stat = teamPlayer.getPlayerStats();
                stat.setChecking(stat.getChecking() + 1);
                appOutput.display("Stats for checking has been increased");
            } else {
                IPlayerCareerProgression playerCareerProgression = leagueManagerFactory.createPlayerCareerProgression(randomChance);
                teamPlayer.isInjured(playerCareerProgression, league.getGamePlayConfig().getInjuryConfig(), league.getLeagueDate());
            }

            if (randomSkating < coachStats.getSkating()) {
                IPlayerStats stat = teamPlayer.getPlayerStats();
                stat.setSkating(stat.getSkating() + 1);
                appOutput.display("Stats for skating has been increased");
            } else {
                IPlayerCareerProgression playerCareerProgression = leagueManagerFactory.createPlayerCareerProgression(randomChance);
                teamPlayer.isInjured(playerCareerProgression, league.getGamePlayConfig().getInjuryConfig(), league.getLeagueDate());
            }

            if (randomShooting < coachStats.getShooting()) {
                IPlayerStats stat = teamPlayer.getPlayerStats();
                stat.setShooting(stat.getShooting() + 1);
                appOutput.display("Stats for shooting has been increased");
            } else {
                IPlayerCareerProgression playerCareerProgression = leagueManagerFactory.createPlayerCareerProgression(randomChance);
                teamPlayer.isInjured(playerCareerProgression, league.getGamePlayConfig().getInjuryConfig(), league.getLeagueDate());
            }

            if (randomSaving < coachStats.getSaving()) {
                IPlayerStats stat = teamPlayer.getPlayerStats();
                stat.setSaving(stat.getSaving() + 1);
                appOutput.display("Stats for saving has been increased");
            } else {
                IPlayerCareerProgression playerCareerProgression = leagueManagerFactory.createPlayerCareerProgression(randomChance);
                teamPlayer.isInjured(playerCareerProgression, league.getGamePlayConfig().getInjuryConfig(), league.getLeagueDate());
            }
        }
    }

    public double generateRandomNumber() {
        double randomNumber;
        Random rand = new Random();
        randomNumber = rand.nextDouble();
        return randomNumber;
    }
}
