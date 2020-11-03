package com.IceHockeyLeague.StateMachine;

import com.IO.IAppInput;
import com.IO.IAppOutput;

import com.IceHockeyLeague.LeagueFileHandler.IJsonParser;
import com.IceHockeyLeague.LeagueFileHandler.ILeagueFileReader;
import com.IceHockeyLeague.LeagueFileHandler.ILeagueFileValidator;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.StateMachine.States.*;

public class StateMachineFactory extends AbstractStateMachineFactory {
    private final IAppInput appInput;
    private final IAppOutput appOutput;
    private final ILeagueFileReader leagueFileReader;
    private final IJsonParser jsonParser;
    private final ILeagueFileValidator leagueFileValidator;

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
    }

    @Override
    public IStateMachine getStateMachine(AbstractState abstractState) {
        return new StateMachine(abstractState);
    }

    @Override
    public AbstractState getImportState() {
        return new ImportState(appInput, appOutput, leagueFileReader, jsonParser, leagueFileValidator);
    }

    @Override
    public AbstractState getCreateTeamState() {
        return new CreateTeamState(appInput, appOutput);
    }

    @Override
    public AbstractState getLoadTeamState() {
        return new LoadTeamState(appInput, appOutput);
    }

    @Override
    public AbstractState getPlayerChoiceState() {
        return new PlayerChoiceState(appInput, appOutput);
    }

    @Override
    public AbstractState getSimulateState(int numberOfSeasons) {
        return new SimulateState(appInput, appOutput, numberOfSeasons);
    }

    @Override
    public AbstractState getInitializeSeasonState() {
        return new InitializeSeasonState(appInput, appOutput);
    }

    @Override
    public AbstractState getAdvanceTimeState() {
        return new AdvanceTimeState();
    }

    @Override
    public AbstractState getGeneratePlayoffScheduleState() {
        return new GeneratePlayoffScheduleState();
    }

    @Override
    public AbstractState getTrainingState() {
        return new TrainingState();
    }

    @Override
    public AbstractState getSimulateGameState() {
        return new SimulateGameState(appOutput);
    }

    @Override
    public AbstractState getInjuryCheckState(ITeam teamA, ITeam teamB) {
        return new InjuryCheckState(appOutput, teamA, teamB);
    }

    @Override
    public AbstractState getExecuteTradesState() {
        return new ExecuteTradesState();
    }

    @Override
    public AbstractState getAgingState() {
        return new AgingState();
    }

    @Override
    public AbstractState getAdvanceToNextSeasonState() {
        return new AdvanceToNextSeasonState(appOutput);
    }

    @Override
    public AbstractState getPersistState() {
        return new PersistState();
    }
}
