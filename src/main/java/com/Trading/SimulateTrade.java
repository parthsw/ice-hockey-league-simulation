package com.Trading;

import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.ArrayList;
import java.util.List;

public class SimulateTrade {
    private GetAllTeamsFromLeague getAllTeamsObject;
    private GetTradableTeams getTradableTeamsObject;
    private GetWorsePlayersToTradeFromTeam selectPlayersToTrade;
    private List<ITeam> allteams;
    private List<ITeam> tradableTeams;
    private int maxPlayerPerTrade;
    private int tradeSize;
    private float randomAcceptChance;

    public void simulateTrade(ILeague league, int lossPointValue, int maxPlayersPerTrade, float randomAcceptChance) {
        this.getAllTeamsObject = new GetAllTeamsFromLeague(league);
        this.allteams = this.getAllTeamsObject.getTeams();
        this.getTradableTeamsObject = new GetTradableTeams(allteams, lossPointValue);
        this.tradableTeams = this.getTradableTeamsObject.getTeams();
        this.selectPlayersToTrade = new GetWorsePlayersToTradeFromTeam();
        this.maxPlayerPerTrade = maxPlayersPerTrade;
        this.randomAcceptChance = randomAcceptChance;
    }

    public void simulate() {
        for (ITeam team : this.tradableTeams) {
            if (team.getIsUserCreated()) {
                continue;
            } else {
                ITeam selectedTeam = this.selectTeamToTrade(team);
                if (selectedTeam.getIsUserCreated()) {
                    this.userChoice();
                } else {
                    GenerateTrade generateTrade = new GenerateTrade();
                    generateTrade.generateTrade(team, this.tradeSize, selectedTeam, this.randomAcceptChance);

                }
            }
        }
    }

    private ITeam selectTeamToTrade(ITeam team) {
        List<ITeam> temp = new ArrayList<>();
        List<ITeamPlayer> playerListToBeTraded;
        temp.addAll(this.allteams);
        temp.remove(team);
        playerListToBeTraded = this.selectPlayersToTrade.getPlayersToTrade(this.maxPlayerPerTrade, team);
        this.tradeSize = playerListToBeTraded.size();
        String position = playerListToBeTraded.get(0).getPlayerStats().getPosition();
        GetBestPlayersFromAllTeams getBestTeamChoice = new GetBestPlayersFromAllTeams(temp);
        getBestTeamChoice.getBestTradeOption(position, tradeSize);
        ITeam bestChoice = getBestTeamChoice.getTeam();
        return bestChoice;
    }

    private void userChoice() {

    }
}
