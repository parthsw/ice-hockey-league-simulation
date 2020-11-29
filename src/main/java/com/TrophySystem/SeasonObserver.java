package com.TrophySystem;

import com.IceHockeyLeague.LeagueManager.Standings.IStanding;
import com.IceHockeyLeague.LeagueManager.Standings.IStandingSystem;
import com.TrophySystem.AwardCeremony;
import com.TrophySystem.ITeamObserver;
import com.TrophySystem.ITrophyNominees;

import java.util.List;

public class SeasonObserver implements ITeamObserver {
    IStandingSystem standingSystem;

    @Override
    public void update(IStandingSystem standingSystem) {
        ITrophyNominees awardTrophy = new AwardCeremony();
        awardTrophy.teamNominees(standingSystem);
    }
}
