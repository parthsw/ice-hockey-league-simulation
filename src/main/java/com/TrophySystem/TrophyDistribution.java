package com.TrophySystem;

import com.IceHockeyLeague.LeagueManager.Standings.IStanding;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TrophyDistribution implements ITrophyContenders, IAwardWinners {
        String highestPointsTeamName;
        String bestPlayerName;
        String bestGoalieName;
        String bestCoachName;
        String topGoalScorerName;
        String bestDefenceManName;
        String lowestPointsTeamName;
        int bestCoachStats = 0;
        int goaliePoints = 0;
        int bestPlayerPoints = 0;
        int bestGoalScorerPoints = 0;
        int bestDefenceManPoints = 0;
        List<PerformanceParameter> bestHeadCoaches;
        List<PerformanceParameter> bestGoalies;
        List<PerformanceParameter> bestPlayers;
        List<PerformanceParameter> bestGoalScorers;
        List<PerformanceParameter> bestDefenceMen;

        public TrophyDistribution(){
                bestHeadCoaches = new ArrayList<>();
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
    public String getBestCoach() {
        return bestCoachName;
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
    public void coachContenders(String coachName, int coachPoints) {
            String bestCoachNameSorted;
            int bestCoachStatsSorted;
        boolean coachFlag = false;
        for (IPerformanceParameter p: bestHeadCoaches) {
            if (p.getPerformerName().equalsIgnoreCase(coachName)) {
                p.setPerformerPoints(p.getPerformerPoints() + coachPoints);
                coachFlag = true;
                break;
            }
        }
        if(coachFlag){

        }
        else{
            IPerformanceParameter newPerformer = new PerformanceParameter();
            newPerformer.setPerformerName(coachName);
            newPerformer.setPerformerPoints(coachPoints);
        }
        bestHeadCoaches.sort(Comparator.comparing(PerformanceParameter::getPerformerPoints));
        bestCoachNameSorted = bestHeadCoaches.get(bestHeadCoaches.size()-1).getPerformerName();
        bestCoachStatsSorted = bestHeadCoaches.get(bestHeadCoaches.size()-1).getPerformerPoints();
        if(bestCoachStatsSorted < coachPoints){
            bestCoachName = bestCoachNameSorted;
            bestCoachStats = bestCoachStatsSorted;
        }
    }

    @Override
    public void playerContenders(String playerName, int playerPoints) {

    }

    @Override
    public void defenceMenContenders(String defenceMenName, int defenceMenPoints) {

    }

    @Override
    public void goalieContenders(String goalieName, int goaliePoints) {

    }

    @Override
    public void goalScorerContenders(String goalScorerName, int goalScorerPoints) {

    }
}
