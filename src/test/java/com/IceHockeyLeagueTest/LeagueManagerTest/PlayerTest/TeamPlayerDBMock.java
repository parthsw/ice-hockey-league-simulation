package com.IceHockeyLeagueTest.LeagueManagerTest.PlayerTest;

import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayerPersistence;

import java.util.List;

public class TeamPlayerDBMock implements ITeamPlayerPersistence {
    @Override
    public boolean saveTeamPlayer(ITeamPlayer teamPlayer) {
        return false;
    }

    @Override
    public boolean loadTeamPlayers(int teamId, List<ITeamPlayer> teamPlayers) {
        return false;
    }
}
