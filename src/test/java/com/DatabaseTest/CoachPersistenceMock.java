package com.DatabaseTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
import com.IceHockeyLeague.LeagueManager.Coach.ICoachPersistence;
import com.IceHockeyLeague.LeagueManager.Coach.ICoachStats;

import java.util.List;

public class CoachPersistenceMock implements ICoachPersistence {
    private final ILeagueManagerFactory leagueManagerFactory;

    public CoachPersistenceMock() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactoryTest());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
    }

    @Override
    public boolean saveTeamCoach(ICoach coach) {
        coach.setLeagueID(2);
        coach.setTeamID(2);
        coach.setCoachID(2);
        coach.setCoachName("Jonathan");

        ICoachStats stats = leagueManagerFactory.createCoachStats();
        stats.setSaving(0.1f);
        stats.setChecking(0.3f);
        stats.setShooting(0.5f);
        stats.setSkating(0.7f);
        coach.setCoachStats(stats);

        return true;
    }

    @Override
    public boolean saveLeagueCoach(ICoach coach) {
        coach.setLeagueID(1);
        coach.setTeamID(-1);
        coach.setCoachID(4);
        coach.setCoachName("Ronald");

        ICoachStats stats = leagueManagerFactory.createCoachStats();
        stats.setSaving(0.6f);
        stats.setChecking(0.7f);
        stats.setShooting(0.2f);
        stats.setSkating(0.8f);
        coach.setCoachStats(stats);

        return true;
    }

    @Override
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

    @Override
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
