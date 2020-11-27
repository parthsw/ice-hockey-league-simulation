package com.PersistenceTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
import com.IceHockeyLeague.LeagueManager.Coach.ICoachStats;

import java.util.List;

public class CoachPersistenceMock {
    private final ILeagueManagerFactory leagueManagerFactory;

    public CoachPersistenceMock() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
    }

    public boolean loadTeamCoach(int teamId, ICoach coach) {
        coach.setLeagueID(1);
        coach.setTeamID(1);
        coach.setCoachID(1);
        coach.setCoachName("Joe Doe");

        ICoachStats stats = leagueManagerFactory.createCoachStats();
        stats.setSaving(0.2f);
        stats.setChecking(0.6f);
        stats.setShooting(0.9f);
        stats.setSkating(0.5f);
        coach.setCoachStats(stats);

        return true;
    }

    public boolean loadLeagueCoaches(int leagueId, List<ICoach> coaches) {
        ICoach coach = leagueManagerFactory.createCoach();
        coach.setLeagueID(leagueId);
        coach.setTeamID(1);
        coach.setCoachID(1);
        coach.setCoachName("Joe Doe");

        ICoachStats stats = leagueManagerFactory.createCoachStats();
        stats.setSaving(0.2f);
        stats.setChecking(0.6f);
        stats.setShooting(0.9f);
        stats.setSkating(0.5f);
        coach.setCoachStats(stats);
        coaches.add(coach);

        ICoach coach1 = leagueManagerFactory.createCoach();
        coach1.setLeagueID(leagueId);
        coach1.setTeamID(2);
        coach1.setCoachID(2);
        coach1.setCoachName("Jonathan");

        ICoachStats stats1 = leagueManagerFactory.createCoachStats();
        stats1.setSaving(0.1f);
        stats1.setChecking(0.3f);
        stats1.setShooting(0.5f);
        stats1.setSkating(0.7f);
        coach1.setCoachStats(stats1);
        coaches.add(coach1);

        return true;
    }

}
