package com.Trading;

import com.IceHockeyLeague.LeagueManager.Player.IPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.List;

public class TeamValidator {
    private ITeam givenTeam;
    private List<IPlayer> skaters;
    private List<IPlayer> goalies;
    private int leagueID;

    public TeamValidator(ITeam team, int leagueID){
        setDefaults(team, leagueID);
    }

    private void setDefaults(ITeam team, int leagueID){
        this.givenTeam = team;
        this.leagueID = leagueID;
    }

    private void validateTeamNumber(){
        int numberOfSkaters = 0;
        int numberOfKeepers = 0;
        for (IPlayer player : this.givenTeam.getPlayers()){
            if(player.getPlayerPosition().equals("Keeper")){
                numberOfKeepers++;
            }else{
                numberOfSkaters++;
            }
        }

        if(numberOfSkaters>18){
            int difference = numberOfSkaters-18;
            this.dropSkaters(difference);
        }

        if(numberOfSkaters<18){
            int difference = 18-numberOfSkaters;
            this.addSkaters(difference);
        }

        if(numberOfKeepers>2){
            int difference = numberOfSkaters-2;
            this.dropKeepers(difference);
        }

        if(numberOfKeepers<2){
            int difference = 2-numberOfSkaters;
            this.addKeepers(difference);
        }
    }

    private void addKeepers(int difference) {
        
    }

    private void dropKeepers(int difference) {

    }

    private void addSkaters(int difference) {

    }

    private void dropSkaters(int difference) {

    }
}
