package asdc.hl.statemachine.states;

import asdc.hl.database.Persistence;
import asdc.hl.leaguemodel.IPersistence;
import asdc.hl.leaguemodel.models.*;
import asdc.hl.statemachine.StateMachine;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ImportState extends State {

    private String filePath;
    private final String VALID = "VALID";
    private IPersistence dbPersistence;

    public ImportState(StateMachine stateMachine) {
        super(stateMachine);
        this.dbPersistence = new Persistence();
    }

    @Override
    public State onRun() {
        try{
            this.importWelcomeMessage();
            StateUtils.printMessage("Please provide full filepath to the JSON file:");
            filePath = StateUtils.promptForUserInput();
            boolean isFilePathValid = this.isFileExist(new File(filePath));
            return this.importStateExecution(isFilePathValid);
        }
        catch (Exception exception) {
            StateUtils.printErrorMessage(exception.getMessage());
            return null;
        }
    }

    private boolean isFileExist(File file) {
        return file.exists() && file.isFile();
    }

    private State importStateExecution(boolean isFilePathValid) {
        // if valid json file path - continue with JSON parsing, validation, and instantiation
        if(isFilePathValid) {
            // JSON Schema-Structure validation
            JSONObject leagueJSONObject = parseJSON(this.getInputStreamFromFilePath(this.filePath));
            JSONObject leagueJSONSchema = this.parseJSON(this.getInputStreamFromResources("LeagueSchema.json"));
            boolean isValidSchema = this.isValidLeagueJSON(leagueJSONObject, leagueJSONSchema);
            if(isValidSchema == false) {
                return null;
            }

            ILeague league = this.constructLeague(leagueJSONObject);

            // JSON data validation
            List<String> dataValidationErrors = this.isLeagueValid(league);
            if(dataValidationErrors.size() > 0) {
                this.printDataValidationErrors(dataValidationErrors);
                return null;
            }
            stateMachine.setLeague(league);
            return new CreateTeamState(stateMachine);
        }
        else { // transition to load state
            StateUtils.printMessage("Transitioning to Load Team State as filepath is not provided or it is invalid...");
            return new LoadTeamState(stateMachine);
        }
    }

    private List<String> isLeagueValid(ILeague league) {
        List<String> errors = new ArrayList<>();
        try {
            String leagueRulesValidation = league.validateBusinessRules();
            this.addDataValidationErrors(errors, leagueRulesValidation);

            List<IConference> conferences = league.getConferences();
            for(IConference conference: conferences) {
                String conferenceRulesValidation = conference.validateBusinessRules();
                this.addDataValidationErrors(errors, conferenceRulesValidation);

                List<IDivision> divisions = conference.getDivisions();
                for(IDivision division: divisions) {

                    List<ITeam> teams = division.getTeams();
                    for(ITeam team: teams) {
                        String teamRulesValidation = team.validateBusinessRules();
                        this.addDataValidationErrors(errors, teamRulesValidation);
                    }
                }
            }
        }
        catch(Exception e) {
            StateUtils.printErrorMessage(e.getMessage());
        }
        return errors;
    };

    private void addDataValidationErrors(List<String> errors, String errorValidation) {
        if(errorValidation.equalsIgnoreCase(VALID)) { }
        else { errors.add(errorValidation); }
    }

    private void printDataValidationErrors(List<String> errors) {
        for(String error: errors){
            StateUtils.printErrorMessage(error);
        }
    }

    // TODO: Key should be part of a configuration file
    private ILeague constructLeague(JSONObject leagueJSON) {
        ILeague league = new League(dbPersistence);
        league.setLeagueName(leagueJSON.getString("leagueName"));

        // conferences could be empty and that will create an empty ArrayList<IConference>
        ArrayList<IConference> conferences = this.constructConferences(leagueJSON.getJSONArray("conferences"));
        for(IConference conference: conferences) {
            league.addConference(conference);
        }

        // freeAgents could be empty and that will create empty ArrayList<IPlayer>
        ArrayList<IPlayer> freeAgents = this.constructPlayers(leagueJSON.getJSONArray("freeAgents"), PLAYER_TYPE.FREE_AGENT);
        league.setFreeAgents(freeAgents);

        return league;
    }

    private ArrayList<IPlayer> constructPlayers(JSONArray playersJSON, PLAYER_TYPE type) {
        ArrayList<IPlayer> freeAgents = new ArrayList<>();

        for(Object playerJSON: playersJSON) {
            IPlayer freeAgent = new Player(dbPersistence);
            freeAgent.setPlayerName(((JSONObject) playerJSON).getString("playerName"));
            freeAgent.setPlayerPosition(((JSONObject) playerJSON).getString("position"));
            freeAgent.setIsCaptain(((JSONObject) playerJSON).getBoolean("captain"));

            if(type == PLAYER_TYPE.FREE_AGENT) {
                freeAgent.setTeamID(0);
            }

            freeAgents.add(freeAgent); // No need to set teamID or playerID
        }
        return freeAgents;
    }

    private ArrayList<IConference> constructConferences(JSONArray conferencesJSON) {
        ArrayList<IConference> conferences = new ArrayList<>();

        for(Object conferenceJSON: conferencesJSON) {
            IConference conference = new Conference(dbPersistence);

            conference.setConferenceName(((JSONObject) conferenceJSON).getString("conferenceName"));

            ArrayList<IDivision> divisions = this.constructDivisions(((JSONObject) conferenceJSON).getJSONArray("divisions"));
            for(IDivision division: divisions) {
                conference.addDivision(division);
            }

            conferences.add(conference); // No need to set conferenceID and leagueID
        }

        return conferences;
    }

    private ArrayList<IDivision> constructDivisions(JSONArray divisionsJSON) {
        ArrayList<IDivision> divisions = new ArrayList<>();

        for(Object divisionJSON: divisionsJSON) {
            IDivision division = new Division(dbPersistence);

            division.setDivisionName(((JSONObject) divisionJSON).getString("divisionName"));

            ArrayList<ITeam> teams = this.constructTeams(((JSONObject) divisionJSON).getJSONArray("teams"));
            for(ITeam team: teams) {
                division.addTeam(team);
            }

            divisions.add(division); // No need to set divisionID and conferenceID
        }

        return divisions;
    }

    private ArrayList<ITeam> constructTeams(JSONArray teamsJSON) {
        ArrayList<ITeam> teams = new ArrayList<>();

        for(Object teamJSON: teamsJSON) {
            IManager manager = new Manager(dbPersistence);
            manager.setManagerName(((JSONObject) teamJSON).getString("generalManager"));

            ICoach coach = new Coach(dbPersistence);
            coach.setCoachName(((JSONObject) teamJSON).getString("headCoach"));

            ArrayList<IPlayer> players = this.constructPlayers(((JSONObject) teamJSON).getJSONArray("players"), PLAYER_TYPE.PLAYER);

            ITeam team = new Team(dbPersistence);
            team.setTeamName(((JSONObject) teamJSON).getString("teamName"));
            team.addManager(manager);
            team.addCoach(coach);
            for(IPlayer player: players) {
                team.addPlayer(player);
            }

            teams.add(team); // No need to set teamID and divisionID
        }

        return teams;
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

    private void importWelcomeMessage() {
        StateUtils.printMessage("***Import State***");
    }

    enum PLAYER_TYPE {
        PLAYER, FREE_AGENT
    }
}
