package com.IceHockeyLeagueTest.LeagueManagerTest.ConferenceTest;

import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Conference.IConferencePersistence;

import java.util.List;

public class ConferenceDBMock implements IConferencePersistence {
    @Override
    public boolean saveConference(IConference conference) {
        return false;
    }

    @Override
    public boolean loadConferences(int leagueId, List<IConference> conferences) {
        return false;
    }
}
