package com.Trading;

import com.IceHockeyLeague.LeagueManager.Player.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.List;

public class TeamValidator {
    private ITeam givenTeam;
    private List<ITeamPlayer> skaters;
    private List<ITeamPlayer> goalies;
    private List<IFreeAgent> availableAgents;
    private int leagueID;
    GetBestAgent obj = new GetBestAgent();
    ISwitchPlayer switchPlayer = new SwitchPlayer();
    private String goalie = "Goalie";

    public TeamValidator(ITeam team, int leagueID, List<IFreeAgent> agents){
        setDefaults(team, leagueID, agents);
    }

    private void setDefaults(ITeam team, int leagueID, List<IFreeAgent> agents){
        this.givenTeam = team;
        this.leagueID = leagueID;
        this.availableAgents = agents;
    }

    public void validateTeamNumber(){
        int numberOfSkaters = 0;
        int numberOfKeepers = 0;
        boolean flag = false;
        for (ITeamPlayer player : this.givenTeam.getPlayers()){
            if(player.getPlayerPosition().equals(goalie)){
                this.goalies.add(player);
                numberOfKeepers++;
            }else{
                this.skaters.add(player);
                numberOfSkaters++;
            }
        }

        if(numberOfSkaters>18){
            int difference = numberOfSkaters-18;
            this.dropSkaters(difference);
            flag = true;
        }

        if(numberOfSkaters<18){
            int difference = 18-numberOfSkaters;
            this.addSkaters(difference);
            flag = true;
        }

        if(numberOfKeepers>2){
            int difference = numberOfSkaters-2;
            this.dropGoalie(difference);
            flag = true;
        }

        if(numberOfKeepers<2){
            int difference = 2-numberOfSkaters;
            this.addGoalie(difference);
            flag = true;
        }

        if(flag){
            validateTeamNumber();
        }
    }

    private void addGoalie(int difference) {
        for(int i=0; i<difference; i++){
            IFreeAgent temp = this.obj.getBestAgentWithPosition(this.availableAgents, goalie);
            this.availableAgents.remove(temp);
            ITeamPlayer switchedObject = switchPlayer.freeToTeamTrade(temp,this.givenTeam.getTeamID());
            this.givenTeam.addPlayer(switchedObject);
        }

    }

    private void dropGoalie(int difference) {
        for(int i=0; i<difference; i++){
            ITeamPlayer temp = this.obj.getWorsePlayerInTeamWithPosition(this.goalies, goalie);
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
            this.givenTeam.addPlayer(switchedObject);
        }

    }

    private void dropSkaters(int difference) {
        for(int i=0; i<difference; i++){
            ITeamPlayer temp = this.obj.getWorseSkaterInTeam(this.skaters);
            this.skaters.remove(temp);
            IFreeAgent switchedObject = switchPlayer.teamToFreeTrade(temp, this.leagueID);
            this.availableAgents.add(switchedObject);
        }
    }

    public ITeam getValidatedTeam(){

    }
}

