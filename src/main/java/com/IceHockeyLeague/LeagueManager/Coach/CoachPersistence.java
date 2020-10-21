package com.IceHockeyLeague.LeagueManager.Coach;

import java.util.List;

public class CoachPersistence implements ICoachPersistence {
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
