package com.IceHockeyLeague.StateMachine.States;

import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.StateMachine.AbstractStateMachineFactory;

import java.time.LocalDate;

public class AdvanceTimeState extends AbstractState {

    @Override
    public AbstractState onRun() {
        AbstractState nextState;
        ILeague league = getLeague();

        league.incrementLeagueDate();

        LocalDate todayDate = league.getLeagueDate();
        LocalDate regularSeasonEndDate = league.getScheduleSystem().getRegularSeasonEndDate();
        if (todayDate.isEqual(regularSeasonEndDate)) {
            nextState = AbstractStateMachineFactory.getFactory().getGeneratePlayoffScheduleState();
        }
        else {
            nextState = AbstractStateMachineFactory.getFactory().getTrainingState();
        }

        return nextState;
    }

    @Override
    public void welcomeMessage() {

    }
}
