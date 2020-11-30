package com.IceHockeyLeague.LeagueManager.Standings;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Scheduler.ISchedule;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.TrophySystem.ITeamObserver;
import com.TrophySystem.ITrophySystemFactory;
import com.TrophySystem.SeasonSubject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StandingSystem extends SeasonSubject implements IStandingSystem{
    private final ILeagueManagerFactory leagueManagerFactory;
    private final ITrophySystemFactory trophySystemFactory;
    private List<IStanding> standings;
    private ITeamObserver teamObserver;

    public StandingSystem() {
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        trophySystemFactory = AbstractAppFactory.getTrophySystemFactory();
        teamObserver = trophySystemFactory.seasonObserver();
    }

    @Override
    public List<IStanding> getStandings() {
        return standings;
    }

    @Override
    public void setStandings(List<IStanding> standings) {
        this.standings = standings;
    }

    @Override
    public void initializeStandings(ILeague league) {
        standings = new ArrayList<>();

        for (IConference conference : league.getConferences()) {
            for (IDivision division : conference.getDivisions()) {
                for (ITeam team : division.getTeams()) {
                    IStanding standing = leagueManagerFactory.createStanding();
                    standing.setConference(conference);
                    standing.setDivision(division);
                    standing.setTeam(team);
                    standings.add(standing);
                }
            }
        }
    }

    @Override
    public void updateStatsForWinningTeam(IConference conference, IDivision division, ITeam team) {
        for (IStanding standing : standings) {
            if (standing.getConference() == conference &&
                    standing.getDivision() == division &&
                    standing.getTeam() == team) {
                standing.incrementGamesPlayed();
                standing.incrementGamesWon();
                standing.incrementPoints();
            }
        }
    }

    @Override
    public void updateStatsForLosingTeam(IConference conference, IDivision division, ITeam team) {
        for (IStanding standing : standings) {
            if (standing.getConference() == conference &&
                    standing.getDivision() == division &&
                    standing.getTeam() == team) {
                standing.incrementGamesPlayed();
            }
        }
    }

    @Override
    public List<IStanding> getStandingsInDivision(IDivision division) {
        List<IStanding> myStandings = new ArrayList<>();
        for (IStanding standing : standings) {
            if (standing.getDivision() == division) {
                myStandings.add(standing);
            }
        }
        myStandings.sort(Standing.standingComparator);
        return myStandings;
    }

    @Override
    public List<IStanding> getStandingsInConference(IConference conference) {
        List<IStanding> myStandings = new ArrayList<>();
        for (IStanding standing : standings) {
            if (standing.getConference() == conference) {
                myStandings.add(standing);
            }
        }
        myStandings.sort(Standing.standingComparator);
        return myStandings;
    }

    @Override
    public IStanding getTopStandingInConference(IConference conference) {
        return getStandingsInConference(conference).get(0);
    }

    @Override
    public List<IStanding> getRegularSeasonStandingsInReverse() {
        standings.sort(Standing.standingComparator.reversed());
        return standings;
    }

    @Override
    public List<IStanding> getPlayOffSeasonStandingsInReverse(List<ISchedule> playoffSchedule) {
        List<IStanding> playOffStandings = new ArrayList<>();
        int numberOfSchedules = playoffSchedule.size();
        playoffSchedule.sort(Comparator.comparing(ISchedule::getDate));

        for (int i = 0; i < numberOfSchedules; i++) {
            IStanding standing, lastStanding;
            ISchedule currentSchedule = playoffSchedule.get(i);
            if (currentSchedule.getWinningTeam() == currentSchedule.getFirstTeam()) {
                standing = setStanding(currentSchedule.getSecondConference(), currentSchedule.getSecondDivision(), currentSchedule.getSecondTeam());
            } else {
                standing = setStanding(currentSchedule.getFirstConference(), currentSchedule.getFirstDivision(), currentSchedule.getFirstTeam());
            }
            playOffStandings.add(standing);

            if (i == numberOfSchedules - 1) {
                if ((currentSchedule.getWinningTeam() == currentSchedule.getFirstTeam())) {
                    lastStanding = setStanding(currentSchedule.getFirstConference(), currentSchedule.getFirstDivision(), currentSchedule.getFirstTeam());
                } else {
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


    public List<IStanding> getSortedStandingsInLeague() {
        List<IStanding> myStandings = new ArrayList<>(standings);
        myStandings.sort(Standing.standingComparator);
        this.standingList = myStandings;
        this.attachObserver(teamObserver);
        this.notifyObserver();
        return myStandings;
    }
}
