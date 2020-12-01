package com.IceHockeyLeague.LeagueManager.GameSimulator;

public interface IGameStats {
    int getTeam1Goals();
    void setTeam1Goals(int team1Goals);
    int getTeam1Penalties();
    void setTeam1Penalties(int team1Penalties);
    int getTeam1Shots();
    void setTeam1Shots(int team1Shots);
    int getTeam1Saves();
    void setTeam1Saves(int team1Saves);
    int getTeam2Goals();
    void setTeam2Goals(int team2Goals);
    int getTeam2Penalties();
    void setTeam2Penalties(int team2Penalties);
    int getTeam2Shots();
    void setTeam2Shots(int team2Shots);
    int getTeam2Saves();
    void setTeam2Saves(int team2Saves);
}
