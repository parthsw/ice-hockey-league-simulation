package com.IceHockeyLeague.StateMachine.States;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;

public class GeneratePlayoffScheduleState extends AbstractState {

    @Override
    public AbstractState onRun() {
        IStateMachineFactory stateMachineFactory = AbstractAppFactory.getStateMachineFactory();
        ILeague league = getLeague();
        league.getScheduleSystem().generatePlayoffSchedule(league, league.getStandingSystem());
        return stateMachineFactory.createTrainingState();
    }
}
