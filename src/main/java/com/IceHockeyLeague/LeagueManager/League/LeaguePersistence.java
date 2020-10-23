package com.IceHockeyLeague.LeagueManager.League;

public class LeaguePersistence implements ILeaguePersistence {
    @Override
    public boolean saveLeague(ILeague league) {
        return false;
    }

    @Override
    public boolean loadLeague(int leagueId, ILeague league) {
        return false;
    }

    @Override
    public boolean checkIfLeagueNameExists(String leagueName) {
        return false;
    }
}
