package com.IceHockeyLeague.LeagueManager.Player;

import com.IceHockeyLeague.LeagueManager.League.ILeague;

public interface IFreeAgent extends IPlayer {

    int getFreeAgentID();
    void setFreeAgentID(int freeAgentID);

    int getLeagueID();
    void setLeagueID(int leagueID);

    ITeamPlayer convertToTeamPlayer(ITeamPlayer teamPlayer);

    boolean saveFreeAgent(IFreeAgentPersistence freeAgentDB);

    boolean handleFreeAgentRetirement(IPlayerCareerProgression playerCareerProgression, ILeague league);
}
