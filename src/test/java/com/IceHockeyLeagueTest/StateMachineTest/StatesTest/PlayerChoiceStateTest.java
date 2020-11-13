package com.IceHockeyLeagueTest.StateMachineTest.StatesTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IOTest.IOMock;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;
import com.IceHockeyLeague.StateMachine.States.AbstractState;
import com.IceHockeyLeague.StateMachine.States.SimulateState;
import org.junit.*;

public class PlayerChoiceStateTest {
    private static IOMock ioMockInstance = null;
    private static IStateMachineFactory stateMachineFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        stateMachineFactory = appFactory.createStateMachineFactory();
        ioMockInstance = IOMock.instance();
    }

    @Before
    public void setupSystemOutput() {
        ioMockInstance.setupSystemOutput();
    }

    @After
    public void resetSystemOutput() {
        ioMockInstance.resetSystemOutput();
        ioMockInstance.resetSystemInput();
    }

    @Test
    public void onRunTest() {
        AbstractState playerChoiceState = stateMachineFactory.createPlayerChoiceState();
        ioMockInstance.commandLineInput("2");

        Assert.assertTrue(playerChoiceState.onRun() instanceof SimulateState);
        Assert.assertTrue(ioMockInstance.getOutput().contains("Please provide the number of seasons to simulate"));
    }
}
