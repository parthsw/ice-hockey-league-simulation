package com.IceHockeyLeague.LeagueManager.GameSimulator;

public class GameStats implements IGameStats {
    private int team1Goals;
    private int team1Penalties;
    private int team1Shots;
    private int team1Saves;
    private int team2Goals;
    private int team2Penalties;
    private int team2Shots;
    private int team2Saves;

    @Override
    public int getTeam1Goals() {
        return team1Goals;
    }

    @Override
    public void setTeam1Goals(int team1Goals) {
        this.team1Goals = team1Goals;
    }

    @Override
    public int getTeam1Penalties() {
        return team1Penalties;
    }

    @Override
    public void setTeam1Penalties(int team1Penalties) {
        this.team1Penalties = team1Penalties;
    }

    @Override
    public int getTeam1Shots() {
        return team1Shots;
    }

    @Override
    public void setTeam1Shots(int team1Shots) {
        this.team1Shots = team1Shots;
    }

    @Override
    public int getTeam1Saves() {
        return team1Saves;
    }

    @Override
    public void setTeam1Saves(int team1Saves) {
        this.team1Saves = team1Saves;
    }

    @Override
    public int getTeam2Goals() {
        return team2Goals;
    }

    @Override
    public void setTeam2Goals(int team2Goals) {
        this.team2Goals = team2Goals;
    }

    @Override
    public int getTeam2Penalties() {
        return team2Penalties;
    }

    @Override
    public void setTeam2Penalties(int team2Penalties) {
        this.team2Penalties = team2Penalties;
    }

    @Override
    public int getTeam2Shots() {
        return team2Shots;
    }

    @Override
    public void setTeam2Shots(int team2Shots) {
        this.team2Shots = team2Shots;
    }

    @Override
    public int getTeam2Saves() {
        return team2Saves;
    }

    @Override
    public void setTeam2Saves(int team2Saves) {
        this.team2Saves = team2Saves;
    }
}
