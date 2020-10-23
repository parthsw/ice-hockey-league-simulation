package com.IceHockeyLeagueTest.LeagueManagerTest.TeamTest;

import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Team.ITeamPersistence;

import java.util.List;

public class TeamDBMock implements ITeamPersistence {
    @Override
    public boolean saveTeam(ITeam team) {
        return false;
    }

    @Override
    public boolean loadTeams(int divisionId, List<ITeam> teams) {
        return false;
    }
}
