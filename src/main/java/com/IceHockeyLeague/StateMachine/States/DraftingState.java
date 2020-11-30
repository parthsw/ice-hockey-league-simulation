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

        LOGGER.info("Generating " + numberOfDrafteesToGenerate + " random players for the drafting...");
        List<IPlayer> generatedPlayers = randomPlayersGenerator.generateRandomPlayers(league.getLeagueDate(), numberOfDrafteesToGenerate);

        for (int i = 1; i <= DRAFT_ROUNDS; i++) {
            List<ITeam> teamsForCurrentRound = draftManager.generateTeamOrderForDraftSelection(teamsForDraftRounds, draftPicks, i);
            for (ITeam teamPickingDraftee : teamsForCurrentRound) {
                draftManager.performDraftSelectionForTeam(teamPickingDraftee, generatedPlayers);
            }
        }

        league.setDraftPicks(null);

        teamRoster.setAgents(league.getFreeAgents());
        for (ITeam currentTeam : teamsForDraftRounds) {
            teamRoster.setPlayers(currentTeam.getPlayers());
            teamRoster.validateRoster();
            currentTeam.setPlayers(teamRoster.getPlayers());
            teamRoster.setPlayers(null);
        }
        return null;
    }

}
