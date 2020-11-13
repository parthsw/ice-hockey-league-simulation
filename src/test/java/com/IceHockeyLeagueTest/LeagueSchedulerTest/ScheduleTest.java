package com.IceHockeyLeagueTest.LeagueSchedulerTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueScheduler.ILeagueSchedulerFactory;
import com.IceHockeyLeague.LeagueScheduler.ISchedule;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;

public class ScheduleTest {
    private static ILeagueManagerFactory leagueManagerFactory;
    private static ILeagueSchedulerFactory leagueSchedulerFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        AbstractAppFactory.setLeagueSchedulerFactory(appFactory.createLeagueSchedulerFactory());
        leagueSchedulerFactory = AbstractAppFactory.getLeagueSchedulerFactory();
    }

    @Test
    public void setFirstConferenceTest() {
        ISchedule schedule = leagueSchedulerFactory.createSchedule();
        IConference conference = leagueManagerFactory.createConference();
        schedule.setFirstConference(conference);
        Assert.assertEquals(conference, schedule.getFirstConference());
    }

    @Test
    public void setSecondConferenceTest() {
        ISchedule schedule = leagueSchedulerFactory.createSchedule();
        IConference conference = leagueManagerFactory.createConference();
        schedule.setSecondConference(conference);
        Assert.assertEquals(conference, schedule.getSecondConference());
    }

    @Test
    public void setFirstDivisionTest() {
        ISchedule schedule = leagueSchedulerFactory.createSchedule();
        IDivision division = leagueManagerFactory.createDivision();
        schedule.setFirstDivision(division);
        Assert.assertEquals(division, schedule.getFirstDivision());
    }

    @Test
    public void setSecondDivisionTest() {
        ISchedule schedule = leagueSchedulerFactory.createSchedule();
        IDivision division = leagueManagerFactory.createDivision();
        schedule.setSecondDivision(division);
        Assert.assertEquals(division, schedule.getSecondDivision());
    }

    @Test
    public void setFirstTeamTest() {
        ISchedule schedule = leagueSchedulerFactory.createSchedule();
        ITeam team = leagueManagerFactory.createTeam();
        schedule.setFirstTeam(team);
        Assert.assertEquals(team, schedule.getFirstTeam());
    }

    @Test
    public void setSecondTeamTest() {
        ISchedule schedule = leagueSchedulerFactory.createSchedule();
        ITeam team = leagueManagerFactory.createTeam();
        schedule.setSecondTeam(team);
        Assert.assertEquals(team, schedule.getSecondTeam());
    }

    @Test
    public void setDateTest() {
        ISchedule schedule = leagueSchedulerFactory.createSchedule();
        LocalDate date = LocalDate.now();
        schedule.setDate(date);
        Assert.assertEquals(date, schedule.getDate());
    }

    @Test
    public void setIsGamePlayedTest() {
        ISchedule schedule = leagueSchedulerFactory.createSchedule();
        schedule.setIsGamePlayed(true);
        Assert.assertTrue(schedule.getIsGamePlayed());
    }

    @Test
    public void setWinningTeamTest() {
        ISchedule schedule = leagueSchedulerFactory.createSchedule();
        ITeam team = leagueManagerFactory.createTeam();
        schedule.setWinningTeam(team);
        Assert.assertEquals(team, schedule.getWinningTeam());
    }
}
