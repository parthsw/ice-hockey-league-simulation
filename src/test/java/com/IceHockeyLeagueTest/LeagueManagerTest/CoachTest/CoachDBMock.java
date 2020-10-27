package com.IceHockeyLeagueTest.LeagueManagerTest.CoachTest;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
import com.IceHockeyLeague.LeagueManager.Coach.ICoachPersistence;
import com.IceHockeyLeague.LeagueManager.Coach.ICoachStats;

import java.util.List;

public class CoachDBMock implements ICoachPersistence {
    @Override
    public boolean saveCoach(ICoach coach) {
        return false;
    }

    @Override
    public boolean loadTeamCoach(int leagueId, int teamId, ICoach coach) {
        coach.setLeagueID(1);
        coach.setTeamID(1);
        coach.setCoachID(1);
        coach.setCoachName("Joe Doe");

        ICoachStats stats = AbstractLeagueManagerFactory.getFactory().getCoachStats();
        stats.setSaving(0.2f);
        stats.setChecking(0.6f);
        stats.setShooting(0.9f);
        stats.setSkating(0.5f);

        coach.setCoachStats(stats);

        return true;
    }

    @Override
    public boolean loadLeagueCoaches(int leagueId, List<ICoach> coaches) {
        return false;
    }
}
