package com.TrophySystem;

import com.IceHockeyLeague.LeagueManager.Standings.IStandingSystem;

public interface ITrophyNominees {
    void teamNominees(IStandingSystem sandingSystem);
    void playerNominee(String playerName,int playerPoints);
    void goalieNominee(String goalieName, int goaliePoints);
    void coachNominees(String coachName, int coachPoints);
    void goalScorerNominee(String goalScorerName, int goalScorerPoints);
    void defenceMenNominees(String defenceMenName, int defenceMenPoints);
}
