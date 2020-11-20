package com.Trading;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.Player.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.ArrayList;
import java.util.List;

public class TeamValidator {
    private final ITradingFactory tradingFactory;
    private ITeam givenTeam;
    private List<ITeamPlayer> skaters;
    private List<ITeamPlayer> goalies;
    private List<IFreeAgent> availableAgents;
    private int leagueID;
    GetBestChoiceAgentAndPlayer obj;
    ISwitchPlayer switchPlayer;
    private String goalie = "Goalie";

    public TeamValidator(ITeam team, int leagueID, List<IFreeAgent> agents){
        tradingFactory = AbstractAppFactory.getTradingFactory();
        this.givenTeam = team;
        this.leagueID = leagueID;
        this.availableAgents = agents;
        skaters = new ArrayList<>();
        goalies = new ArrayList<>();
        obj = tradingFactory.createGetBestAgent();
        switchPlayer = tradingFactory.createSwitchPlayer();
    }

    public ITeam validateTeam() {

        int numberOfSkaters = 0;
        int numberOfKeepers = 0;

        for (ITeamPlayer player : this.givenTeam.getPlayers()) {
            if (player.getPlayerStats().getPosition().equalsIgnoreCase(goalie)) {
                this.goalies.add(player);
                numberOfKeepers++;
            } else {
                this.skaters.add(player);
                numberOfSkaters++;
            }
        }

        if (skaters.size() > 18) {
            int difference = numberOfSkaters - 18;
            this.dropSkaters(difference);
        } else if (skaters.size() < 18) {
            int difference = 18 - numberOfSkaters;
            this.addSkaters(difference);
        }

        if (goalies.size() > 2) {
            int difference = numberOfKeepers - 2;
            this.dropGoalie(difference);
        } else if (goalies.size() < 2) {
            int difference = 2 - numberOfKeepers;
            this.addGoalie(difference);
        }

        this.skaters.addAll(this.goalies);
        this.givenTeam.setPlayers(this.skaters);
        return this.givenTeam;

    }

    private void addGoalie(int difference) {
        for(int i=0; i<difference; i++){
            IFreeAgent temp = this.obj.getBestAgentWithPosition(this.availableAgents, this.goalie);
            this.availableAgents.remove(temp);
            ITeamPlayer switchedObject = switchPlayer.freeToTeamTrade(temp,this.givenTeam.getTeamID());
            this.goalies.add(switchedObject);
        }

    }

    private void dropGoalie(int difference) {
        for(int i=0; i<difference; i++){
            ITeamPlayer temp = this.obj.getWorsePlayerInTeamWithPosition(this.goalies, this.goalie);
            this.goalies.remove(temp);
            IFreeAgent switchedObject = switchPlayer.teamToFreeTrade(temp, this.leagueID);
            this.availableAgents.add(switchedObject);
        }

    }

    private void addSkaters(int difference) {
        for(int i = 0; i <difference; i++){
            IFreeAgent temp = this.obj.getRandomBestAgentSkater(this.availableAgents);
            this.availableAgents.remove(temp);
            ITeamPlayer switchedObject = switchPlayer.freeToTeamTrade(temp,this.givenTeam.getTeamID());
            this.skaters.add(switchedObject);
        }

    }

    private void dropSkaters(int difference) {
        for (int i = 0; i < difference; i++) {
            ITeamPlayer temp = this.obj.getWorseSkaterInTeam(this.skaters);
            this.skaters.remove(temp);
            IFreeAgent switchedObject = switchPlayer.teamToFreeTrade(temp, this.leagueID);
            this.availableAgents.add(switchedObject);
        }
    }

}

