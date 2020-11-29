package com.IceHockeyLeague.LeagueManager.FreeAgent;

import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Player.IPlayer;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerCareerProgression;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;

import java.util.List;

public interface IFreeAgent extends IPlayer {
    int getFreeAgentId();

    void setFreeAgentId(int freeAgentId);

    int getLeagueId();

    void setLeagueId(int leagueId);

    ITeamPlayer convertToTeamPlayer(ITeamPlayer teamPlayer);

    void generateFreeAgent(IPlayer player);

    IFreeAgent bestFreeAgentForPosition(List<IFreeAgent> freeAgents, String position);

    boolean saveFreeAgent(IFreeAgentPersistence freeAgentDb);

    boolean handleFreeAgentRetirement(IPlayerCareerProgression playerCareerProgression, ILeague league);
}
