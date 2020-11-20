package com.Trading;

import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.List;

public class TradingFactory implements ITradingFactory {

    @Override
    public DivisionUpdatorAfterTrading makeDivisionUpdatorAfterTrading(ILeague league, ITeam oldSendingTeam, ITeam newSendingTeam, ITeam oldRecievingTeam, ITeam newRecievingTeam) {
        return new DivisionUpdatorAfterTrading(league, oldSendingTeam, newSendingTeam, oldRecievingTeam, newRecievingTeam);
    }

    @Override
    public GenerateTrade makeGenerateTrade() {
        return new GenerateTrade();
    }

    @Override
    public GetAllTeamsFromLeague makeGetAllTeamsFromLeague(ILeague league) {
        return new GetAllTeamsFromLeague(league);
    }

    @Override
    public GetBestChoiceAgentAndPlayer makeGetBestChoiceAgentAndPlayer() {
        return new GetBestChoiceAgentAndPlayer();
    }

    @Override
    public GetBestPlayersFromAllTeams makeGetBestPlayersFromAllTeams(List<ITeam> teams) {
        return new GetBestPlayersFromAllTeams(teams);
    }

    @Override
    public GetTopNBestPlayersForGivenPosition makeGetTopNBestPlayersForGivenPosition(ITeam team, int tradeNumber, String position) {
        return new GetTopNBestPlayersForGivenPosition(team, tradeNumber, position);
    }

    @Override
    public GetTradableTeams makeGetTradableTeams(List<ITeam> teams, int lossPoint) {
        return new GetTradableTeams(teams, lossPoint);
    }

    @Override
    public GetWorsePlayersToTradeFromTeam makeGetWorsePlayersToTradeFromTeam() {
        return new GetWorsePlayersToTradeFromTeam();
    }
}
