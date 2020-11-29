package com.IceHockeyLeague.Trading;

import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
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
    public GetBestChoiceAgentAndPlayer createGetBestAgent() {
        return new GetBestChoiceAgentAndPlayer();
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
    public TeamValidator createTeamValidator(ITeam team, int leagueID, List<IFreeAgent> agents) {
        return new TeamValidator(team, leagueID, agents);
    }

    @Override
    public Trade createTrade(int maxPlayersPerTrade) {
        return new Trade(maxPlayersPerTrade);
    }

}
