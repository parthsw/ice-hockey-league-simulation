package com.IceHockeyLeague.Trading;

import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.List;

public interface IGetBestPlayersFromAllTeams {
    void getBestTradeOption(int tradableNumber);

    ITeam getTeam();

    List<ITeamPlayer> getBestPlayersSet();

}
