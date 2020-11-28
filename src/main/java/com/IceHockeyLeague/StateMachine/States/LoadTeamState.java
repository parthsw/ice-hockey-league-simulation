package com.IceHockeyLeague.StateMachine.States;

import com.AbstractAppFactory;
//import com.Database.IDatabaseFactory;
import com.IO.IAppInput;
import com.IO.IAppOutput;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
//import com.IceHockeyLeague.LeagueManager.Team.ITeamPersistence;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LoadTeamState extends AbstractState {
    private static final String LOAD_STATE = "*****Load Team State*****";
    private static final String TEAM_LOAD_PROMPT = "Please provide the name of a team you want to load.";
    private static final String TEAM_NAME_EMPTY = "The provided team name is empty.";
    private static final String TEAM_NOT_EXIST = "The provided team name is not available in any persisted leagues.";
    private static final String LEAGUE_SELECTION_PROMPT = "Please enter the ID of a league that you want to load";

    private final ILeagueManagerFactory leagueManagerFactory;
    private final IStateMachineFactory stateMachineFactory;
    private final IAppInput appInput;
    private final IAppOutput appOutput;

    public LoadTeamState(IAppInput appInput, IAppOutput appOutput) {
        this.appInput = appInput;
        this.appOutput = appOutput;
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        stateMachineFactory = AbstractAppFactory.getStateMachineFactory();
    }

    @Override
    public AbstractState onRun() {
        welcomeMessage();
        appOutput.display(TEAM_LOAD_PROMPT);
        return processLeagueLoad();
    }

    public void welcomeMessage() {
        appOutput.display(LOAD_STATE);
    }

    private AbstractState processLeagueLoad() {
        String teamName;
        ITeam team = leagueManagerFactory.createTeam();
        ILeague league = getLeague();
        while (true) {
            teamName = appInput.getInput();
            team.setTeamName(teamName);
            if (team.isNullOrEmpty(teamName)) {
                appOutput.displayError(TEAM_NAME_EMPTY);
                continue;
            }
            boolean flag = true;
            String fileToLoad = teamName + ".json";
            String pathToFile = "";
            File path = new File("serialization_input_output\\");  // C://tejasvi
            File[] files = path.listFiles();
            for (File file: files) {
                if (file.getName().equals(fileToLoad)) {
                    pathToFile = file.getAbsolutePath();
                    league = league.loadCompleteLeague(pathToFile);
                    break;
                } else {
                    flag = false;
                }
            }
            if(flag){
            }
            else{
                appOutput.displayError(TEAM_NOT_EXIST);
                return null;
            }
            this.setLeague(league);
            return stateMachineFactory.createPlayerChoiceState();
        }
    }
}
