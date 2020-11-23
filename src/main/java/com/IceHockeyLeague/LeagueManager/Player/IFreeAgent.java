package com.IceHockeyLeague.LeagueManager.Player;

import com.IceHockeyLeague.LeagueManager.League.ILeague;

import java.util.List;

public interface IFreeAgent extends IPlayer {

    int getFreeAgentID();
    void setFreeAgentID(int freeAgentID);

    int getLeagueID();
    void setLeagueID(int leagueID);

    ITeamPlayer convertToTeamPlayer(ITeamPlayer teamPlayer);
    void generateFreeAgent(IPlayer player);

    IFreeAgent bestFreeAgentForPosition(List<IFreeAgent> freeAgentList, String position);

    boolean saveFreeAgent(IFreeAgentPersistence freeAgentDB);

    boolean handleFreeAgentRetirement(IPlayerCareerProgression playerCareerProgression, ILeague league);
}
