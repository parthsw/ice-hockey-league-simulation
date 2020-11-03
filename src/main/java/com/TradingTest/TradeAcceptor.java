package com.TradingTest;

import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.ArrayList;
import java.util.List;

public class TradeAcceptor {
    private ITeam team1;
    private ITeam team2;
    private List<ITeamPlayer> fromTeam1;
    private List<ITeamPlayer> fromTeam2;

    public TradeAcceptor(Trade trade) {
        this.team1 = trade.getSendingTeam();
        this.team2 = trade.getReceivingTeam();
        this.fromTeam1 = trade.getSendingPlayers();
        this.fromTeam2 = trade.getReceivingPlayers();
    }


    public List<ITeam> acceptTrade() {
        List<ITeamPlayer> team1Players = new ArrayList<>();
        team1Players.addAll(this.team1.getPlayers());
        List<ITeamPlayer> team2Players = new ArrayList<>();
        team2Players.addAll(this.team2.getPlayers());

        for (ITeamPlayer player : this.fromTeam1) {
            player.setTeamID(this.team2.getTeamID());
            team2Players.add(player);
        }
        team1Players.removeAll(this.fromTeam1);

        for (ITeamPlayer player : this.fromTeam2) {
            player.setTeamID(this.team1.getTeamID());
            team1Players.add(player);
        }
        team2Players.removeAll(this.fromTeam2);

        this.team1.setPlayers(team1Players);
        this.team2.setPlayers(team2Players);
        List<ITeam> result = new ArrayList<>();
        result.add(this.team1);
        result.add(this.team2);
        return result;
    }
}
