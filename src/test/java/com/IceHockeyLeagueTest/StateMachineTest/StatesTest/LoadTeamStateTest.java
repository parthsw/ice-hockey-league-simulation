package com.IceHockeyLeagueTest.StateMachineTest.StatesTest;

import com.IO.AbstractIOFactory;
import com.IO.IOFactory;
import com.IOTest.IOMock;
import com.IceHockeyLeague.LeagueFileHandler.AbstractLeagueFileHandlerFactory;
import com.IceHockeyLeague.LeagueFileHandler.LeagueFileHandlerFactory;
import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.StateMachine.AbstractStateMachineFactory;
import com.IceHockeyLeague.StateMachine.StateMachineFactory;
import com.IceHockeyLeague.StateMachine.States.AbstractState;
import com.IceHockeyLeague.StateMachine.States.LoadTeamState;
import com.IceHockeyLeague.StateMachine.States.PlayerChoiceState;
import com.IceHockeyLeagueTest.LeagueManagerTest.TestLeagueManagerFactory;
import org.junit.*;
import org.junit.rules.TemporaryFolder;

public class LoadTeamStateTest {
    private static IOMock ioMockInstance = null;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

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
        AbstractLeagueManagerFactory.setFactory(new TestLeagueManagerFactory());
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
        LoadTeamState loadTeamState = (LoadTeamState) AbstractStateMachineFactory.getFactory().getLoadTeamState();
        loadTeamState.welcomeMessage();

        Assert.assertTrue(ioMockInstance.getOutput().contains("*****Load Team State*****"));
    }

    @Test
    public void onRunTest() {
        AbstractState loadTeamState = AbstractStateMachineFactory.getFactory().getLoadTeamState();
        ioMockInstance.commandLineInput("Boston");

        Assert.assertTrue(loadTeamState.onRun() instanceof PlayerChoiceState);
    }

}
