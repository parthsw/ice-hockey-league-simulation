package com.IceHockeyLeague.Trading;

import com.AbstractAppFactory;
import com.IO.IAppOutput;
import com.IO.IIOFactory;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Trade implements ITrade {
    private ITeam sendingTeam;
    private ITeam receivingTeam;
    private List<ITeamPlayer> sendingPlayers;
    private List<ITeamPlayer> receivingPlayers;
    private int maxPlayersPerTrade;
    private final IIOFactory ioFactory;
    private IAppOutput appOutput;
    private final Logger LOGGER = LogManager.getLogger(Trade.class);

    public Trade(int maxPlayersPerTrade) {
        this.maxPlayersPerTrade = maxPlayersPerTrade;
        ioFactory = AbstractAppFactory.getIOFactory();
        this.appOutput = ioFactory.createCommandLineOutput();
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
        if (this.sendingTeam.getTeamName().equalsIgnoreCase(receivingTeam.getTeamName())) {
            return false;
        }

        if ((this.sendingPlayers.size() > this.maxPlayersPerTrade) || (this.receivingPlayers.size() > this.maxPlayersPerTrade)) {
            return false;
        }

        return true;
    }

    public List<ITeam> acceptTrade() {
        LOGGER.info("Entered trade accept");
        List<ITeamPlayer> team1Players = new ArrayList<>();
        team1Players.addAll(this.sendingTeam.getPlayers());
        List<ITeamPlayer> team2Players = new ArrayList<>();
        team2Players.addAll(this.receivingTeam.getPlayers());

        for (ITeamPlayer player : this.sendingPlayers) {
            player.setTeamId(this.receivingTeam.getTeamID());
            team2Players.add(player);
        }
        team1Players.removeAll(this.sendingPlayers);

        for (ITeamPlayer player : this.receivingPlayers) {
            player.setTeamId(this.sendingTeam.getTeamID());
            team1Players.add(player);
        }
        team2Players.removeAll(this.receivingPlayers);

        this.sendingTeam.setPlayers(team1Players);
        this.receivingTeam.setPlayers(team2Players);
        List<ITeam> result = new ArrayList<>();
        int counter = 0;
        for (ITeamPlayer player : this.sendingPlayers) {
            appOutput.display("Player " + player.getPlayerName() + " from team " + this.sendingTeam.getTeamName() + " traded with " + this.receivingPlayers.get(counter).getPlayerName() + " of team " + this.receivingTeam.getTeamName());
            counter++;
        }
        result.add(this.sendingTeam);
        result.add(this.receivingTeam);
        return result;
    }

    public boolean tradeDecision(float randomAcceptChance) {
        LOGGER.info("Entered trade Decision");
        Random rand = new Random();
        float sumOffered = 0;
        float sumRequested = 0;

        for (ITeamPlayer player : this.sendingPlayers) {
            sumOffered += player.getPlayerStats().getStrength();
        }

        for (ITeamPlayer player : this.receivingPlayers) {
            sumRequested += player.getPlayerStats().getStrength();
        }

        if (rand.nextFloat() <= randomAcceptChance) {
            return true;
        } else if (sumOffered > sumRequested) {
            return true;
        } else {
            return false;
        }
    }
}
