package com.IceHockeyLeagueTest.LeagueManagerTest.SchedulerTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Scheduler.ISchedule;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;

public class ScheduleTest {
    private static ILeagueManagerFactory leagueManagerFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
    }

    @Test
    public void setFirstConferenceTest() {
        ISchedule schedule = leagueManagerFactory.createSchedule();
        IConference conference = leagueManagerFactory.createConference();
        schedule.setFirstConference(conference);
        Assert.assertEquals(conference, schedule.getFirstConference());
    }

    @Test
    public void setSecondConferenceTest() {
        ISchedule schedule = leagueManagerFactory.createSchedule();
        IConference conference = leagueManagerFactory.createConference();
        schedule.setSecondConference(conference);
        Assert.assertEquals(conference, schedule.getSecondConference());
    }

    @Test
    public void setFirstDivisionTest() {
        ISchedule schedule = leagueManagerFactory.createSchedule();
        IDivision division = leagueManagerFactory.createDivision();
        schedule.setFirstDivision(division);
        Assert.assertEquals(division, schedule.getFirstDivision());
    }

    @Test
    public void setSecondDivisionTest() {
        ISchedule schedule = leagueManagerFactory.createSchedule();
        IDivision division = leagueManagerFactory.createDivision();
        schedule.setSecondDivision(division);
        Assert.assertEquals(division, schedule.getSecondDivision());
    }

    @Test
    public void setFirstTeamTest() {
        ISchedule schedule = leagueManagerFactory.createSchedule();
        ITeam team = leagueManagerFactory.createTeam();
        schedule.setFirstTeam(team);
        Assert.assertEquals(team, schedule.getFirstTeam());
    }

    @Test
    public void setSecondTeamTest() {
        ISchedule schedule = leagueManagerFactory.createSchedule();
        ITeam team = leagueManagerFactory.createTeam();
        schedule.setSecondTeam(team);
        Assert.assertEquals(team, schedule.getSecondTeam());
    }

    @Test
    public void setDateTest() {
        ISchedule schedule = leagueManagerFactory.createSchedule();
        LocalDate date = LocalDate.now();
        schedule.setDate(date);
        Assert.assertEquals(date, schedule.getDate());
    }

    @Test
    public void setIsGamePlayedTest() {
        ISchedule schedule = leagueManagerFactory.createSchedule();
        schedule.setIsGamePlayed(true);
        Assert.assertTrue(schedule.getIsGamePlayed());
    }

    @Test
    public void setWinningTeamTest() {
        ISchedule schedule = leagueManagerFactory.createSchedule();
        ITeam team = leagueManagerFactory.createTeam();
        schedule.setWinningTeam(team);
        Assert.assertEquals(team, schedule.getWinningTeam());
    }
}
