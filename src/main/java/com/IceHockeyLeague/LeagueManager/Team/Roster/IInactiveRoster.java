package com.IceHockeyLeague.LeagueManager.Team.Roster;

import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;

import java.util.List;

public interface IInactiveRoster {
    ITeamPlayer getReplacementPlayer(ITeamPlayer player, String position);

    List<ITeamPlayer> getInactivePlayers();

    void setInactivePlayers(List<ITeamPlayer> players);

    void setAgents(List<IFreeAgent> agents);
}
