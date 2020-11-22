package com.IceHockeyLeague.StateMachine.States;

import com.AbstractAppFactory;
//import com.Database.IDatabaseFactory;
import com.IO.IAppInput;
import com.IO.IAppOutput;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Team.ITeamPersistence;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;

import java.util.ArrayList;
import java.util.List;

public class LoadTeamState extends AbstractState {
    private static final String LOAD_STATE = "*****Load Team State*****";
    private static final String TEAM_LOAD_PROMPT = "Please provide the name of a team you want to load.";
    private static final String TEAM_NAME_EMPTY = "The provided team name is empty.";
    private static final String TEAM_NOT_EXIST = "The provided team name is not available in any persisted leagues.";
    private static final String LEAGUE_SELECTION_PROMPT = "Please enter the ID of a league that you want to load";

    private final ILeagueManagerFactory leagueManagerFactory;
  //  private final IDatabaseFactory databaseFactory;
    private final IStateMachineFactory stateMachineFactory;
    private final IAppInput appInput;
    private final IAppOutput appOutput;

    public LoadTeamState(IAppInput appInput, IAppOutput appOutput) {
        this.appInput = appInput;
        this.appOutput = appOutput;
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
    //    databaseFactory = AbstractAppFactory.getDatabaseFactory();
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

        while(true) {
            teamName = appInput.getInput();
            team.setTeamName(teamName);
            if(team.isNullOrEmpty(teamName)) {
                appOutput.displayError(TEAM_NAME_EMPTY);
                continue;
            }
          //  ITeamPersistence teamDB = databaseFactory.createTeamPersistence();
            List<ILeague> leagueList = new ArrayList<>();
       //     if(team.checkIfTeamNameExists(teamDB, teamName, leagueList)) {
                if(leagueList.size() == 0) {
                    appOutput.displayError(TEAM_NOT_EXIST);
                    return null;
                }
                if(leagueList.size() == 1) {
                    ILeague leagueToLoad = leagueList.get(0);
                    leagueToLoad.loadCompleteLeague(leagueToLoad.getLeagueID());
                    this.setLeague(leagueToLoad);
                }
                else {
                    appOutput.display(LEAGUE_SELECTION_PROMPT);
                    for(ILeague league: leagueList) {
                        appOutput.display("League ID: " + league.getLeagueID() + "& League Name: " + league.getLeagueName());
                    }
                    int leagueId = Integer.parseInt(appInput.getInput());
                    ILeague leagueToLoad = leagueManagerFactory.createLeague();
                    leagueToLoad.loadCompleteLeague(leagueId);
                    this.setLeague(leagueToLoad);
                }
                break;
            }
   //     }

        return stateMachineFactory.createPlayerChoiceState();
    }
}
