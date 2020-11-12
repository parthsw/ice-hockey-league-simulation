package com.IceHockeyLeague.LeagueFileHandler;

public class LeagueFileHandlerFactory implements ILeagueFileHandlerFactory {

    @Override
    public ILeagueFileReader createLeagueFileReader() {
        return new LeagueFileReader();
    }

    @Override
    public IJsonParser createJsonParser() {
        return new JsonParser();
    }

    @Override
    public ILeagueFileValidator createLeagueFileValidator() {
        return new LeagueFileValidator();
    }

}
