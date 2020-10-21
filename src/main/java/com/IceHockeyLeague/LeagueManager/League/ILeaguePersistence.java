package com.IceHockeyLeague.LeagueManager.League;

public interface ILeaguePersistence {
    boolean saveLeague(ILeague league);
    ILeague loadLeague(int leagueId);
    boolean checkIfLeagueNameExists(String leagueName);
}
