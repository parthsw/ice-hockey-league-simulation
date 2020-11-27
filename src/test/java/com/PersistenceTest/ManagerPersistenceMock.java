package com.PersistenceTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Manager.IManager;
import com.IceHockeyLeague.LeagueManager.Manager.IManagerPersistence;

import java.util.List;

public class ManagerPersistenceMock implements IManagerPersistence  {
    private final ILeagueManagerFactory leagueManagerFactory;

    public ManagerPersistenceMock() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
    }

    @Override
    public boolean saveTeamManager(IManager manager) {
        manager.setLeagueID(2);
        manager.setTeamID(1);
        manager.setManagerID(2);
        manager.setManagerName("Joseph Hans");
        return true;
    }

    @Override
    public boolean saveLeagueManager(IManager manager) {
        manager.setLeagueID(3);
        manager.setTeamID(-1);
        manager.setManagerID(3);
        manager.setManagerName("Roy K");
        return true;
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
        IManager manager = leagueManagerFactory.createManager();
        manager.setLeagueID(leagueId);
        manager.setTeamID(-1);
        manager.setManagerName("Fred One");
        manager.setManagerID(1);
        managers.add(manager);

        IManager manager1 = leagueManagerFactory.createManager();
        manager1.setLeagueID(leagueId);
        manager1.setTeamID(-1);
        manager1.setManagerName("Mike One");
        manager1.setManagerID(2);
        managers.add(manager1);

        IManager manager2 = leagueManagerFactory.createManager();
        manager2.setLeagueID(leagueId);
        manager2.setTeamID(-1);
        manager2.setManagerName("Mike Two");
        manager2.setManagerID(3);
        managers.add(manager2);

        return true;
    }
}
