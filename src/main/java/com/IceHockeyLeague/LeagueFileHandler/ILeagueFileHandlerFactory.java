package com.IceHockeyLeague.LeagueFileHandler;

public interface ILeagueFileHandlerFactory {

    ILeagueFileReader createLeagueFileReader();
    IJsonParser createJsonParser();
    ILeagueFileValidator createLeagueFileValidator();

}
