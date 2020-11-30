package com.IceHockeyLeague.LeagueManager.GameSimulator;

public interface IGameSimulationConfig {
    int getAverageGoalsPerTeam();
    void setAverageGoalsPerTeam(int averageGoalsPerTeam);
    int getAveragePenaltiesPerTeam();
    void setAveragePenaltiesPerTeam(int averagePenaltiesPerTeam);
    int getAverageShotsPerTeam();
    void setAverageShotsPerTeam(int averageShotsPerTeam);
    int getAverageSavesPerTeam();
    void setAverageSavesPerTeam(int averageSavesPerTeam);
}
