package com.IceHockeyLeague.LeagueStandings;

import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.Comparator;

public class Standing implements IStanding {
    private IConference conference;
    private IDivision division;
    private ITeam team;
    private int gamesPlayed;
    private int gamesWon;
    private int points;

    @Override
    public IConference getConference() {
        return conference;
    }

    @Override
    public void setConference(IConference conference) {
        this.conference = conference;
    }

    @Override
    public IDivision getDivision() {
        return division;
    }

    @Override
    public void setDivision(IDivision division) {
        this.division = division;
    }

    @Override
    public ITeam getTeam() {
        return team;
    }

    @Override
    public void setTeam(ITeam team) {
        this.team = team;
    }

    @Override
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    @Override
    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    @Override
    public void incrementGamesPlayed() {
        gamesPlayed += 1;
    }

    @Override
    public int getGamesWon() {
        return gamesWon;
    }

    @Override
    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    @Override
    public void incrementGamesWon() {
        gamesWon += 1;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public void incrementPoints() {
        points += 2;
    }

    public static Comparator<IStanding> standingComparator = (s1, s2) -> {
        int pointsComparison = s2.getPoints() - s1.getPoints();
        int gamesWonComparison = s2.getGamesWon() - s1.getGamesWon();
        int gamesPlayedComparison = s1.getGamesPlayed() - s2.getGamesPlayed();

        if (pointsComparison == 0) {
            if (gamesWonComparison == 0) {
                return gamesPlayedComparison;
            }
            else {
                return gamesWonComparison;
            }
        }

        return pointsComparison;
    };
}
