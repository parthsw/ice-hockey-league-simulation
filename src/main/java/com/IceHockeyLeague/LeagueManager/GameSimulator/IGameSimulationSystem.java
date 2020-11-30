package com.IceHockeyLeague.LeagueManager.GameSimulator;

import com.IceHockeyLeague.LeagueManager.Team.ITeam;

public interface IGameSimulationSystem {
    int getTotalGoals();
    void setTotalGoals(int totalGoals);
    int getTotalPenalties();
    void setTotalPenalties(int totalPenalties);
    int getTotalShots();
    void setTotalShots(int totalShots);
    int getTotalSaves();
    void setTotalSaves(int totalSaves);
    int getNumberOfGamesPlayed();
    void setNumberOfGamesPlayed(int numberOfGamesPlayed);
    ITeam simulateGameAndGetWinner(ITeam teamA, ITeam teamB);
}
