package com.IceHockeyLeague.Trading;

import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.List;

public interface IGetAllTeamsFromLeague {
    void gatherTeams(ILeague league);

    List<ITeam> getTeams();
}
