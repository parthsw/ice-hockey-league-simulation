package com.TrophySystem;

import com.IceHockeyLeague.LeagueManager.Standings.IStanding;
import com.IceHockeyLeague.LeagueManager.Standings.IStandingSystem;
import com.TrophySystem.IAwardWinners;
import com.TrophySystem.ITrophyNominees;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AwardCeremony implements IAwardWinners, ITrophyNominees {
    String highestPointsTeam;
    String bestPlayer;
    String bestGoalie;
    String bestCoach;
    String topGoalScorer;
    String bestDefenceMan;
    String lowestPointsTeam;
    int bestCoachStats = 0;
    int bestGoaliePoints = 0;
    int bestPlayerPoints = 0;
    int bestGoalScorerPoints = 0;
    int bestDefenceMenPoints = 0;
    Map<String, Integer> bestHeadCoaches;
    Map<String, Integer> bestGoalies;
    Map<String, Integer> bestPlayers;
    Map<String, Integer> bestGoalScorers;
    Map<String, Integer> bestDefenceMen;

    public AwardCeremony(){
        bestHeadCoaches = new HashMap<>();
        bestGoalies = new HashMap<>();
        bestPlayers = new HashMap<>();
        bestGoalScorers = new HashMap<>();
        bestDefenceMen = new HashMap<>();
    }

    public String getHighestPointsTeam(){
        return highestPointsTeam;
    }

    public String getBestPlayer() {
        return bestPlayer;
    }

    public String getBestGoalie(){
        return bestGoalie;
    }

    public String getBestCoach(){
        return bestCoach;
    }

    public String getTopGoalScorer(){
        return topGoalScorer;
    }

    public String getBestDefenceMen(){
        return bestDefenceMan;
    }

    public String getLowestPointsTeam(){
        return lowestPointsTeam;
    }

    @Override
    public void teamNominees(IStandingSystem standingSystem) {
        List<IStanding> standings = standingSystem.getSortedStandingsInLeague();
        highestPointsTeam = standings.get(0).getTeam().getTeamName();
        lowestPointsTeam = standings.get(standings.size()-1).getTeam().getTeamName();
    }

    @Override
    public void playerNominee(String playerName, int playerPoints) {
        String nominee = nominees(bestPlayers, playerName,playerPoints);
        String[] playerDetails = nominee.split(",");
        playerName = playerDetails[0];
        playerPoints = Integer.parseInt(playerDetails[1]);
        if(bestPlayerPoints < playerPoints){
            bestPlayer = playerName;
            bestPlayerPoints = playerPoints;
        }
    }

    @Override
    public void goalieNominee(String goalieName, int goaliePoints) {
        String nominee = nominees(bestGoalies, goalieName,goaliePoints);
        String[] goalieDetails = nominee.split(",");
        goalieName = goalieDetails[0];
        goaliePoints = Integer.parseInt(goalieDetails[1]);
        if(bestGoaliePoints < goaliePoints){
            bestGoalie = goalieName;
            bestGoaliePoints = goaliePoints;
        }
    }

    @Override
    public void coachNominees(String coachName, int coachPoints) {
        String nominee = nominees(bestHeadCoaches, coachName,coachPoints);
        String[] coachDetails = nominee.split(",");
        coachName = coachDetails[0];
        coachPoints = Integer.parseInt(coachDetails[1]);
        if(bestCoachStats < coachPoints){
            bestPlayer = coachName;
            bestPlayerPoints = coachPoints;
        }
    }

    @Override
    public void goalScorerNominee(String goalScorerName, int goalScorerPoints) {
        String nominee = nominees(bestGoalScorers, goalScorerName,goalScorerPoints);
        String[] goalScorerDetails = nominee.split(",");
        goalScorerName = goalScorerDetails[0];
        goalScorerPoints = Integer.parseInt(goalScorerDetails[1]);
        if(bestGoalScorerPoints < goalScorerPoints){
            bestPlayer = goalScorerName;
            bestPlayerPoints = goalScorerPoints;
        }
    }

    @Override
    public void defenceMenNominees(String defenceMenName, int defenceMenPoints) {
        String nominee = nominees(bestDefenceMen, defenceMenName,defenceMenPoints);
        String[] playerDetails = nominee.split(",");
        defenceMenName = playerDetails[0];
        defenceMenPoints = Integer.parseInt(playerDetails[1]);
        if(bestDefenceMenPoints < defenceMenPoints){
            bestDefenceMan = defenceMenName;
            bestDefenceMenPoints = defenceMenPoints;
        }
    }

    public String nominees(Map<String,Integer> nominations, String nomineeName, int points){
        String nomineeDetails;
        if(nominations.containsKey(nomineeName)){
            nominations.put(nomineeName,nominations.get(nomineeName) + points);
        }
        else{
            nominations.put(nomineeName,points);
        }
        List<Map.Entry<String,Integer>> sortedOnes = nominations.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());
        nomineeName = sortedOnes.get(sortedOnes.size()-1).getKey();
        points = sortedOnes.get(sortedOnes.size()-1).getValue();
        nomineeDetails = nomineeName+","+points;
        return nomineeDetails;
    }
}
