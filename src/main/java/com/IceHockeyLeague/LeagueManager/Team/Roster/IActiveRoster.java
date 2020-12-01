package com.IceHockeyLeague.LeagueManager.Team.Roster;

import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;

import java.util.List;

public interface IActiveRoster {
    void validateActiveRoster(IInactiveRoster inactiveRoster);

    List<ITeamPlayer> getActivePlayers();

    void setActivePlayers(List<ITeamPlayer> activePlayers);


}
