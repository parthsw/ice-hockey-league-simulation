package com.Database;

import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
import com.IceHockeyLeague.LeagueManager.Coach.ICoachPersistence;

import java.util.List;

public class CoachPersistence implements ICoachPersistence {
    @Override
    public boolean saveCoach(ICoach coach) {
        return false;
    }

    @Override
    public boolean loadTeamCoach(int leagueId, int teamId, ICoach coach) {
        return false;
    }

    @Override
    public boolean loadLeagueCoaches(int leagueId, List<ICoach> coaches) {
        return false;
    }
}
