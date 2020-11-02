package com.IceHockeyLeague.StateMachine.States;

import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.StateMachine.AbstractStateMachineFactory;

public class GeneratePlayoffScheduleState extends AbstractState {

    @Override
    public AbstractState onRun() {
        ILeague league = getLeague();
        league.getScheduleSystem().generatePlayoffSchedule(league, league.getStandingSystem());
        return AbstractStateMachineFactory.getFactory().getTrainingState();
    }

    @Override
    public void welcomeMessage() {

    }
}
