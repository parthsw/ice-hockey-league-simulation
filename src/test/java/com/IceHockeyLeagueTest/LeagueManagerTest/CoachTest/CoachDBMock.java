package com.IceHockeyLeagueTest.LeagueManagerTest.CoachTest;

import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
import com.IceHockeyLeague.LeagueManager.Coach.ICoachPersistence;

import java.util.List;

public class CoachDBMock implements ICoachPersistence {
    @Override
    public boolean saveCoach(ICoach coach) {
        return false;
    }

    @Override
    public boolean loadCoach(int teamId, ICoach coach) {
        return false;
    }

    @Override
    public List<ICoach> loadUnassignedCoaches(int leagueId) {
        return null;
    }
}
