package com.IceHockeyLeague.LeagueManager.FreeAgent;

import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Player.IPlayer;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerCareerProgression;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;

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
