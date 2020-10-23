package com.IceHockeyLeague.LeagueManager.Conference;

import java.util.List;

public class ConferencePersistence implements IConferencePersistence {
    @Override
    public boolean saveConference(IConference conference) {
        return false;
    }

    @Override
    public boolean loadConferences(int leagueId, List<IConference> conferences) {
        return false;
    }
}
