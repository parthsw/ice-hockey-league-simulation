package com.PersistenceTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueCreator;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeagueTest.LeagueFileHandlerTest.LeagueJsonMock;
import com.Persistence.ILeaguePersistence;

import java.time.LocalDate;

public class LeaguePersistenceMock implements ILeaguePersistence {
    private final ILeagueManagerFactory leagueManagerFactory;

    public LeaguePersistenceMock() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
    }

    public boolean loadLeague(int leagueId, ILeague league) {
        LeagueJsonMock leagueJsonMock = LeagueJsonMock.getInstance();
        ILeagueCreator leagueCreator = leagueManagerFactory.createLeagueCreator();
        ILeague createdLeague = leagueCreator.createLeague(leagueJsonMock.validLeagueJson());

        league.setLeagueName(createdLeague.getLeagueName());
        league.setLeagueID(leagueId);
        league.setGamePlayConfig(createdLeague.getGamePlayConfig());
        league.setConferences(createdLeague.getConferences());
        league.setFreeAgents(createdLeague.getFreeAgents());
        league.setManagers(createdLeague.getManagers());
        league.setCoaches(createdLeague.getCoaches());
        return true;
    }

    public boolean checkIfLeagueNameExists(String leagueName) {
        return false;
    }

    @Override
    public boolean saveLeague(ILeague league) {
        return false;
    }

    @Override
    public ILeague loadLeague(String filePath) {
        ILeague league = leagueManagerFactory.createLeague();
        loadLeague(1, league);
        return league;
    }
}
