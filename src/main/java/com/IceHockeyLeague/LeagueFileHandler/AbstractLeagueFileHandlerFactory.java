package com.IceHockeyLeague.LeagueFileHandler;

public abstract class AbstractLeagueFileHandlerFactory {
    private static AbstractLeagueFileHandlerFactory abstractLeagueFileHandlerFactory;

    public static AbstractLeagueFileHandlerFactory getFactory() {
        return abstractLeagueFileHandlerFactory;
    }

    public static void setFactory(AbstractLeagueFileHandlerFactory factory) {
        abstractLeagueFileHandlerFactory = factory;
    }

    public abstract ILeagueFileReader getLeagueFileReader();
    public abstract IJsonParser getJsonParser();
    public abstract ILeagueFileValidator getLeagueFileValidator();
}
