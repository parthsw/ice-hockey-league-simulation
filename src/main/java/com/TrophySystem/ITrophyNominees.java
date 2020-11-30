package com.TrophySystem;

import com.IceHockeyLeague.LeagueManager.Standings.IStanding;
import com.IceHockeyLeague.LeagueManager.Standings.IStandingSystem;

import java.util.List;

public interface ITrophyNominees {
    void teamNominees(List<IStanding> standingList);
    void playerNominee(String playerName,int playerPoints);
    void goalieNominee(String goalieName, int goaliePoints);
    void coachNominees(String coachName, int coachPoints);
    void goalScorerNominee(String goalScorerName, int goalScorerPoints);
    void defenceMenNominees(String defenceMenName, int defenceMenPoints);
}
