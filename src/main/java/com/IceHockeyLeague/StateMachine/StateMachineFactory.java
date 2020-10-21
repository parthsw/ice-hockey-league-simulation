package com.IceHockeyLeague.StateMachine;

import com.IO.AbstractIOFactory;
import com.IO.IAppInput;
import com.IO.IAppOutput;

import com.IceHockeyLeague.StateMachine.States.*;

public class StateMachineFactory extends AbstractStateMachineFactory {
    private IAppInput appInput;
    private IAppOutput appOutput;

    public StateMachineFactory() {
        appInput = AbstractIOFactory.getFactory().getCommandLineInput();
        appOutput = AbstractIOFactory.getFactory().getCommandLineOutput();
    }

    @Override
    public IStateMachine getStateMachine(AbstractState abstractState) {
        return new StateMachine(abstractState);
    }

    @Override
    public AbstractState getImportState() {
        return new ImportState(appInput, appOutput);
    }

    @Override
    public AbstractState getCreateTeamState() {
        return new CreateTeamState(appInput, appOutput);
    }

    @Override
    public AbstractState getLoadTeamState() {
        return new LoadTeamState(appInput, appOutput);
    }

    @Override
    public AbstractState getPlayerChoiceState() {
        return new PlayerChoiceState(appInput, appOutput);
    }
}
