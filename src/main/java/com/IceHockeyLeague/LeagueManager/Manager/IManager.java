package com.IceHockeyLeague.LeagueManager.Manager;

public interface IManager {
    int getManagerID();
    void setManagerID(int id);

    String getManagerName();
    void setManagerName(String name);

    int getTeamID();
    void setTeamID(int id);

    int getLeagueID();
    void setLeagueID(int id);

    boolean isValid();
}
