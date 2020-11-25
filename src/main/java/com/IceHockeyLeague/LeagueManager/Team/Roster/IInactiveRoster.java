package com.IceHockeyLeague.LeagueManager.Team.Roster;

import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;

public interface IInactiveRoster {
    ITeamPlayer getReplacementPlayer(ITeamPlayer player, String position);
}
