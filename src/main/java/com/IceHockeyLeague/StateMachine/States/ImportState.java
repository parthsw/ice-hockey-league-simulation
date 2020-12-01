package com.IceHockeyLeague.StateMachine.States;

import com.AbstractAppFactory;
import com.IO.IAppInput;
import com.IO.IAppOutput;

import com.IceHockeyLeague.LeagueFileHandler.IJsonParser;
import com.IceHockeyLeague.LeagueFileHandler.ILeagueFileReader;
import com.IceHockeyLeague.LeagueFileHandler.ILeagueFileValidator;

import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueCreator;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.io.*;
import java.util.List;

public class ImportState extends AbstractState {
    private static final String IMPORT_STATE = "*****Import State*****";
    private static final String LOAD = "load";
    private static final String FILEPATH = "Please provide full filepath to the league JSON file:";
    private static final String FILEPATH_ERROR = "The provided filepath is incorrect.";
    private static final String LEAGUE_SCHEMA = "LeagueSchema.json";
    private static final String LEAGUE_SCHEMA_SUCCESS = "The provided json file has a valid structure.";
    private static final String LEAGUE_SCHEMA_ERROR = "The provided json file violates below constraint(s):";
    private static final String TRANSITION_LOAD_TEAM = "Transitioning to Load Team State...";

    private final Logger LOGGER = LogManager.getLogger(ImportState.class);
    private final ILeagueManagerFactory leagueManagerFactory;
    private final IStateMachineFactory stateMachineFactory;
    private final IAppInput appInput;
    private final IAppOutput appOutput;
    private final ILeagueFileReader leagueFileReader;
    private final IJsonParser jsonParser;
    private final ILeagueFileValidator leagueFileValidator;

    public ImportState(
            IAppInput appInput,
            IAppOutput appOutput,
            ILeagueFileReader leagueFileReader,
            IJsonParser jsonParser,
            ILeagueFileValidator leagueFileValidator) {
        this.appInput = appInput;
        this.appOutput = appOutput;
        this.leagueFileReader = leagueFileReader;
        this.jsonParser = jsonParser;
        this.leagueFileValidator = leagueFileValidator;
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        stateMachineFactory = AbstractAppFactory.getStateMachineFactory();
    }

    @Override
    public AbstractState onRun() {
        LOGGER.info("User enters into ImportState to process league JSON file...");
        welcomeMessage();
        appOutput.display(FILEPATH);
        String filePath = appInput.getInput();
        LOGGER.info("The league JSON file to process can be found at: " + filePath);
        return processLeagueJsonFile(filePath);
    }

    public void welcomeMessage() {
        appOutput.display(IMPORT_STATE);
    }

    private AbstractState processLeagueJsonFile(String filePath) {
        InputStream leagueJsonStream, leagueSchemaStream;
        JSONObject leagueJson, leagueSchema;

        if (filePath.equalsIgnoreCase(LOAD)) {
            appOutput.displayError(FILEPATH_ERROR);
            appOutput.display(TRANSITION_LOAD_TEAM);
            LOGGER.info("User decides to load an existing team, therefore, transitioning to LoadTeamState...");
            return stateMachineFactory.createLoadTeamState();
        }

        try {
            leagueJsonStream = leagueFileReader.readSystemFile(filePath);
        } catch (FileNotFoundException fileNotFoundException) {
            LOGGER.error("The provided filepath " + filePath + " does not exist");
            LOGGER.error("Assigning smaller league json to InputStream...");
            leagueJsonStream = new ByteArrayInputStream("{\"leagueName\": \"DHL\"}".getBytes());
        }

        LOGGER.info("Parsing the league JSON available at " + filePath);
        leagueJson = jsonParser.parse(leagueJsonStream);

        leagueSchemaStream = leagueFileReader.readAppResourceFile(LEAGUE_SCHEMA);

        LOGGER.info("Parsing the league schema available at " + LEAGUE_SCHEMA);
        leagueSchema = jsonParser.parse(leagueSchemaStream);

        boolean isValidStructure = isStructureValid(leagueJson, leagueSchema);
        if (isValidStructure) {
            ILeagueCreator leagueCreator = leagueManagerFactory.createLeagueCreator();

            LOGGER.info("Constructing league business class from parsed JSON file " + filePath);
            ILeague league = leagueCreator.createLeague(leagueJson);
            this.setLeague(league);

            LOGGER.info("League " + league.getLeagueName() + " has successfully been parsed, therefore, Transitioning to CreateTeamState...");
            return stateMachineFactory.createCreateTeamState();
        } else {
            LOGGER.error("Terminating the app as provided league JSON has schema issues as above...");
            return null;
        }
    }

    private void displaySchemaValidationErrors(List<String> errors) {
        appOutput.displayError(LEAGUE_SCHEMA_ERROR);
        for (String error : errors) {
            LOGGER.error(error);
            appOutput.displayError(error);
        }
    }

    private boolean isStructureValid(JSONObject json, JSONObject schema) {
        List<String> validationResults = leagueFileValidator.validateJsonSchema(json, schema);

        if (validationResults == null) {
            appOutput.display(LEAGUE_SCHEMA_SUCCESS);
            return true;
        } else {
            displaySchemaValidationErrors(validationResults);
            return false;
        }
    }

}
