package com.IceHockeyLeagueTest.StateMachineTest;

import com.IO.AbstractIOFactory;
import com.IO.IOFactory;
import com.IceHockeyLeague.LeagueFileHandler.AbstractLeagueFileHandlerFactory;
import com.IceHockeyLeague.LeagueFileHandler.LeagueFileHandlerFactory;
import com.IceHockeyLeague.StateMachine.AbstractStateMachineFactory;
import com.IceHockeyLeague.StateMachine.IStateMachine;
import com.IceHockeyLeague.StateMachine.StateMachineFactory;
import com.IceHockeyLeague.StateMachine.States.AbstractState;
import com.IceHockeyLeague.StateMachine.States.CreateTeamState;
import com.IceHockeyLeague.StateMachine.States.ImportState;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class StateMachineTest {
    private static AbstractStateMachineFactory stateMachineFactory;

    @BeforeClass
    public static void setup() {
        AbstractIOFactory.setFactory(new IOFactory());
        AbstractLeagueFileHandlerFactory.setFactory(new LeagueFileHandlerFactory());
        AbstractStateMachineFactory.setFactory(
                new StateMachineFactory(
                        AbstractIOFactory.getFactory().getCommandLineInput(),
                        AbstractIOFactory.getFactory().getCommandLineOutput(),
                        LeagueFileHandlerFactory.getFactory().getLeagueFileReader(),
                        LeagueFileHandlerFactory.getFactory().getJsonParser(),
                        LeagueFileHandlerFactory.getFactory().getLeagueFileValidator()
                )
        );
        stateMachineFactory = AbstractStateMachineFactory.getFactory();
    }

    @Test
    public void ConstructorTest() {
        AbstractState importState = stateMachineFactory.getImportState();
        IStateMachine stateMachine = stateMachineFactory.getStateMachine(importState);

        Assert.assertTrue(stateMachine.getCurrentState() instanceof ImportState);
    }

    @Test
    public void setCurrentStateTest() {
        AbstractState createTeamState = stateMachineFactory.getCreateTeamState();
        IStateMachine stateMachine = stateMachineFactory.getStateMachine(createTeamState);

        stateMachine.setCurrentState(createTeamState);
        Assert.assertTrue(stateMachine.getCurrentState() instanceof CreateTeamState);
    }

    @Test
    public void getCurrentStateTest() {
        AbstractState importState = stateMachineFactory.getImportState();
        IStateMachine stateMachine = stateMachineFactory.getStateMachine(importState);

        stateMachine.setCurrentState(importState);
        Assert.assertTrue(stateMachine.getCurrentState() instanceof ImportState);
    }

    @Test
    public void onExecutionTest() {
        IStateMachine stateMachine = stateMachineFactory.getStateMachine(null);
        stateMachine.onExecution();
        Assert.assertNull(stateMachine.getCurrentState());
    }
}
