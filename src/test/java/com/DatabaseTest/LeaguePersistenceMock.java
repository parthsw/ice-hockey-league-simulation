package com.DatabaseTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueCreator;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.ILeaguePersistence;
import com.IceHockeyLeagueTest.LeagueFileHandlerTest.LeagueJsonMock;

import java.time.LocalDate;

public class LeaguePersistenceMock implements ILeaguePersistence {
    private final ILeagueManagerFactory leagueManagerFactory;

    public LeaguePersistenceMock() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactoryTest());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
    }

    @Override
    public boolean saveLeague(ILeague league) {
        LeagueJsonMock leagueJsonMock = LeagueJsonMock.getInstance();
        ILeagueCreator leagueCreator = leagueManagerFactory.createLeagueCreator();
        ILeague createdLeague = leagueCreator.createLeague(leagueJsonMock.validLeagueJson());

        league.setLeagueName("NHL");
        league.setLeagueID(2);
        league.setLeagueDate(LocalDate.now());
        league.setGamePlayConfig(createdLeague.getGamePlayConfig());
        league.setConferences(createdLeague.getConferences());
        league.setFreeAgents(createdLeague.getFreeAgents());
        league.setManagers(createdLeague.getManagers());
        league.setCoaches(createdLeague.getCoaches());
        return true;
    }

    @Override
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

    @Override
    public boolean checkIfLeagueNameExists(String leagueName) {
        return false;
    }
}
