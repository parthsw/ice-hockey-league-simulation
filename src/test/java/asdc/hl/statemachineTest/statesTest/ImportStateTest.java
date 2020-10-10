package asdc.hl.statemachineTest.statesTest;

import asdc.hl.statemachine.StateMachine;
import asdc.hl.statemachine.states.CreateTeamState;
import asdc.hl.statemachine.states.ImportState;
import asdc.hl.statemachine.states.LoadTeamState;
import asdc.hl.statemachine.states.State;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.*;
import static org.hamcrest.CoreMatchers.containsString;

import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class ImportStateTest {
    private static State importState;
    private static StateMachine stateMachine;
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Rule
    public final TextFromStandardInputStream inputMock = emptyStandardInputStream();

//    @BeforeClass
//    public static void setup() {
//        stateMachine = new StateMachine();
//        importState = new ImportState(stateMachine);
//    }
//
//    @Before
//    public void beforeEach() {
//        System.setOut(new PrintStream(outputStream));
//        stateMachine.setState(importState);
//    }
//
//    @Test
//    public void oonRunTest() {
//        inputMock.provideLines("invalidJSONPath");
//        assertTrue(importState.onRun() instanceof LoadTeamState);
//    }
//
//    @Test
//    public void importStateExecutionLoadTeamStateTest() {
//        inputMock.provideLines("invalidJSONPath");
//        assertTrue(importState.onRun() instanceof LoadTeamState);
//    }
//
//    @Test
//    public void importStateExecutionCreateTeamStateTest() throws IOException {
//        inputMock.provideLines(createInvalidLeagueStructureJSONFile());
//        String expectedError = "#: required key [conferences] not found";
//        importState.onRun();
//        assertThat(outputStream.toString(), containsString(expectedError));
//    }
//
//    @Test
//    public void importStateExecutionCreateTeamStateValidTest() throws IOException {
//        inputMock.provideLines(createValidLeagueJSONFile());
//        assertTrue(importState.onRun() instanceof CreateTeamState);
//    }
//
//    @Test
//    public void getInputStreamFromFilePathTest() {
//        inputMock.provideLines("invalidJSONPath");
//        assertTrue(importState.onRun() instanceof LoadTeamState);
//    }
//
//    @Test
//    public void printDataValidationErrorsTest() throws IOException {
//        String h = createInvalidLeagueDataJSONFile();
//        inputMock.provideLines(h);
//        String expectedError = "#: required key [conferences] not found";
//        importState.onRun();
//        assertThat(outputStream.toString(), containsString(expectedError));
//    }
//
//    @After
//    public void afterEach() {
//        outputStream.reset();
//        tempFolder.delete();
//    }

    public String createInvalidLeagueStructureJSONFile() throws IOException {
        File tempFile = tempFolder.newFile("invalidLeague.json");
        FileWriter file = new FileWriter(tempFile.getPath());

        JSONArray freeAgents = new JSONArray();
        freeAgents.add(createPlayer("Agent One", "forward", false));

        JSONObject leagueJSONObject = createLeague("Dalhousie Hockey League", new JSONArray(), freeAgents);
        leagueJSONObject.remove("conferences");

        file.write(leagueJSONObject.toJSONString());
        file.flush();
        return tempFile.getPath();
    }

    public String createInvalidLeagueDataJSONFile() throws IOException {
        File tempFile = tempFolder.newFile("invalidLeagueData.json");
        FileWriter file = new FileWriter(tempFile.getPath());

        JSONArray freeAgents = new JSONArray();
        freeAgents.add(createPlayer("Agent One", "forward", false));

        JSONObject leagueJSONObject = createLeague("Dalhousie Hockey League", new JSONArray(), freeAgents);

        JSONArray divisions = new JSONArray();
        divisions.add(createDivision("Atlantic", new JSONArray()));

        JSONArray conferences = new JSONArray();
        conferences.add(createConference("Eastern Conference", divisions));

        file.write(leagueJSONObject.toJSONString());
        file.flush();
        return tempFile.getPath();
    }

    public String createValidLeagueJSONFile() throws IOException {
        File tempFile = tempFolder.newFile("validLeague.json");
        FileWriter file = new FileWriter(tempFile.getPath());

        JSONArray players = new JSONArray();
        players.add(createPlayer("Player one", "forward", true));
        for(int i=1; i<=19; i++) {
            players.add(createPlayer("Player" + i, "defense", false));
        }

        JSONArray teams = new JSONArray();
        teams.add(createTeam("Halifax", "John Wick", "Leonardo", players));

        JSONArray divisions = new JSONArray();
        divisions.add(createDivision("Atlantic", teams));
        divisions.add(createDivision("Pacific", teams));

        JSONArray conferences = new JSONArray();
        conferences.add(createConference("Eastern", divisions));
        conferences.add(createConference("Western", divisions));

        JSONArray freeAgents = new JSONArray();
        freeAgents.add(createPlayer("Agent One", "goalie", false));

        JSONObject leagueJSONObject = createLeague("Dalhousie Hockey League", conferences, freeAgents);

        file.write(leagueJSONObject.toJSONString());
        file.flush();
        return tempFile.getPath();
    }

    private JSONObject createPlayer(String name, String position, Boolean captain) {
        JSONObject player = new JSONObject();
        player.put("playerName", name);
        player.put("position", position);
        player.put("captain", captain);
        return player;
    }

    private JSONObject createTeam(String name, String generalManager, String headCoach, JSONArray players) {
        JSONObject team = new JSONObject();
        team.put("teamName", name);
        team.put("generalManager", generalManager);
        team.put("headCoach", headCoach);
        team.put("players", players);
        return team;
    }

    private JSONObject createDivision(String name, JSONArray teams) {
        JSONObject division = new JSONObject();
        division.put("divisionName", name);
        division.put("teams", teams);
        return division;
    }

    private JSONObject createConference(String name, JSONArray divisions) {
        JSONObject conference = new JSONObject();
        conference.put("conferenceName", name);
        conference.put("divisions", divisions);
        return conference;
    }

    private JSONObject createLeague(String name, JSONArray conferences, JSONArray freeAgents) {
        JSONObject league = new JSONObject();
        league.put("leagueName", name);
        league.put("conferences", conferences);
        league.put("freeAgents", freeAgents);
        return league;
    }
}
