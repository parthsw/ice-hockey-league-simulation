package com.IceHockeyLeague.LeagueStandings;

import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.List;

public interface IStandingSystem {
    List<IStanding> getStandings();
    void setStandings(List<IStanding> standingsList);
    void initializeStandings(ILeague league);
    void updateStatsForWinningTeam(IConference conference, IDivision division, ITeam team);
    void updateStatsForLosingTeam(IConference conference, IDivision division, ITeam team);
    List<IStanding> getStandingsInDivision(IDivision division);
    List<IStanding> getStandingsInConference(IConference conference);
    IStanding getTopStandingInConference(IConference conference);
}
