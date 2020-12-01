package com.IceHockeyLeague.LeagueManager.Draft;

import com.IceHockeyLeague.LeagueManager.Draft.DraftPick.IDraftPick;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Player.IPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.List;

public interface IDraftManager {
    IPlayer findBestDraftee(List<IPlayer> draftees);

    void performDraftSelectionForTeam(ITeam teamPickingDraftee, List<IPlayer> draftees);

    List<ITeam> generateTeamOrderForDraftSelection(ILeague league);

    List<ITeam> generateTeamOrderForDraftSelection(List<ITeam> teams, List<IDraftPick> draftPicks, int roundNumber);
}
