package com.IceHockeyLeague.LeagueScheduler;

public class LeagueSchedulerFactory implements ILeagueSchedulerFactory {

    @Override
    public ISchedule createSchedule() {
        return new Schedule();
    }

    @Override
    public IScheduleSystem createScheduleSystem() {
        return new ScheduleSystem();
    }
}
