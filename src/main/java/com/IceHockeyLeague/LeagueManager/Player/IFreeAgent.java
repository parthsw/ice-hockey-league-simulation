package com.IceHockeyLeague.LeagueManager.Player;

public interface IFreeAgent extends IPlayer {

    int getFreeAgentID();
    void setFreeAgentID(int freeAgentID);

    int getLeagueID();
    void setLeagueID(int leagueID);

    boolean saveFreeAgent(IFreeAgentPersistence freeAgentDB);
}
