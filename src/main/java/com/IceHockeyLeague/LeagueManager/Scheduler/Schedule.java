package com.IceHockeyLeague.LeagueManager.Scheduler;

import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.time.LocalDate;

public class Schedule implements ISchedule {
    private IConference firstConference;
    private IDivision firstDivision;
    private ITeam firstTeam;
    private IConference secondConference;
    private IDivision secondDivision;
    private ITeam secondTeam;
    private LocalDate date;
    private boolean isGamePlayed;
    private ITeam winningTeam;

    @Override
    public IConference getFirstConference() {
        return firstConference;
    }

    @Override
    public void setFirstConference(IConference conference) {
        firstConference = conference;
    }

    @Override
    public IConference getSecondConference() {
        return secondConference;
    }

    @Override
    public void setSecondConference(IConference conference) {
        secondConference = conference;
    }

    @Override
    public IDivision getFirstDivision() {
        return firstDivision;
    }

    @Override
    public void setFirstDivision(IDivision division) {
        firstDivision = division;
    }

    @Override
    public IDivision getSecondDivision() {
        return secondDivision;
    }

    @Override
    public void setSecondDivision(IDivision division) {
        secondDivision = division;
    }

    @Override
    public ITeam getFirstTeam() {
        return firstTeam;
    }

    @Override
    public void setFirstTeam(ITeam team) {
        firstTeam = team;
    }

    @Override
    public ITeam getSecondTeam() {
        return secondTeam;
    }

    @Override
    public void setSecondTeam(ITeam team) {
        secondTeam = team;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean getIsGamePlayed() {
        return isGamePlayed;
    }

    @Override
    public void setIsGamePlayed(boolean gamePlayed) {
        isGamePlayed = gamePlayed;
    }

    @Override
    public ITeam getWinningTeam() {
        return winningTeam;
    }

    @Override
    public void setWinningTeam(ITeam winningTeam) {
        this.winningTeam = winningTeam;
    }
}
