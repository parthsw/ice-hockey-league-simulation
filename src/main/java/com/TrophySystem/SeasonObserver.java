package com.TrophySystem;

import com.IceHockeyLeague.LeagueManager.Standings.IStanding;

import java.util.List;

public class SeasonObserver implements ITeamObserver {
    @Override
    public void update(List<IStanding> standings) {
        ITrophyContenders awardTrophy = new TrophyDistribution();
        awardTrophy.teamContenders(standings);
    }
}
