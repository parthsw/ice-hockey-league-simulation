package com.IceHockeyLeague.LeagueScheduler;

public interface ILeagueSchedulerFactory {
    ISchedule createSchedule();
    IScheduleSystem createScheduleSystem();
}
