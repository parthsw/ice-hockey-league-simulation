package com.IceHockeyLeague.StateMachine.States;

import com.IceHockeyLeague.LeagueManager.Draft.DraftPick.IDraftPick;
import com.IceHockeyLeague.LeagueManager.Draft.IDraftManager;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Player.*;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.List;

public class DraftingState extends AbstractState {
    private static final int DRAFT_ROUNDS = 7;
    private final IRandomPlayersGenerator randomPlayersGenerator;
    private final IDraftManager draftManager;

    public DraftingState(IRandomPlayersGenerator randomPlayersGenerator, IDraftManager draftManager) {
        this.randomPlayersGenerator = randomPlayersGenerator;
        this.draftManager = draftManager;
    }

    @Override
    public AbstractState onRun() {
        ILeague league = getLeague();
        List<IDraftPick> draftPicks = league.getDraftPicks();
        List<ITeam> teamsForDraftRounds = draftManager.generateTeamOrderForDraftSelection(league);
        int numberOfDrafteesPerRound = teamsForDraftRounds.size();
        int numberOfDrafteesToGenerate = numberOfDrafteesPerRound * DRAFT_ROUNDS;

        List<IPlayer> generatedPlayers = randomPlayersGenerator.generateRandomPlayers(league.getLeagueDate(), numberOfDrafteesToGenerate);

        for(int i = 1; i <= DRAFT_ROUNDS; i++) {
            List<ITeam> teamsForCurrentRound = draftManager.generateTeamOrderForDraftSelection(teamsForDraftRounds, draftPicks, i);
            for(ITeam teamPickingDraftee: teamsForCurrentRound) {
                draftManager.performDraftSelectionForTeam(teamPickingDraftee, generatedPlayers);
            }
        }

        league.setDraftPicks(null);

        for (ITeam currentTeam : teamsForDraftRounds) {
            currentTeam.checkTeamPlayers();
        }
        return null;
    }

}
