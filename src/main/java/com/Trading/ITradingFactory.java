package com.Trading;

import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Player.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.List;

public interface ITradingFactory {
    DivisionUpdatorAfterTrading createDivisionUpdatorAfterTrading(ILeague league, ITeam oldSendingTeam, ITeam newSendingTeam, ITeam oldReceivingTeam, ITeam newReceivingTeam);

    GenerateTrade createGenerateTrade();

    GetAllTeamsFromLeague createGetAllTeamsFromLeague(ILeague league);

    GetBestAgent createGetBestAgent();

    GetBestPlayersFromAllTeams createGetBestPlayersFromAllTeams(List<ITeam> teams);

    GetTopNBestPlayersForGivenPosition createGetTopNBestPlayersForGivenPosition(ITeam team, int tradeNumber, String position);

    GetTradableTeams createGetTradableTeams(List<ITeam> teams, int lossPoint);

    GetWorsePlayersToTradeFromTeam createGetWorsePlayersToTradeFromTeam();

    ISwitchPlayer createSwitchPlayer();

    SimulateTrade createSimulateTrade();

    TeamValidator createTeamValidator(ITeam team, int leagueID, List<IFreeAgent> agents);

    Trade createTrade(int maxPlayersPerTrade);

    TradeAcceptor createTradeAcceptor(Trade trade);

    TradeDecision createTradeDecision(List<ITeamPlayer> offered, List<ITeamPlayer> requested, float randomAcceptChance);
}
