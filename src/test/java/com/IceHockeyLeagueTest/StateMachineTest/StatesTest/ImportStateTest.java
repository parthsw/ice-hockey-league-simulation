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
import com.IceHockeyLeague.StateMachine.States.CreateTeamState;
import com.IceHockeyLeague.StateMachine.States.ImportState;
import com.IceHockeyLeague.StateMachine.States.LoadTeamState;
import com.IceHockeyLeagueTest.LeagueFileHandlerTest.LeagueJsonMock;
import com.IceHockeyLeagueTest.LeagueManagerTest.TestLeagueManagerFactory;
import org.json.JSONObject;
import org.junit.*;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

public class ImportStateTest {
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
        ImportState importState = (ImportState) AbstractStateMachineFactory.getFactory().getImportState();
        importState.welcomeMessage();

        Assert.assertTrue(ioMockInstance.getOutput().contains("*****Import State*****"));
    }

    @Test
    public void onRunTest() {
        AbstractState importState = AbstractStateMachineFactory.getFactory().getImportState();
        ioMockInstance.commandLineInput("empty");

        Assert.assertTrue(importState.onRun() instanceof LoadTeamState);
    }

    @Test
    public void onRunAlternateValidTest() throws IOException {
        AbstractState importState = AbstractStateMachineFactory.getFactory().getImportState();
        File leagueFile = folder.newFile("validLeague.json");
        JSONObject validLeague = LeagueJsonMock.getInstance().validLeagueJson();

        ioMockInstance.commandLineInput(LeagueJsonMock.getInstance().createLeagueJsonFile(leagueFile, validLeague));

        Assert.assertTrue(importState.onRun() instanceof CreateTeamState);
    }

    @Test
    public void onRunAlternateInvalidTest() throws IOException {
        AbstractState importState = AbstractStateMachineFactory.getFactory().getImportState();
        File leagueFile = folder.newFile("invalidLeague.json");
        JSONObject invalidLeague = LeagueJsonMock.getInstance().invalidLeagueJson();

        ioMockInstance.commandLineInput(LeagueJsonMock.getInstance().createLeagueJsonFile(leagueFile, invalidLeague));

        Assert.assertNull(importState.onRun());
        Assert.assertTrue(ioMockInstance.getOutput().contains("#: required key [gameplayConfig] not found"));
    }
}
