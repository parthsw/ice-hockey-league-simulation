package com.Trading;

import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.List;

public class GenerateTrade {
    private List<ITeam> resultTeams;
    private boolean tradeResult;
    private Trade trade;

    public void generateTrade(ITeam team1, int maxPlayersPerTrade, ITeam team2) {
        this.trade = new Trade(maxPlayersPerTrade);

        GetWorsePlayersToTradeFromTeam getSendTeamPlayers = new GetWorsePlayersToTradeFromTeam();
        List<ITeamPlayer> toTradePlayers = getSendTeamPlayers.getPlayersToTrade(maxPlayersPerTrade, team1);
        String position = toTradePlayers.get(0).getPlayerStats().getPosition();
        GetTopNBestPlayersForGivenPosition objectGetTeam2Players = new GetTopNBestPlayersForGivenPosition(team2, toTradePlayers.size(), position);
        List<ITeamPlayer> team2Players = objectGetTeam2Players.getPlayers();

        trade.setSendingTeam(team1);
        trade.setSendingPlayers(toTradePlayers);
        trade.setReceivingTeam(team2);
        trade.setReceivingPlayers(team2Players);

    }

    public Trade getGeneratedTrade() {
        return this.trade;
    }

    public void decideTrade(float randomAcceptChance) {
        TradeDecision decision = new TradeDecision(trade.getSendingPlayers(), trade.getReceivingPlayers(), randomAcceptChance);
        boolean result = decision.tradeDecision();
        this.tradeResult = result;
        if (result) {
            TradeAcceptor acceptTrade = new TradeAcceptor(trade);
            this.resultTeams = acceptTrade.acceptTrade();
        }
    }

    public List<ITeam> getResultTeams() {
        return this.resultTeams;
    }

    public boolean getTradeResult() {
        return this.tradeResult;
    }

}
