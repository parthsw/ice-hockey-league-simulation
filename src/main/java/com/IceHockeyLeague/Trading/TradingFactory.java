package com.IceHockeyLeague.Trading;

import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.List;

public class TradingFactory implements ITradingFactory {

    @Override
    public DivisionUpdatorAfterTrading createDivisionUpdatorAfterTrading() {
        return new DivisionUpdatorAfterTrading();
    }

    @Override
    public GenerateTrade createGenerateTrade() {
        return new GenerateTrade();
    }

    @Override
    public GetAllTeamsFromLeague createGetAllTeamsFromLeague(ILeague league) {
        return new GetAllTeamsFromLeague(league);
    }

    public GetPlayerFromTeam createGetPlayerFromTeam() {
        return new GetPlayerFromTeam();
    }

    @Override
    public GetBestPlayersFromAllTeams createGetBestPlayersFromAllTeams(List<ITeam> teams) {
        return new GetBestPlayersFromAllTeams(teams);
    }

    @Override
    public GetTopNBestPlayersForGivenPosition createGetTopNBestPlayersForGivenPosition(ITeam team, int maxTradeNumber) {
        return new GetTopNBestPlayersForGivenPosition(team, maxTradeNumber);
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
    public Trade createTrade(int maxPlayersPerTrade) {
        return new Trade(maxPlayersPerTrade);
    }

    @Override
    public GetBestAgent createGetBestAgent() {
        return new GetBestAgent();
    }

}
