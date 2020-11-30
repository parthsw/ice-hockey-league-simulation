package com.TrophySystem;

import com.IceHockeyLeague.LeagueManager.Standings.IStanding;

import java.util.List;

public interface ITrophyContenders {
    void teamContenders(List<IStanding> standingList);
    void playerContenders(String playerName,int playerPoints);
    void goalieContenders(String goalieName, int goaliePoints);
    void goalScorerContenders(String goalScorerName, int goalScorerPoints);
    void defenceMenContenders(String defenceMenName, int defenceMenPoints);
}
