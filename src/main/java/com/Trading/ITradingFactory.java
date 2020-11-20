package com.Trading;

import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.List;

public interface ITradingFactory {
    DivisionUpdatorAfterTrading makeDivisionUpdatorAfterTrading(ILeague league, ITeam oldSendingTeam, ITeam newSendingTeam, ITeam oldRecievingTeam, ITeam newRecievingTeam);

    GenerateTrade makeGenerateTrade();

    GetAllTeamsFromLeague makeGetAllTeamsFromLeague(ILeague league);

    GetBestChoiceAgentAndPlayer makeGetBestChoiceAgentAndPlayer();

    GetBestPlayersFromAllTeams makeGetBestPlayersFromAllTeams(List<ITeam> teams);

    GetTopNBestPlayersForGivenPosition makeGetTopNBestPlayersForGivenPosition(ITeam team, int tradeNumber, String position);

    GetTradableTeams makeGetTradableTeams(List<ITeam> teams, int lossPoint);

    GetWorsePlayersToTradeFromTeam makeGetWorsePlayersToTradeFromTeam();


}
