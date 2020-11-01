package com.IceHockeyLeagueTest.LeagueSchedulerTest;

import com.IceHockeyLeague.LeagueManager.Conference.Conference;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.Division;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Team.Team;
import com.IceHockeyLeague.LeagueScheduler.ISchedule;
import com.IceHockeyLeague.LeagueScheduler.Schedule;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class ScheduleTest {

    @Test
    public void setFirstConferenceTest() {
        ISchedule schedule = new Schedule();
        IConference conference = new Conference();
        schedule.setFirstConference(conference);
        Assert.assertEquals(conference, schedule.getFirstConference());
    }

    @Test
    public void setSecondConferenceTest() {
        ISchedule schedule = new Schedule();
        IConference conference = new Conference();
        schedule.setSecondConference(conference);
        Assert.assertEquals(conference, schedule.getSecondConference());
    }

    @Test
    public void setFirstDivisionTest() {
        ISchedule schedule = new Schedule();
        IDivision division = new Division();
        schedule.setFirstDivision(division);
        Assert.assertEquals(division, schedule.getFirstDivision());
    }

    @Test
    public void setSecondDivisionTest() {
        ISchedule schedule = new Schedule();
        IDivision division = new Division();
        schedule.setSecondDivision(division);
        Assert.assertEquals(division, schedule.getSecondDivision());
    }

    @Test
    public void setFirstTeamTest() {
        ISchedule schedule = new Schedule();
        ITeam team = new Team();
        schedule.setFirstTeam(team);
        Assert.assertEquals(team, schedule.getFirstTeam());
    }

    @Test
    public void setSecondTeamTest() {
        ISchedule schedule = new Schedule();
        ITeam team = new Team();
        schedule.setSecondTeam(team);
        Assert.assertEquals(team, schedule.getSecondTeam());
    }

    @Test
    public void setDateTest() {
        ISchedule schedule = new Schedule();
        LocalDate date = LocalDate.now();
        schedule.setDate(date);
        Assert.assertEquals(date, schedule.getDate());
    }

    @Test
    public void setIsGamePlayedTest() {
        ISchedule schedule = new Schedule();
        schedule.setIsGamePlayed(true);
        Assert.assertTrue(schedule.getIsGamePlayed());
    }

    @Test
    public void setWinningTeamTest() {
        ISchedule schedule = new Schedule();
        ITeam team = new Team();
        schedule.setWinningTeam(team);
        Assert.assertEquals(team, schedule.getWinningTeam());
    }
}
