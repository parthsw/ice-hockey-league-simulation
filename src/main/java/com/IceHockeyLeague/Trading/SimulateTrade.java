package com.IceHockeyLeague.Trading;

import com.AbstractAppFactory;
import com.IO.IAppInput;
import com.IO.IAppOutput;
import com.IO.IIOFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.ArrayList;
import java.util.List;

public class SimulateTrade implements ISimulateTrade{
    private final ITradingFactory tradingFactory;
    private final IIOFactory ioFactory;

    private GetAllTeamsFromLeague getAllTeamsObject;
    private GetTradableTeams getTradableTeamsObject;
    private GetWorsePlayersToTradeFromTeam selectPlayersToTrade;
    private List<ITeam> allTeams;
    private List<ITeam> tradableTeams;
    private int maxPlayerPerTrade;
    private float randomAcceptChance;
    private ILeague league;
    private IAppInput appInput;
    private IAppOutput appOutput;

    private String divider = "------------------------------------------------------------------------------------------------------------------";
    private String newTradeOffer = "                                          You have a new Trade offer";

    public SimulateTrade() {
        tradingFactory = AbstractAppFactory.getTradingFactory();
        ioFactory = AbstractAppFactory.getIOFactory();
    }

    public void simulateTrade(ILeague league, int lossPointValue, int maxPlayersPerTrade, float randomAcceptChance) {
        this.league = league;
        this.getAllTeamsObject = tradingFactory.createGetAllTeamsFromLeague(this.league);
        this.allTeams = this.getAllTeamsObject.getTeams();
        this.getTradableTeamsObject = tradingFactory.createGetTradableTeams(allTeams, lossPointValue);
        this.tradableTeams = this.getTradableTeamsObject.getTeams();
        this.selectPlayersToTrade = tradingFactory.createGetWorsePlayersToTradeFromTeam();
        this.maxPlayerPerTrade = maxPlayersPerTrade;
        this.randomAcceptChance = randomAcceptChance;
        this.appInput = ioFactory.createCommandLineInput();
        this.appOutput = ioFactory.createCommandLineOutput();
    }


    public void simulate() {
        for (ITeam team : this.tradableTeams) {
            if (team.getIsUserCreated()) {
            } else {
                ITeam selectedTeam = this.selectTeamToTrade(team);
                GenerateTrade generateTrade = tradingFactory.createGenerateTrade();
                generateTrade.generateTrade(team, this.maxPlayerPerTrade, selectedTeam);
                if (selectedTeam.getIsUserCreated()) {
                    this.userChoice(generateTrade.getGeneratedTrade(), generateTrade);
                } else {
                    this.tradeResolution(generateTrade, this.randomAcceptChance);
                    Trade trade = generateTrade.getGeneratedTrade();
                    trade.getSendingTeam().setLossPointValue(0);
                }
            }
        }
    }

    private ITeam selectTeamToTrade(ITeam team) {
        List<ITeam> temp = new ArrayList<>();
        temp.addAll(this.allTeams);
        temp.remove(team);
        GetBestPlayersFromAllTeams getBestTeamChoice = tradingFactory.createGetBestPlayersFromAllTeams(temp);
        getBestTeamChoice.getBestTradeOption(this.maxPlayerPerTrade);
        ITeam bestChoice = getBestTeamChoice.getTeam();
        return bestChoice;
    }

    private void userChoice(Trade trade, GenerateTrade generateTrade) {
        this.appOutput.display(this.divider);
        this.appOutput.display(this.newTradeOffer);
        this.appOutput.display(this.divider);
        this.appOutput.display("");
        this.appOutput.display("Team " + trade.getSendingTeam().getTeamName() + " has offered you a trade.");
        this.appOutput.display("Players offered:");
        for (ITeamPlayer player : trade.getSendingPlayers()) {
            displayPlayers(player);
        }
        this.appOutput.display("Players demanded:");
        for (ITeamPlayer player : trade.getReceivingPlayers()) {
            displayPlayers(player);
        }
        this.appOutput.display("To accept this offer type 'YES'");
        this.appOutput.display("To reject this offer type 'NO'");
        String userChoice = this.appInput.getInput();

        if (userChoice.equals("YES")) {
            this.tradeResolution(generateTrade, 1);
            trade.getSendingTeam().setLossPointValue(0);
        } else if (userChoice.equals("NO")) {
            trade.getSendingTeam().setLossPointValue(0);
        } else {
            this.appOutput.displayError("Invalid choice input entered.");
        }
    }

    private void tradeResolution(GenerateTrade generateTrade, float randomAcceptChance) {
        generateTrade.decideTrade(randomAcceptChance);
        List<ITeam> resultTeams = generateTrade.getResultTeams();
        for (ITeam modifiedTeam : resultTeams) {
            modifiedTeam.validateRoster(league.getFreeAgents());
        }

    }

    private void displayPlayers(ITeamPlayer player) {
        this.appOutput.display("**************************************************************************");
        this.appOutput.display("Name: " + player.getPlayerName());
        this.appOutput.display("Age: " + player.getPlayerAgeInfo().getAgeInYears());
        this.appOutput.display("Position: " + player.getPlayerStats().getPosition());
        this.appOutput.display("Strength: " + player.getPlayerStats().getStrength());
        this.appOutput.display("Shooting: " + player.getPlayerStats().getShooting());
        this.appOutput.display("Checking: " + player.getPlayerStats().getChecking());
        this.appOutput.display("Saving: " + player.getPlayerStats().getSaving());
        this.appOutput.display("Skating: " + player.getPlayerStats().getSkating());
        this.appOutput.display("**************************************************************************");
    }
}
