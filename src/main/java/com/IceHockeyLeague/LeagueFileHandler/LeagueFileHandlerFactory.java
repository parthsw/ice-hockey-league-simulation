package com.IceHockeyLeague.LeagueFileHandler;

public class LeagueFileHandlerFactory extends AbstractLeagueFileHandlerFactory {

    @Override
    public ILeagueFileReader getLeagueFileReader() {
        return new LeagueFileReader();
    }

    @Override
    public IJsonParser getJsonParser() {
        return new JsonParser();
    }

    @Override
    public ILeagueFileValidator getLeagueFileValidator() {
        return new LeagueFileValidator();
    }
}
