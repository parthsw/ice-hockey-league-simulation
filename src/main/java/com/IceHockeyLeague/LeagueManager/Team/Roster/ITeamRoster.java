package com.IceHockeyLeague.LeagueManager.Team.Roster;

import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;

import java.util.List;

public interface ITeamRoster {
    List<ITeamPlayer> getActiveRoster();

    void setPlayers(List<ITeamPlayer> players);

    List<ITeamPlayer> getPlayers();

    void setAgents(List<IFreeAgent> agents);

    List<ITeamPlayer> getInactiveRoster();

    void validateRoster();

}
