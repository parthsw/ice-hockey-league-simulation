package com.IceHockeyLeague.LeagueManager.Team.TeamRoster;

import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;

public interface IInactiveRoster {
    ITeamPlayer getReplacementPlayer(ITeamPlayer player, String position);
}
