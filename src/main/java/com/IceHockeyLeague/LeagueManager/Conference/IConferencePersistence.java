package com.IceHockeyLeague.LeagueManager.Conference;

import java.util.List;

public interface IConferencePersistence {
    boolean saveConference(IConference conference);
    boolean loadConferences(int leagueId, List<IConference> conferences);
}
