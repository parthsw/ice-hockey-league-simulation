package com.IceHockeyLeagueTest.StateMachineTest.StatesTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IOTest.IOMock;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;
import com.IceHockeyLeague.StateMachine.States.AbstractState;
import com.IceHockeyLeague.StateMachine.States.CreateTeamState;
import com.IceHockeyLeague.StateMachine.States.ImportState;
import com.IceHockeyLeague.StateMachine.States.LoadTeamState;
import com.IceHockeyLeagueTest.LeagueFileHandlerTest.LeagueJsonMock;
import org.json.JSONObject;
import org.junit.*;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

public class ImportStateTest {
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
        ImportState importState = (ImportState) stateMachineFactory.createImportState();
        importState.welcomeMessage();

        Assert.assertTrue(ioMockInstance.getOutput().contains("*****Import State*****"));
    }

    @Test
    public void onRunTest() {
        AbstractState importState = stateMachineFactory.createImportState();
        ioMockInstance.commandLineInput("empty");

        Assert.assertTrue(importState.onRun() instanceof LoadTeamState);
    }

    @Test
    public void onRunAlternateValidTest() throws IOException {
        AbstractState importState = stateMachineFactory.createImportState();
        File leagueFile = folder.newFile("validLeague.json");
        JSONObject validLeague = LeagueJsonMock.getInstance().validLeagueJson();

        ioMockInstance.commandLineInput(LeagueJsonMock.getInstance().createLeagueJsonFile(leagueFile, validLeague));

        Assert.assertTrue(importState.onRun() instanceof CreateTeamState);
    }

    @Test
    public void onRunAlternateInvalidTest() throws IOException {
        AbstractState importState = stateMachineFactory.createImportState();
        File leagueFile = folder.newFile("invalidLeague.json");
        JSONObject invalidLeague = LeagueJsonMock.getInstance().invalidLeagueJson();

        ioMockInstance.commandLineInput(LeagueJsonMock.getInstance().createLeagueJsonFile(leagueFile, invalidLeague));

        Assert.assertNull(importState.onRun());
        Assert.assertTrue(ioMockInstance.getOutput().contains("#: required key [gameplayConfig] not found"));
    }
}
