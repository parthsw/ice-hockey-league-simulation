package com.IceHockeyLeague.StateMachine.States;

import com.IO.IAppInput;
import com.IO.IAppOutput;

public class CreateTeamState extends AbstractState {

    public CreateTeamState(IAppInput appInput, IAppOutput appOutput) {
this.getLeague();
    }

    @Override
    public AbstractState onRun() {
        return null;
    }

    @Override
    public void welcomeMessage() {

    }
}
