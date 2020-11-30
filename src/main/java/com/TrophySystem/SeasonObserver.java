package com.TrophySystem;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.Standings.IStanding;

import java.util.List;

public class SeasonObserver implements ITeamObserver {

    ITrophySystemFactory trophySystemFactory;
    ITrophyContenders awardTrophy;

    public SeasonObserver() {
        trophySystemFactory = AbstractAppFactory.getTrophySystemFactory();
        awardTrophy = trophySystemFactory.trophyDistribution();
    }

    @Override
    public void update(List<IStanding> standings) {
        awardTrophy.teamContenders(standings);
    }
}
