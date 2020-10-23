package com.IceHockeyLeagueTest.LeagueManagerTest.LeagueTest;

import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.ILeaguePersistence;

public class LeagueDBMock implements ILeaguePersistence {
    @Override
    public boolean saveLeague(ILeague league) {
        return false;
    }

    @Override
    public ILeague loadLeague(int leagueId) {
        return null;
    }

    @Override
    public boolean checkIfLeagueNameExists(String leagueName) {
        return false;
    }
}
