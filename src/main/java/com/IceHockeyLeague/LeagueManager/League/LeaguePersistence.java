package com.IceHockeyLeague.LeagueManager.League;

public class LeaguePersistence implements ILeaguePersistence {
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
