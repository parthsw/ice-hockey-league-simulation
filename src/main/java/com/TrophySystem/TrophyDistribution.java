package com.TrophySystem;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.Standings.IStanding;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TrophyDistribution implements ITrophyContenders, IAwardWinners {
        ITrophySystemFactory trophySystemFactory = AbstractAppFactory.getTrophySystemFactory();
        String highestPointsTeamName;
        String bestPlayerName;
        String bestGoalieName;
        String topGoalScorerName;
        String bestDefenceManName;
        String lowestPointsTeamName;
        int bestGoaliePoints = 0;
        int bestPlayerPoints = 0;
        int bestGoalScorerPoints = 0;
        int bestDefenceManPoints = 0;
        List<PerformanceParameter> bestGoalies;
        List<PerformanceParameter> bestPlayers;
        List<PerformanceParameter> bestGoalScorers;
        List<PerformanceParameter> bestDefenceMen;

        public TrophyDistribution(){
                bestGoalies = new ArrayList<>();
                bestPlayers = new ArrayList<>();
                bestGoalScorers = new ArrayList<>();
                bestDefenceMen = new ArrayList<>();
        }

    @Override
    public String getHighestPointsTeam() {
        return highestPointsTeamName;
    }

    @Override
    public String getBestPlayer() {
        return bestPlayerName;
    }

    @Override
    public String getBestGoalie() {
        return bestGoalieName;
    }

    @Override
    public String getLowestPointsTeam() {
        return lowestPointsTeamName;
    }

    @Override
    public String getTopGoalScorer() {
        return topGoalScorerName;
    }

    @Override
    public String getBestDefenceMen() {
        return bestDefenceManName;
    }

    @Override
    public void teamContenders(List<IStanding> standingList) {
            highestPointsTeamName = standingList.get(0).getTeam().getTeamName();
            lowestPointsTeamName = standingList.get(0).getTeam().getTeamName();
    }

    @Override
    public void playerContenders(String playerName, int playerPoints) {
        boolean playerFlag = false;
        for (IPerformanceParameter p: bestPlayers) {
            if (p.getPerformerName().equalsIgnoreCase(playerName)) {
                p.setPerformerPoints(p.getPerformerPoints() + playerPoints);
                playerFlag = true;
                break;
            }
        }
        if(playerFlag){
        }
        else{
            IPerformanceParameter newPerformer = trophySystemFactory.performanceParameter();
            newPerformer.setPerformerName(playerName);
            newPerformer.setPerformerPoints(playerPoints);
        }
        bestPlayers.sort(Comparator.comparing(PerformanceParameter::getPerformerPoints));
        playerName = bestPlayers.get(bestPlayers.size()-1).getPerformerName();
        playerPoints = bestPlayers.get(bestPlayers.size()-1).getPerformerPoints();
        if(bestPlayerPoints < playerPoints){
            bestPlayerName = playerName;
            bestPlayerPoints = playerPoints;
        }
    }

    @Override
    public void defenceMenContenders(String defenceMenName, int defenceMenPoints) {
        boolean defenceManFlag = false;
        for (IPerformanceParameter p: bestDefenceMen) {
            if (p.getPerformerName().equalsIgnoreCase(defenceMenName)) {
                p.setPerformerPoints(p.getPerformerPoints() + defenceMenPoints);
                defenceManFlag = true;
                break;
            }
        }
        if(defenceManFlag){
        }
        else{
            IPerformanceParameter newPerformer = trophySystemFactory.performanceParameter();
            newPerformer.setPerformerName(defenceMenName);
            newPerformer.setPerformerPoints(defenceMenPoints);
        }
        bestDefenceMen.sort(Comparator.comparing(PerformanceParameter::getPerformerPoints));
        defenceMenName = bestDefenceMen.get(bestDefenceMen.size()-1).getPerformerName();
        defenceMenPoints = bestDefenceMen.get(bestDefenceMen.size()-1).getPerformerPoints();
        if(bestDefenceManPoints < defenceMenPoints){
            bestDefenceManName = defenceMenName;
            bestDefenceManPoints = defenceMenPoints;
        }
    }

    @Override
    public void goalieContenders(String goalieName, int goaliePoints) {
        boolean goalieFlag = false;
        for (IPerformanceParameter p: bestGoalies) {
            if (p.getPerformerName().equalsIgnoreCase(goalieName)) {
                p.setPerformerPoints(p.getPerformerPoints() + goaliePoints);
                goalieFlag = true;
                break;
            }
        }
        if(goalieFlag){
        }
        else{
            IPerformanceParameter newPerformer = trophySystemFactory.performanceParameter();
            newPerformer.setPerformerName(goalieName);
            newPerformer.setPerformerPoints(goaliePoints);
        }
        bestGoalies.sort(Comparator.comparing(PerformanceParameter::getPerformerPoints));
        goalieName = bestGoalies.get(bestGoalies.size()-1).getPerformerName();
        goaliePoints = bestGoalies.get(bestGoalies.size()-1).getPerformerPoints();
        if(bestGoaliePoints < goaliePoints){
            bestGoalieName = goalieName;
            bestGoaliePoints = goaliePoints;
        }
    }

    @Override
    public void goalScorerContenders(String goalScorerName, int goalScorerPoints) {
        boolean goalScorerFlag = false;
        for (IPerformanceParameter p: bestGoalScorers) {
            if (p.getPerformerName().equalsIgnoreCase(goalScorerName)) {
                p.setPerformerPoints(p.getPerformerPoints() + goalScorerPoints);
                goalScorerFlag = true;
                break;
            }
        }
        if(goalScorerFlag){
        }
        else{
            IPerformanceParameter newPerformer = trophySystemFactory.performanceParameter();
            newPerformer.setPerformerName(goalScorerName);
            newPerformer.setPerformerPoints(goalScorerPoints);
        }
        bestGoalScorers.sort(Comparator.comparing(PerformanceParameter::getPerformerPoints));
        goalScorerName = bestGoalScorers.get(bestGoalScorers.size()-1).getPerformerName();
        goalScorerPoints = bestGoalScorers.get(bestGoalScorers.size()-1).getPerformerPoints();
        if(bestGoalScorerPoints < goalScorerPoints){
            topGoalScorerName = goalScorerName;
            bestGoalScorerPoints = goalScorerPoints;
        }
    }
}
