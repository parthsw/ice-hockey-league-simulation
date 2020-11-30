package com.IceHockeyLeague.StateMachine.States;

import com.IO.IAppOutput;
import com.IceHockeyLeague.LeagueManager.Draft.DraftPick.IDraftPick;
import com.IceHockeyLeague.LeagueManager.Draft.IDraftManager;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Player.*;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Team.Roster.ITeamRoster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DraftingState extends AbstractState {
    private static final int DRAFT_ROUNDS = 7;
    private static final String DRAFTING_DAY = "Performing the player drafting on ";
    private static final String PLAYER_GENERATION_START = "Generating random players for the drafting...";
    private static final String PLAYER_GENERATION_END = " players are successfully generated.";
    private static final String DRAFT_ROUND_START = "Starting round no : ";
    private static final String DRAFT_ROUND_END = "Successfully completed drafting for round no : ";
    private static final String TEAM_PICKING_DRAFT = " team performing the draft selection...";
    private static final String ROSTER_VALIDATION = "Validating roster for team :";

    private final Logger LOGGER = LogManager.getLogger(DraftingState.class);
    private final IRandomPlayersGenerator randomPlayersGenerator;
    private final IDraftManager draftManager;
    private final ITeamRoster teamRoster;
    private final IAppOutput appOutput;

    public DraftingState(IRandomPlayersGenerator randomPlayersGenerator, IDraftManager draftManager, ITeamRoster teamRoster, IAppOutput appOutput) {
        this.randomPlayersGenerator = randomPlayersGenerator;
        this.draftManager = draftManager;
        this.teamRoster = teamRoster;
        this.appOutput = appOutput;
    }

    @Override
    public AbstractState onRun() {
        ILeague league = getLeague();
        List<IDraftPick> draftPicks = league.getDraftPicks();
        List<ITeam> teamsForDraftRounds = draftManager.generateTeamOrderForDraftSelection(league);
        int numberOfDrafteesPerRound = teamsForDraftRounds.size();
        int numberOfDrafteesToGenerate = numberOfDrafteesPerRound * DRAFT_ROUNDS;
        LOGGER.info(DRAFTING_DAY + league.getLeagueDate() + " for league " + league.getLeagueName());
        appOutput.display(DRAFTING_DAY + league.getLeagueDate());

        LOGGER.info(PLAYER_GENERATION_START);
        appOutput.display(PLAYER_GENERATION_START);
        List<IPlayer> generatedPlayers = randomPlayersGenerator.generateRandomPlayers(league.getLeagueDate(), numberOfDrafteesToGenerate);
        LOGGER.info(numberOfDrafteesToGenerate + PLAYER_GENERATION_END);
        appOutput.display(numberOfDrafteesToGenerate + PLAYER_GENERATION_END);

        for (int i = 1; i <= DRAFT_ROUNDS; i++) {
            LOGGER.info(DRAFT_ROUND_START + i);
            appOutput.display(DRAFT_ROUND_START + i);

            List<ITeam> teamsForCurrentRound = draftManager.generateTeamOrderForDraftSelection(teamsForDraftRounds, draftPicks, i);
            for (ITeam teamPickingDraftee : teamsForCurrentRound) {
                LOGGER.info(teamPickingDraftee.getTeamName() + TEAM_PICKING_DRAFT);
                appOutput.display(teamPickingDraftee.getTeamName() + TEAM_PICKING_DRAFT);
                draftManager.performDraftSelectionForTeam(teamPickingDraftee, generatedPlayers);
            }

            LOGGER.info(DRAFT_ROUND_END + i);
            appOutput.display(DRAFT_ROUND_END + i);
        }

        league.setDraftPicks(null);

        teamRoster.setAgents(league.getFreeAgents());
        for (ITeam currentTeam : teamsForDraftRounds) {
            LOGGER.info(ROSTER_VALIDATION + currentTeam.getTeamName());
            appOutput.display(ROSTER_VALIDATION + currentTeam.getTeamName());

            teamRoster.setPlayers(currentTeam.getPlayers());
            teamRoster.validateRoster();
            currentTeam.setPlayers(teamRoster.getPlayers());
            teamRoster.setPlayers(null);
        }
        return null;
    }

}
