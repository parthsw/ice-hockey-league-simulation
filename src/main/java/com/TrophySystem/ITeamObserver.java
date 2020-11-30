package com.TrophySystem;

import com.IceHockeyLeague.LeagueManager.Standings.IStanding;
import com.IceHockeyLeague.LeagueManager.Standings.IStandingSystem;

import java.util.List;

public interface ITeamObserver {
    void update(List<IStanding> standings);
}
