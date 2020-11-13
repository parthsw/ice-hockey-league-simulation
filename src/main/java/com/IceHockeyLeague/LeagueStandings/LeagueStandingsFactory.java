package com.IceHockeyLeague.LeagueStandings;

public class LeagueStandingsFactory implements ILeagueStandingsFactory {

    @Override
    public IStanding createStanding() {
        return new Standing();
    }

    @Override
    public IStandingSystem createStandingSystem() {
        return new StandingSystem();
    }
}
