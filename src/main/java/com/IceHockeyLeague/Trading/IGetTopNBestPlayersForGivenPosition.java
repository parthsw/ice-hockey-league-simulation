package com.IceHockeyLeague.Trading;

import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;

import java.util.List;

public interface IGetTopNBestPlayersForGivenPosition {
    List<ITeamPlayer> getPlayers();

    float getCombinedStrendth();
}
