package com.IceHockeyLeague.LeagueManager.Draft.DraftPick;

import com.IceHockeyLeague.LeagueManager.Team.ITeam;

public interface IDraftPick {
    ITeam getSendingTeam();

    ITeam getReceivingTeam();

    int getDraftPickRoundNumber();
}
