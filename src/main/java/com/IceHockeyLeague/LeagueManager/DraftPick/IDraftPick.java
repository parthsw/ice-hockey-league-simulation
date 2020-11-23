package com.IceHockeyLeague.LeagueManager.DraftPick;

import com.IceHockeyLeague.LeagueManager.Team.ITeam;

public interface IDraftPick {
    ITeam getSendingTeam();

    ITeam getReceivingTeam();

    int getDraftRoundNumber();

}
