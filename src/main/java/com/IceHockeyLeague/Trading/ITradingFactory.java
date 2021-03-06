package com.IceHockeyLeague.Trading;

import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.List;

public interface ITradingFactory {
    DivisionUpdatorAfterTrading createDivisionUpdatorAfterTrading();

    GenerateTrade createGenerateTrade();

    GetAllTeamsFromLeague createGetAllTeamsFromLeague(ILeague league);

    GetPlayerFromTeam createGetPlayerFromTeam();

    GetBestAgent createGetBestAgent();

    GetBestPlayersFromAllTeams createGetBestPlayersFromAllTeams(List<ITeam> teams);

    GetTopNBestPlayersForGivenPosition createGetTopNBestPlayersForGivenPosition(ITeam team, int tradeNumber);

    GetTradableTeams createGetTradableTeams(List<ITeam> teams, int lossPoint);

    GetWorsePlayersToTradeFromTeam createGetWorsePlayersToTradeFromTeam();

    ISwitchPlayer createSwitchPlayer();

    SimulateTrade createSimulateTrade();

    Trade createTrade(int maxPlayersPerTrade);

}
