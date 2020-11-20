package com.IceHockeyLeague.LeagueManager.Standings;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Scheduler.ISchedule;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StandingSystem implements IStandingSystem{
    private final ILeagueManagerFactory leagueManagerFactory;
    private List<IStanding> standings;

    public StandingSystem() {
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
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

    public List<IStanding> getRegularSeasonStandingsInReverse() {
        standings.sort(Standing.standingComparator.reversed());
        return standings;
    }

    public List<IStanding> getPlayOffSeasonStandingsInReverse(List<ISchedule> playoffSchedule) {
        playoffSchedule.sort(Comparator.comparing(ISchedule::getDate));
        List<IStanding> playOffStandings = new ArrayList<>();
        int numberOfSchedules = playoffSchedule.size();

        for(int i = 0; i < numberOfSchedules; i++) {
            IStanding standing, lastStanding;
            ISchedule currentSchedule = playoffSchedule.get(i);
            if(currentSchedule.getWinningTeam() == currentSchedule.getFirstTeam()) {
                standing = setStanding(currentSchedule.getSecondConference(), currentSchedule.getSecondDivision(), currentSchedule.getSecondTeam());
            }
            else {
                standing = setStanding(currentSchedule.getFirstConference(), currentSchedule.getFirstDivision(), currentSchedule.getFirstTeam());
            }
            playOffStandings.add(standing);

            if(i == numberOfSchedules - 1) {
                if ((currentSchedule.getWinningTeam() == currentSchedule.getFirstTeam())) {
                    lastStanding = setStanding(currentSchedule.getFirstConference(), currentSchedule.getFirstDivision(), currentSchedule.getFirstTeam());
                }
                else {
                    lastStanding = setStanding(currentSchedule.getSecondConference(), currentSchedule.getSecondDivision(), currentSchedule.getSecondTeam());
                }
                playOffStandings.add(lastStanding);
            }
        }
        return playOffStandings;
    }

    private IStanding setStanding(IConference conference, IDivision division, ITeam team) {
        IStanding standing = leagueManagerFactory.createStanding();
        standing.setConference(conference);
        standing.setDivision(division);
        standing.setTeam(team);
        return standing;
    }
}
