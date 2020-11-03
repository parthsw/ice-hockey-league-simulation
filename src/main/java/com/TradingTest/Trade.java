package com.TradingTest;

import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.List;

public class Trade {
    private ITeam sendingTeam;
    private ITeam receivingTeam;
    private List<ITeamPlayer> sendingPlayers;
    private List<ITeamPlayer> receivingPlayers;
    private int maxPlayersPerTrade;

    public Trade(int maxPlayersPerTrade) {
        this.maxPlayersPerTrade = maxPlayersPerTrade;
    }

    public ITeam getSendingTeam() {
        return sendingTeam;
    }

    public void setSendingTeam(ITeam sendingTeam) {
        this.sendingTeam = sendingTeam;
    }

    public ITeam getReceivingTeam() {
        return receivingTeam;
    }

    public void setReceivingTeam(ITeam receivingTeam) {
        this.receivingTeam = receivingTeam;
    }

    public List<ITeamPlayer> getSendingPlayers() {
        return sendingPlayers;
    }

    public void setSendingPlayers(List<ITeamPlayer> sendingPlayers) {
        this.sendingPlayers = sendingPlayers;
    }

    public List<ITeamPlayer> getReceivingPlayers() {
        return receivingPlayers;
    }

    public void setReceivingPlayers(List<ITeamPlayer> receivingPlayers) {
        this.receivingPlayers = receivingPlayers;
    }

    public boolean validateTrade() {
        if (this.sendingTeam.getTeamName().equals(receivingTeam.getTeamName())) {
            return false;
        }

        if ((this.sendingPlayers.size() > this.maxPlayersPerTrade) || (this.receivingPlayers.size() > this.maxPlayersPerTrade)) {
            return false;
        }

        String position = this.receivingPlayers.get(0).getPlayerStats().getPosition();
        if (this.sendingPlayers.size() == this.receivingPlayers.size()) {
            for (ITeamPlayer player : this.sendingPlayers) {
                if (player.getPlayerStats().getPosition().equals(position)) {

                } else {
                    return false;
                }
            }

            for (ITeamPlayer player : this.receivingPlayers) {
                if (player.getPlayerStats().getPosition().equals(position)) {

                } else {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
