package com.IceHockeyLeague.StateMachine.States;

import com.IO.IAppInput;
import com.IO.IAppOutput;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.StateMachine.AbstractStateMachineFactory;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.temporal.TemporalAdjusters;

public class InitializeSeasonState extends AbstractState {

    private final IAppInput appInput;
    private final IAppOutput appOutput;

    public InitializeSeasonState(IAppInput appInput, IAppOutput appOutput) {
        this.appInput = appInput;
        this.appOutput = appOutput;
    }

    @Override
    public AbstractState onRun() {
        ILeague league = this.getLeague();

        LocalDate currentDate = league.getLeagueDate();
        int year;
        if (currentDate == null) {
            year = Year.now().getValue();
        }
        else {
            year = currentDate.getYear();
        }
        currentDate = LocalDate.of(year, Month.SEPTEMBER, 30);
        league.setLeagueDate(currentDate);

        LocalDate temporaryDate;
        temporaryDate = LocalDate.of(year + 1, Month.APRIL, 1);
        LocalDate regularSeasonStartDate = LocalDate.of(year, Month.OCTOBER, 1);
        LocalDate regularSeasonEndDate = temporaryDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.SATURDAY));
        LocalDate playoffStartDate = temporaryDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.WEDNESDAY)).with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
        LocalDate playoffEndDate = LocalDate.of(year + 1, Month.JUNE, 1);

        temporaryDate = LocalDate.of(year + 1, Month.FEBRUARY, 1);
        LocalDate tradeDeadline = temporaryDate.with(TemporalAdjusters.lastInMonth(DayOfWeek.MONDAY));

        league.getScheduleSystem().setRegularSeasonStartDate(regularSeasonStartDate);
        league.getScheduleSystem().setRegularSeasonEndDate(regularSeasonEndDate);
        league.getScheduleSystem().setPlayoffStartDate(playoffStartDate);
        league.getScheduleSystem().setPlayoffEndDate(playoffEndDate);
        league.getScheduleSystem().setTradeDeadline(tradeDeadline);

        league.getScheduleSystem().generateRegularSeasonSchedule(league);
        league.getStandingSystem().initializeStandings(league);

        return AbstractStateMachineFactory.getFactory().getAdvanceTimeState();
    }

    @Override
    public void welcomeMessage() {

    }
}
