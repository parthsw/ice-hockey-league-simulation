package com.IceHockeyLeague.Trading;

import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;

import java.util.List;

public interface IGetPlayerFromTeam {
    ITeamPlayer getWorsePlayerInTeamWithPosition(List<ITeamPlayer> players, String position);

    ITeamPlayer getWorseSkaterInTeam(List<ITeamPlayer> players);
}
