package com.IceHockeyLeague.LeagueManager.Manager;

import java.util.List;

public class ManagerPersistence implements IManagerPersistence {
    @Override
    public boolean saveManager(IManager manager) {
        return false;
    }

    @Override
    public boolean loadTeamManager(int leagueId, int teamId, IManager manager) {
        return false;
    }

    @Override
    public boolean loadLeagueManagers(int leagueId, List<IManager> manager) {
        return false;
    }
}
