package com.Trading;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.ArrayList;
import java.util.List;

public class GenerateTrade {
    private final ITradingFactory tradingFactory;
    private List<ITeam> resultTeams = new ArrayList<>();
    private boolean tradeResult;
    private Trade trade;

    public GenerateTrade() {
        tradingFactory = AbstractAppFactory.getTradingFactory();
    }

    public void generateTrade(ITeam team1, int maxPlayersPerTrade, ITeam team2) {
        this.trade = tradingFactory.createTrade(maxPlayersPerTrade);

        GetWorsePlayersToTradeFromTeam getSendTeamPlayers = tradingFactory.createGetWorsePlayersToTradeFromTeam();
        List<ITeamPlayer> toTradePlayers = getSendTeamPlayers.getPlayersToTrade(maxPlayersPerTrade, team1);
        String position = toTradePlayers.get(0).getPlayerStats().getPosition();
        GetTopNBestPlayersForGivenPosition objectGetTeam2Players = tradingFactory.createGetTopNBestPlayersForGivenPosition(team2, toTradePlayers.size(), position);
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
        TradeDecision decision = tradingFactory.createTradeDecision(trade.getSendingPlayers(), trade.getReceivingPlayers(), randomAcceptChance);
        boolean result = decision.tradeDecision();
        this.tradeResult = result;
        if (result) {
            TradeAcceptor acceptTrade = tradingFactory.createTradeAcceptor(trade);
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
