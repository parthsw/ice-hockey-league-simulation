package com.IceHockeyLeagueTest.StateMachineTest.StatesTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IOTest.IOMock;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;
import com.IceHockeyLeague.StateMachine.States.AbstractState;
import com.IceHockeyLeague.StateMachine.States.LoadTeamState;
import com.IceHockeyLeague.StateMachine.States.PlayerChoiceState;
import org.junit.*;
import org.junit.rules.TemporaryFolder;

public class LoadTeamStateTest {
    private static IOMock ioMockInstance = null;
    private static IStateMachineFactory stateMachineFactory;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

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
    public void welcomeMessageTest() {
        LoadTeamState loadTeamState = (LoadTeamState) stateMachineFactory.createLoadTeamState();
        loadTeamState.welcomeMessage();

        Assert.assertTrue(ioMockInstance.getOutput().contains("*****Load Team State*****"));
    }

    @Test
    public void onRunTest() {
        AbstractState loadTeamState = stateMachineFactory.createLoadTeamState();
        ioMockInstance.commandLineInput("Boston");

        Assert.assertTrue(loadTeamState.onRun() instanceof PlayerChoiceState);
    }

}
