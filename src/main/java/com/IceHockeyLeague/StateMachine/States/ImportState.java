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
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class ImportState extends AbstractState {
    private static final String IMPORT_STATE = "*****Import State*****";
    private static final String FILEPATH = "Please provide full filepath to the league JSON file:";
    private static final String FILEPATH_ERROR = "The provided filepath is incorrect.";
    private static final String LEAGUE_SCHEMA = "LeagueSchema.json";
    private static final String LEAGUE_SCHEMA_SUCCESS = "The provided json file has a valid structure.";
    private static final String LEAGUE_SCHEMA_ERROR = "The provided json file violates below constraint(s):";
    private static final String TRANSITION_LOAD_TEAM = "Transitioning to Load Team State...";

    private final ILeagueManagerFactory leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
    private final IStateMachineFactory stateMachineFactory = AbstractAppFactory.getStateMachineFactory();
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
    }

    @Override
    public AbstractState onRun() {
        welcomeMessage();
        appOutput.display(FILEPATH);
        String filePath = appInput.getInput();
        return processLeagueJsonFile(filePath);
    }

    public void welcomeMessage() {
        appOutput.display(IMPORT_STATE);
    }

    private AbstractState processLeagueJsonFile(String filePath) {
        try {
            InputStream leagueJsonStream = leagueFileReader.readSystemFile(filePath);
            JSONObject leagueJson = jsonParser.parse(leagueJsonStream);

            InputStream leagueSchemaStream = leagueFileReader.readAppResourceFile(LEAGUE_SCHEMA);
            JSONObject leagueSchema = jsonParser.parse(leagueSchemaStream);

            boolean isValidStructure = isStructureValid(leagueJson, leagueSchema);
            if(isValidStructure) {
                ILeagueCreator leagueCreator = leagueManagerFactory.createLeagueCreator();
                ILeague league = leagueCreator.createLeague(leagueJson);

                this.setLeague(league);
                return stateMachineFactory.createCreateTeamState();
            } else {
                return null;
            }

        } catch (FileNotFoundException fileNotFoundException) {
            appOutput.displayError(FILEPATH_ERROR);
            appOutput.display(TRANSITION_LOAD_TEAM);
            return stateMachineFactory.createLoadTeamState();
        }
    }

    private void displaySchemaValidationErrors(List<String> errors) {
        appOutput.displayError(LEAGUE_SCHEMA_ERROR);
        for (String error : errors) {
            appOutput.displayError(error);
        }
    }

    private boolean isStructureValid(JSONObject json, JSONObject schema) {
        List<String> validationResults = leagueFileValidator.validateJsonSchema(json, schema);

        if(validationResults == null) {
            appOutput.display(LEAGUE_SCHEMA_SUCCESS);
            return true;
        } else {
            displaySchemaValidationErrors(validationResults);
            return false;
        }
    }
}
