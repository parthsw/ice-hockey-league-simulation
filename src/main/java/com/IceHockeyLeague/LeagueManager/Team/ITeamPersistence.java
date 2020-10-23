package com.IceHockeyLeague.LeagueManager.Team;

import java.util.List;

public interface ITeamPersistence {
    boolean saveTeam(ITeam team);
    boolean loadTeams(int divisionId, List<ITeam> teams);
}
