package com.IceHockeyLeague.LeagueManager.Scheduler;

import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Standings.IStandingSystem;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.time.LocalDate;
import java.util.List;

public interface IScheduleSystem {
    List<ISchedule> getRegularSchedule();
    void setRegularSchedule(List<ISchedule> regularSchedule);

    List<ISchedule> getPlayoffSchedule();
    void setPlayoffSchedule(List<ISchedule> playoffSchedule);

    LocalDate getRegularSeasonStartDate();
    void setRegularSeasonStartDate(LocalDate regularSeasonStartDate);

    LocalDate getRegularSeasonEndDate();
    void setRegularSeasonEndDate(LocalDate regularSeasonEndDate);

    LocalDate getPlayoffStartDate();
    void setPlayoffStartDate(LocalDate playoffStartDate);

    LocalDate getPlayoffEndDate();
    void setPlayoffEndDate(LocalDate playoffEndDate);

    LocalDate getTradeDeadline();
    void setTradeDeadline(LocalDate tradeDeadline);

    void generateRegularSeasonSchedule(ILeague league);
    void generatePlayoffSchedule(ILeague league, IStandingSystem standingSystem);
    boolean anyUnplayedGamesOnThisDate(LocalDate date);
    ISchedule getScheduledMatchOnThisDate(LocalDate date);
    boolean isStanleyCupWinnerDetermined();
    void updateScheduleAfterGamePlayed(ISchedule schedule);
    ITeam getStanleyCupWinner();
}
