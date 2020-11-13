package com.Trading;

import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Player.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.List;

public class TradingFactory implements ITradingFactory {

    @Override
    public DivisionUpdatorAfterTrading createDivisionUpdatorAfterTrading(ILeague league, ITeam oldSendingTeam, ITeam newSendingTeam, ITeam oldReceivingTeam, ITeam newReceivingTeam) {
        return new DivisionUpdatorAfterTrading(league, oldSendingTeam, newSendingTeam, oldReceivingTeam, newReceivingTeam);
    }

    @Override
    public GenerateTrade createGenerateTrade() {
        return new GenerateTrade();
    }

    @Override
    public GetAllTeamsFromLeague createGetAllTeamsFromLeague(ILeague league) {
        return new GetAllTeamsFromLeague(league);
    }

    @Override
    public GetBestAgent createGetBestAgent() {
        return new GetBestAgent();
    }

    @Override
    public GetBestPlayersFromAllTeams createGetBestPlayersFromAllTeams(List<ITeam> teams) {
        return new GetBestPlayersFromAllTeams(teams);
    }

    @Override
    public GetTopNBestPlayersForGivenPosition createGetTopNBestPlayersForGivenPosition(ITeam team, int tradeNumber, String position) {
        return new GetTopNBestPlayersForGivenPosition(team, tradeNumber, position);
    }

    @Override
    public GetTradableTeams createGetTradableTeams(List<ITeam> teams, int lossPoint) {
        return new GetTradableTeams(teams, lossPoint);
    }

    @Override
    public GetWorsePlayersToTradeFromTeam createGetWorsePlayersToTradeFromTeam() {
        return new GetWorsePlayersToTradeFromTeam();
    }

    @Override
    public ISwitchPlayer createSwitchPlayer() {
        return new SwitchPlayer();
    }

    @Override
    public SimulateTrade createSimulateTrade() {
        return new SimulateTrade();
    }

    @Override
    public TeamValidator createTeamValidator(ITeam team, int leagueID, List<IFreeAgent> agents) {
        return new TeamValidator(team, leagueID, agents);
    }

    @Override
    public Trade createTrade(int maxPlayersPerTrade) {
        return new Trade(maxPlayersPerTrade);
    }

    @Override
    public TradeAcceptor createTradeAcceptor(Trade trade) {
        return new TradeAcceptor(trade);
    }

    @Override
    public TradeDecision createTradeDecision(List<ITeamPlayer> offered, List<ITeamPlayer> requested, float randomAcceptChance) {
        return new TradeDecision(offered, requested, randomAcceptChance);
    }
}
