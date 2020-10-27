package com.IceHockeyLeague.LeagueManager.Manager;

import java.util.List;

public interface IManagerPersistence {
    boolean saveManager(IManager manager);
    boolean loadTeamManager(int teamId, IManager manager);
    boolean loadLeagueManagers(int leagueId, List<IManager> managers);
}
