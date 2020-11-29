package com.IceHockeyLeague.LeagueManager.Player;

import java.util.List;

public interface ITeamPlayerPersistence {
    boolean saveTeamPlayer(ITeamPlayer teamPlayer);

    boolean loadTeamPlayers(int teamId, List<ITeamPlayer> teamPlayers);
}
