package com.IceHockeyLeagueTest.LeagueManagerTest.ManagerTest;

import com.IceHockeyLeague.LeagueManager.Manager.IManager;
import com.IceHockeyLeague.LeagueManager.Manager.IManagerPersistence;

import java.util.List;

public class ManagerDBMock implements IManagerPersistence  {
    @Override
    public boolean saveTeamManager(IManager manager) {
        return false;
    }

    @Override
    public boolean saveLeagueManager(IManager manager) {
        return false;
    }

    @Override
    public boolean loadTeamManager(int teamId, IManager manager) {
        manager.setLeagueID(1);
        manager.setTeamID(1);
        manager.setManagerID(1);
        manager.setManagerName("Joseph Spaghetti");
        return true;
    }

    @Override
    public boolean loadLeagueManagers(int leagueId, List<IManager> managers) {
        return false;
    }
}
