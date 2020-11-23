package com.IceHockeyLeague.Trading;

import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;

public interface ISwitchPlayer {
    IFreeAgent teamToFreeTrade(ITeamPlayer player, int leagueID);

    ITeamPlayer freeToTeamTrade(IFreeAgent freeAgent, int teamID);
}
