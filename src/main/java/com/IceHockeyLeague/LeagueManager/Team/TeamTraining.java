package com.IceHockeyLeague.LeagueManager.Team;

import com.IO.CommandLineOutput;
import com.IO.IAppOutput;
import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Coach.ICoachStats;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerCareerProgression;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerStats;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Player.PlayerStats;

import java.util.List;
import java.util.Random;

public class TeamTraining implements ITeamTraining{
    private IAppOutput appOutput = new CommandLineOutput();
    @Override
    public void trainTeam(ILeague league, List<ITeamPlayer> teamPlayers, ICoachStats coachStats) {
        appOutput.display("Training initiated for the team");
        for (ITeamPlayer teamPlayer: teamPlayers) {
            appOutput.display("Training initiated for the player " + teamPlayer.getPlayerName());
            double randomChecking = generateRandomNumber();
            double randomSkating = generateRandomNumber();
            double randomShooting = generateRandomNumber();
            double randomSaving = generateRandomNumber();

            if(randomChecking < coachStats.getChecking()){
                IPlayerStats stat = teamPlayer.getPlayerStats();
                stat.setChecking(stat.getChecking()+1);
                appOutput.display("Stats for checking has been increased");
            }
            else{
                IPlayerCareerProgression playerCareerProgression = AbstractLeagueManagerFactory.getFactory().getPlayerCareerProgression();
                teamPlayer.isInjured(playerCareerProgression, league.getGamePlayConfig().getInjuryConfig(), league.getLeagueDate());
            }

            if(randomSkating < coachStats.getSkating()){
                IPlayerStats stat = teamPlayer.getPlayerStats();
                stat.setSkating(stat.getSkating()+1);
                appOutput.display("Stats for skating has been increased");
            }
            else{
                IPlayerCareerProgression playerCareerProgression = AbstractLeagueManagerFactory.getFactory().getPlayerCareerProgression();
                teamPlayer.isInjured(playerCareerProgression, league.getGamePlayConfig().getInjuryConfig(), league.getLeagueDate());
            }

            if(randomShooting < coachStats.getShooting()){
                IPlayerStats stat = teamPlayer.getPlayerStats();
                stat.setShooting(stat.getShooting()+1);
                appOutput.display("Stats for shooting has been increased");
            }
            else{
                IPlayerCareerProgression playerCareerProgression = AbstractLeagueManagerFactory.getFactory().getPlayerCareerProgression();
                teamPlayer.isInjured(playerCareerProgression, league.getGamePlayConfig().getInjuryConfig(), league.getLeagueDate());
            }

            if(randomSaving < coachStats.getSaving()){
                IPlayerStats stat = teamPlayer.getPlayerStats();
                stat.setSaving(stat.getSaving()+1);
                appOutput.display("Stats for saving has been increased");
            }
            else{
                IPlayerCareerProgression playerCareerProgression = AbstractLeagueManagerFactory.getFactory().getPlayerCareerProgression();
                teamPlayer.isInjured(playerCareerProgression, league.getGamePlayConfig().getInjuryConfig(), league.getLeagueDate());
            }
        }
    }
    public double generateRandomNumber(){
        double randomNumber = 0.0;
        Random rand = new Random();
        randomNumber = rand.nextDouble();
        return randomNumber;
    }
}
