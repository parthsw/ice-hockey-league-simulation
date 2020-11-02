package com.IceHockeyLeagueTest.LeagueManagerTest.LeagueTest;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueCreator;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.ILeaguePersistence;
import com.IceHockeyLeagueTest.LeagueFileHandlerTest.LeagueJsonMock;

import java.time.LocalDate;

public class LeagueDBMock implements ILeaguePersistence {
    @Override
    public boolean saveLeague(ILeague league) {
        LeagueJsonMock leagueJsonMock = LeagueJsonMock.getInstance();
        ILeagueCreator leagueCreator = AbstractLeagueManagerFactory.getFactory().getLeagueCreator();
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
        ILeagueCreator leagueCreator = AbstractLeagueManagerFactory.getFactory().getLeagueCreator();
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
