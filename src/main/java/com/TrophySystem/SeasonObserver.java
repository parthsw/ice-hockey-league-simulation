package com.TrophySystem;

import com.IceHockeyLeague.LeagueManager.Standings.IStanding;
import com.IceHockeyLeague.LeagueManager.Standings.IStandingSystem;
import com.TrophySystem.AwardCeremony;
import com.TrophySystem.ITeamObserver;
import com.TrophySystem.ITrophyNominees;

import java.util.List;

public class SeasonObserver implements ITeamObserver {
    @Override
    public void update(List<IStanding> standings) {
        ITrophyNominees awardTrophy = new AwardCeremony();
        awardTrophy.teamNominees(standings);
    }


    /*@Override
    public void update(List<IStanding> standings) {
        ITrophyNominees awardTrophy = new AwardCeremony();
        awardTrophy.teamNominees(standings);
    }*/
}
