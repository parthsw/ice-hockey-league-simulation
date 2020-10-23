package com.IceHockeyLeague.LeagueManager.League;

public interface ILeaguePersistence {
    boolean saveLeague(ILeague league);
    boolean loadLeague(int leagueId, ILeague league);
    boolean checkIfLeagueNameExists(String leagueName);
}
