package com.IceHockeyLeague.LeagueScheduler;

import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.time.LocalDate;

public interface ISchedule {
    IConference getFirstConference();
    void setFirstConference(IConference conference);

    IConference getSecondConference();
    void setSecondConference(IConference conference);

    IDivision getFirstDivision();
    void setFirstDivision(IDivision division);

    IDivision getSecondDivision();
    void setSecondDivision(IDivision division);

    ITeam getFirstTeam();
    void setFirstTeam(ITeam team);

    ITeam getSecondTeam();
    void setSecondTeam(ITeam team);

    LocalDate getDate();
    void setDate(LocalDate date);

    boolean getIsGamePlayed();
    void setIsGamePlayed(boolean gamePlayed);

    ITeam getWinningTeam();
    void setWinningTeam(ITeam winningTeam);
}
