package com.IceHockeyLeagueTest.StateMachineTest.StatesTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IOTest.IOMock;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;
import com.IceHockeyLeague.StateMachine.States.AbstractState;
import com.IceHockeyLeague.StateMachine.States.ImportState;
import com.IceHockeyLeagueTest.LeagueFileHandlerTest.LeagueJsonMock;
import org.json.JSONObject;
import org.junit.*;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

public class ImportStateTest {
    private static final String LOAD_TEAM_STATE = "LoadTeamState";
    private static final String CREATE_TEAM_STATE = "CreateTeamState";
    private static IOMock ioMockInstance = null;
    private static IStateMachineFactory stateMachineFactory;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        AbstractAppFactory.setStateMachineFactory(appFactory.createStateMachineFactory());
        AbstractAppFactory.setTrophySystemFactory(appFactory.createTrophySystemFactory());
        stateMachineFactory = AbstractAppFactory.getStateMachineFactory();
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
        AbstractState nextState;

        ioMockInstance.commandLineInput("load");
        nextState = importState.onRun();
        Assert.assertEquals(nextState.getClass().getSimpleName(), LOAD_TEAM_STATE);
    }

    @Test
    public void onRunAlternateValidTest() throws IOException {
        AbstractState importState = stateMachineFactory.createImportState();
        AbstractState nextState;
        File leagueFile = folder.newFile("validLeague.json");
        JSONObject validLeague = LeagueJsonMock.instance().validLeagueJson();

        ioMockInstance.commandLineInput(LeagueJsonMock.instance().createLeagueJsonFile(leagueFile, validLeague));
        nextState = importState.onRun();
        Assert.assertEquals(nextState.getClass().getSimpleName(), CREATE_TEAM_STATE);
    }

    @Test
    public void onRunInvalidTest() {
        AbstractState importState = stateMachineFactory.createImportState();
        ioMockInstance.commandLineInput("file.json");
        Assert.assertNull(importState.onRun());
    }

    @Test
    public void onRunAlternateInvalidTest() throws IOException {
        AbstractState importState = stateMachineFactory.createImportState();
        File leagueFile = folder.newFile("invalidLeague.json");
        JSONObject invalidLeague = LeagueJsonMock.instance().invalidLeagueJson();

        ioMockInstance.commandLineInput(LeagueJsonMock.instance().createLeagueJsonFile(leagueFile, invalidLeague));
        Assert.assertNull(importState.onRun());
        Assert.assertTrue(ioMockInstance.getOutput().contains("#: required key [gameplayConfig] not found"));
    }

}
