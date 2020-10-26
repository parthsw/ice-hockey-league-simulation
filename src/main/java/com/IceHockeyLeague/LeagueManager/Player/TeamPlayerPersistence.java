package com.IceHockeyLeague.LeagueManager.Player;

import java.util.List;

public class TeamPlayerPersistence implements ITeamPlayerPersistence {
    @Override
    public boolean saveTeamPlayer(ITeamPlayer teamPlayer) {
        return false;
    }

    @Override
    public boolean loadTeamPlayers(int teamId, List<ITeamPlayer> teamPlayers) {
        return false;
    }
}
