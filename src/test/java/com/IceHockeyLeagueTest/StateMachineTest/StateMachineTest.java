package com.IceHockeyLeagueTest.StateMachineTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;
import com.IceHockeyLeague.StateMachine.IStateMachine;
import com.IceHockeyLeague.StateMachine.States.AbstractState;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class StateMachineTest {
    private static IStateMachineFactory stateMachineFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        stateMachineFactory = appFactory.createStateMachineFactory();
    }

    @Test
    public void ConstructorTest() {
        AbstractState importState = stateMachineFactory.createImportState();
        IStateMachine stateMachine = stateMachineFactory.createStateMachine(importState);
        Assert.assertEquals(importState, stateMachine.getCurrentState());
    }

    @Test
    public void setCurrentStateTest() {
        AbstractState createTeamState = stateMachineFactory.createCreateTeamState();
        IStateMachine stateMachine = stateMachineFactory.createStateMachine(createTeamState);
        stateMachine.setCurrentState(createTeamState);
        Assert.assertEquals(createTeamState, stateMachine.getCurrentState());
    }

    @Test
    public void getCurrentStateTest() {
        AbstractState importState = stateMachineFactory.createImportState();
        IStateMachine stateMachine = stateMachineFactory.createStateMachine(importState);
        stateMachine.setCurrentState(importState);
        Assert.assertEquals(importState, stateMachine.getCurrentState());
    }

    @Test
    public void onExecutionTest() {
        IStateMachine stateMachine = stateMachineFactory.createStateMachine(null);
        stateMachine.onExecution();
        Assert.assertNull(stateMachine.getCurrentState());
    }

}
