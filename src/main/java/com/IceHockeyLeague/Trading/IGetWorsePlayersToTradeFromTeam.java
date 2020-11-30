package com.IceHockeyLeague.Trading;

import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.List;

public interface IGetWorsePlayersToTradeFromTeam {

    List<ITeamPlayer> getPlayersToTrade(int maxTradablePlayers, ITeam team);
}
