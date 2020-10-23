package com.IceHockeyLeague.LeagueManager.Team;

import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;

import java.util.List;

public class TeamPersistence implements ITeamPersistence {
    @Override
    public boolean saveTeam(ITeam team) {
        return false;
    }

    @Override
    public boolean loadTeams(int divisionId, List<ITeam> teams) {
        return false;
    }
}
