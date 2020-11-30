package com.IceHockeyLeague.Trading;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class GenerateTrade implements IGenerateTrade {
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
        GetTopNBestPlayersForGivenPosition objectGetTeam2Players = tradingFactory.createGetTopNBestPlayersForGivenPosition(team2, toTradePlayers.size());
        List<ITeamPlayer> team2Players = objectGetTeam2Players.getPlayers();

        trade.setSendingTeam(team1);
        trade.setSendingPlayers(toTradePlayers);
        trade.setReceivingTeam(team2);
        trade.setReceivingPlayers(team2Players);

    }

    public Trade getGeneratedTrade() {
        return this.trade;
    }

    private float updateRandomChance(float randomAcceptChance) {
        Hashtable<String, Float> managerPersonality = this.trade.getReceivingTeam().getManager().getManagerPersonality();
        for (String key : managerPersonality.keySet()) {
            float value = managerPersonality.get(key);
            randomAcceptChance += value;
        }
        return randomAcceptChance;
    }

    public void decideTrade(float randomAcceptChance) {
        randomAcceptChance = this.updateRandomChance(randomAcceptChance);
        boolean result = trade.tradeDecision(randomAcceptChance);
        this.tradeResult = result;
        if (result) {
            this.resultTeams = trade.acceptTrade();
        }
    }

    public List<ITeam> getResultTeams() {
        return this.resultTeams;
    }

    public boolean getTradeResult() {
        return this.tradeResult;
    }

}
