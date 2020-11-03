package com.TradingTest;

import com.IceHockeyLeague.LeagueManager.Player.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;

public interface ISwitchPlayer {
    IFreeAgent teamToFreeTrade(ITeamPlayer player, int leagueID);

    ITeamPlayer freeToTeamTrade(IFreeAgent freeAgent, int teamID);
}
