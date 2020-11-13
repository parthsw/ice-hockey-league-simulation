package com.IceHockeyLeague.StateMachine.States;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;

public class GeneratePlayoffScheduleState extends AbstractState {
    private final IStateMachineFactory stateMachineFactory;

    public GeneratePlayoffScheduleState() {
        stateMachineFactory = AbstractAppFactory.getStateMachineFactory();
    }

    @Override
    public AbstractState onRun() {
        ILeague league = getLeague();
        league.getScheduleSystem().generatePlayoffSchedule(league, league.getStandingSystem());
        return stateMachineFactory.createTrainingState();
    }
}
