package com.IceHockeyLeague.LeagueManager.Standings;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.TrophySystem.ITeamObserver;
import com.TrophySystem.SeasonObserver;
import com.TrophySystem.SeasonSubject;

import java.util.ArrayList;
import java.util.List;

public class StandingSystem extends SeasonSubject implements IStandingSystem{
    private final ILeagueManagerFactory leagueManagerFactory;
    private List<IStanding> standings;
    private ITeamObserver teamObserver;

    public StandingSystem() {
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        teamObserver = new SeasonObserver();
    }

    public List<IStanding> getStandings() {
        return standings;
    }

    public void setStandings(List<IStanding> standingsList) {
        standings = standingsList;
    }

    public void initializeStandings(ILeague league) {
        standings = new ArrayList<>();

        for (IConference conference: league.getConferences()) {
            for (IDivision division: conference.getDivisions()) {
                for (ITeam team: division.getTeams()) {
                    IStanding standing = leagueManagerFactory.createStanding();
                    standing.setConference(conference);
                    standing.setDivision(division);
                    standing.setTeam(team);
                    standings.add(standing);
                }
            }
        }
    }

    public void updateStatsForWinningTeam(IConference conference, IDivision division, ITeam team) {
        for (IStanding standing: standings) {
            if (standing.getConference() == conference &&
                standing.getDivision() == division &&
                standing.getTeam() == team) {
                standing.incrementGamesPlayed();
                standing.incrementGamesWon();
                standing.incrementPoints();
            }
        }
    }

    public void updateStatsForLosingTeam(IConference conference, IDivision division, ITeam team) {
        for (IStanding standing: standings) {
            if (standing.getConference() == conference &&
                    standing.getDivision() == division &&
                    standing.getTeam() == team) {
                standing.incrementGamesPlayed();
            }
        }
    }

    public List<IStanding> getStandingsInDivision(IDivision division) {
        List<IStanding> myStandings = new ArrayList<>();
        for (IStanding standing: standings){
            if (standing.getDivision() == division){
                myStandings.add(standing);
            }
        }
        myStandings.sort(Standing.standingComparator);
        return myStandings;
    }

    public List<IStanding> getStandingsInConference(IConference conference) {
        List<IStanding> myStandings = new ArrayList<>();
        for (IStanding standing: standings){
            if (standing.getConference() == conference){
                myStandings.add(standing);
            }
        }
        myStandings.sort(Standing.standingComparator);
        return myStandings;
    }

    public IStanding getTopStandingInConference(IConference conference) {
        return getStandingsInConference(conference).get(0);
    }

    public List<IStanding> getSortedStandingsInLeague() {
        List<IStanding> myStandings = new ArrayList<>(standings);
        myStandings.sort(Standing.standingComparator);
        this.standingList = myStandings;
        this.attachObserver(teamObserver);
        this.notifyObserver();
        return myStandings;
    }
}
