package com.Persistence;

import com.IceHockeyLeague.LeagueManager.League.ILeague;

public interface ILeaguePersistence {
    boolean saveLeague(ILeague league);
    ILeague loadLeague(String filePath);
}