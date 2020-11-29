package com.TrophySystem;

public interface ITrophyNominees {
    void teamNominees();
    void playerNominee(String playerName,int playerPoints);
    void goalieNominee(String goalieName, int goaliePoints);
    void coachNominees(String coachName, int coachPoints);
    void goalScorerNominee(String goalScorerName, int goalScorerPoints);
    void defenceMenNominees(String defenceMenName, int defenceMenPoints);
}
