package asdc.hl.statemachine.states;

import asdc.hl.statemachine.StateMachine;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.List;

public class ImportState extends State {

    private String filePath;

    public ImportState(StateMachine stateMachine) {
        super(stateMachine);
    }

    @Override
    public State onRun() {
        StateUtils.printMessage("Please provide full filepath to the JSON file:");
        filePath = StateUtils.promptForUserInput();
        boolean isFilePathValid = this.isFileExist(new File(filePath));
        return this.importStateExecution(isFilePathValid);
    }

    private boolean isFileExist(File file) {
        return file.exists() && file.isFile();
    }

    private State importStateExecution(boolean isFilePathValid) {
        // if valid json file path - continue with JSON parsing, validation, and instantiation
        if(isFilePathValid) {
            JSONObject leagueJSONObject = parseJSON(this.getInputStreamFromFilePath(this.filePath));
            JSONObject leagueJSONSchema = this.parseJSON(this.getInputStreamFromResources("LeagueSchema.json"));
            boolean isValidSchema = this.isValidLeagueJSON(leagueJSONObject, leagueJSONSchema);
            if(isValidSchema == false) {
                return null;
            }
            // TODO:
            // 1. Perform data validation on league json data
            // 2. If valid - instantiate the league data object model - set to stateMachine
            // stateMachine.setLeague();
            // 2.1. Call the database to check whether the given league exist or not
            // 2.2. If exist, Show error and terminate, otherwise move further
            // 3. If invalid - throw error and abort
            return new CreateTeamState(stateMachine);
        }
        else { // transition to load state
            return new LoadTeamState(stateMachine);
        }

    }

    private InputStream getInputStreamFromFilePath(String filePath) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filePath);
        }
        catch(FileNotFoundException fileNotFoundException) {
            StateUtils.printErrorMessage(fileNotFoundException.getMessage());
        }
        return inputStream;
    }

    private InputStream getInputStreamFromResources(String fileName)  {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        return inputStream;
    }

    private JSONObject parseJSON(InputStream inputStream) {
        JSONObject jsonObject;
        jsonObject = new JSONObject(new JSONTokener(inputStream));
        return jsonObject;
    }

    private boolean isValidLeagueJSON(JSONObject jsonObject, JSONObject jsonSchema)  {
        try {
            Schema schema = SchemaLoader.load(jsonSchema);
            schema.validate(jsonObject);
        }
        catch (ValidationException validationException) {
            System.out.println(validationException.getMessage());
            this.enumerateStructuralErrors(validationException.getAllMessages());
            return false;
        }
        return true;
    }

    private void enumerateStructuralErrors(List<String> structuralErrors) {
        for(String structuralError: structuralErrors) {
            System.out.println(structuralError);
        }
    }
}
