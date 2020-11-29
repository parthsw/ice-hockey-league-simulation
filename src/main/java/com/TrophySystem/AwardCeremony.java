package com.TrophySystem;

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
    public void teamNominees() {
        String teamName;
        int score;
        Map<String, Integer> teams = new HashMap<>();
    }

    @Override
    public void playerNominee(String playerName, int playerPoints) {

    }

    @Override
    public void goalieNominee(String goalieName, int goaliePoints) {

    }

    @Override
    public void coachNominees(String coachName, int coachPoints) {

    }

    @Override
    public void goalScorerNominee(String goalScorerName, int goalScorerPoints) {

    }

    @Override
    public void defenceMenNominees(String defenceMenName, int defenceMenPoints) {

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
