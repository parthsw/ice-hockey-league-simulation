package com.IceHockeyLeague.StateMachine;

import com.AbstractAppFactory;
import com.IO.IAppInput;
import com.IO.IAppOutput;

import com.IceHockeyLeague.LeagueFileHandler.IJsonParser;
import com.IceHockeyLeague.LeagueFileHandler.ILeagueFileReader;
import com.IceHockeyLeague.LeagueFileHandler.ILeagueFileValidator;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.IRandomChance;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.StateMachine.States.*;

public class StateMachineFactory implements IStateMachineFactory {
    private final IAppInput appInput;
    private final IAppOutput appOutput;
    private final ILeagueFileReader leagueFileReader;
    private final IJsonParser jsonParser;
    private final ILeagueFileValidator leagueFileValidator;
    private final ILeagueManagerFactory leagueManagerFactory;

    public StateMachineFactory(
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
    }

    @Override
    public IStateMachine createStateMachine(AbstractState abstractState) {
        return new StateMachine(abstractState);
    }

    @Override
    public AbstractState createImportState() {
        return new ImportState(appInput, appOutput, leagueFileReader, jsonParser, leagueFileValidator);
    }

    @Override
    public AbstractState createCreateTeamState() {
        return new CreateTeamState(appInput, appOutput);
    }

    @Override
    public AbstractState createLoadTeamState() {
        return new LoadTeamState(appInput, appOutput);
    }

    @Override
    public AbstractState createPlayerChoiceState() {
        return new PlayerChoiceState(appInput, appOutput);
    }

    @Override
    public AbstractState createSimulateState(int numberOfSeasons) {
        return new SimulateState(appInput, appOutput, numberOfSeasons);
    }

    @Override
    public AbstractState createInitializeSeasonState() {
        return new InitializeSeasonState(appInput, appOutput);
    }

    @Override
    public AbstractState createAdvanceTimeState() {
        return new AdvanceTimeState();
    }

    @Override
    public AbstractState createGeneratePlayoffScheduleState() {
        return new GeneratePlayoffScheduleState(appOutput);
    }

    @Override
    public AbstractState createTrainingState() {
        return new TrainingState();
    }

    @Override
    public AbstractState createSimulateGameState() {
        return new SimulateGameState(appOutput);
    }

    @Override
    public AbstractState createInjuryCheckState(ITeam teamA, ITeam teamB) {
        return new InjuryCheckState(appOutput, teamA, teamB);
    }

    @Override
    public AbstractState createExecuteTradesState() {
        return new ExecuteTradesState();
    }

    @Override
    public AbstractState createAgingState() {
        return new AgingState();
    }

    @Override
    public AbstractState createDraftingState() {
        IRandomChance randomChance = leagueManagerFactory.createRandomChance();
        return new DraftingState(leagueManagerFactory.createRandomPlayersGenerator(randomChance), leagueManagerFactory.createDraftManager(), leagueManagerFactory.createTeamRoster(), appOutput);
    }

    @Override
    public AbstractState createAdvanceToNextSeasonState() {
        IRandomChance randomChance = leagueManagerFactory.createRandomChance();
        return new AdvanceToNextSeasonState(appOutput, leagueManagerFactory.createPlayerCareerProgression(randomChance));
    }

    @Override
    public AbstractState createPersistState() {
        return new PersistState();
    }

    @Override
    public AbstractState createTrophyState() {
        return new TrophyState();
    }

}
