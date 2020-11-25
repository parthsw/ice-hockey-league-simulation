package com.IceHockeyLeague.LeagueManager.Manager;

import java.util.List;

public interface IManager {
    int getManagerID();
    void setManagerID(int id);

    String getManagerName();
    void setManagerName(String name);

    int getTeamID();
    void setTeamID(int id);

    int getLeagueID();
    void setLeagueID(int id);

    boolean isNullOrEmpty(String managerName);
    boolean isManagerNameExist(List<IManager> managers, String managerName);
}
