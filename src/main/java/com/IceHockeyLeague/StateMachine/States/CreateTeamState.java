package com.IceHockeyLeague.StateMachine.States;

import com.IO.IAppInput;
import com.IO.IAppOutput;
import com.IceHockeyLeague.LeagueManager.League.ILeague;


public class CreateTeamState extends AbstractState {

    public CreateTeamState(IAppInput appInput, IAppOutput appOutput) {

    }

    @Override
    public AbstractState onRun() {

        ILeague l = this.getLeague();
        return null;
    }

    @Override
    public void welcomeMessage() {

    }
}
