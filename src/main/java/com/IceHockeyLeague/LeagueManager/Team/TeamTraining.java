package com.IceHockeyLeague.LeagueManager.Team;

import com.IceHockeyLeague.LeagueManager.Coach.ICoachStats;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;

import java.util.List;
import java.util.Random;

public class TeamTraining {

    public void trainTeam(List<ITeamPlayer> teamPlayers, ICoachStats coachStats) {
        Random rand = new Random();
        for (ITeamPlayer teamPlayer: teamPlayers) {

            double randomChecking = generateRandomNumber();
            double randomSkating = generateRandomNumber();
            double randomShooting = generateRandomNumber();
            double randomSaving = generateRandomNumber();

            if(randomChecking < coachStats.getChecking()){

            }
            else{
                teamPlayer.getInjuredStatus();
            }

            if(randomSkating < coachStats.getSkating()){

            }
            else{
                teamPlayer.getInjuredStatus();
            }

            if(randomShooting < coachStats.getShooting()){

            }
            else{
                teamPlayer.getInjuredStatus();
            }

            if(randomSaving < coachStats.getSaving()){

            }
            else{
                teamPlayer.getInjuredStatus();
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
