package com.IceHockeyLeague.LeagueManager.Team;

import com.IceHockeyLeague.LeagueManager.League.ILeague;

import java.util.List;

public interface ITeamPersistence {
    boolean saveTeam(ITeam team);
    boolean loadTeams(int divisionId, List<ITeam> teams);
    boolean checkIfTeamNameExists(String teamName, List<ILeague> leagues);
}
