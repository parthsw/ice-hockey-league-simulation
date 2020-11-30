package com.IceHockeyLeague.LeagueManager.GameSimulator;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

public class GameSimulationSystem implements IGameSimulationSystem {
    private int totalGoals;
    private int totalPenalties;
    private int totalShots;
    private int totalSaves;
    private int numberOfGamesPlayed;
    private final ILeagueManagerFactory leagueManagerFactory;
    private final IGameSimulationConfig gameSimulationConfig;

    public GameSimulationSystem() {
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        gameSimulationConfig = leagueManagerFactory.createGameSimulationConfig();
    }

    @Override
    public int getTotalGoals() {
        return totalGoals;
    }

    @Override
    public void setTotalGoals(int totalGoals) {
        this.totalGoals = totalGoals;
    }

    @Override
    public int getTotalPenalties() {
        return totalPenalties;
    }

    @Override
    public void setTotalPenalties(int totalPenalties) {
        this.totalPenalties = totalPenalties;
    }

    @Override
    public int getTotalShots() {
        return totalShots;
    }

    @Override
    public void setTotalShots(int totalShots) {
        this.totalShots = totalShots;
    }

    @Override
    public int getTotalSaves() {
        return totalSaves;
    }

    @Override
    public void setTotalSaves(int totalSaves) {
        this.totalSaves = totalSaves;
    }

    @Override
    public int getNumberOfGamesPlayed() {
        return numberOfGamesPlayed;
    }

    @Override
    public void setNumberOfGamesPlayed(int numberOfGamesPlayed) {
        this.numberOfGamesPlayed = numberOfGamesPlayed;
    }

    @Override
    public ITeam simulateGameAndGetWinner(ITeam teamA, ITeam teamB) {
        IGameSimulation gameSimulation = leagueManagerFactory.createGameSimulation(teamA, teamB, gameSimulationConfig);
        gameSimulation.simulateGame();
        updateGameStats(gameSimulation);
        return getWinner(gameSimulation, teamA, teamB);
    }

    private void updateGameStats(IGameSimulation gameSimulation) {
        totalGoals += gameSimulation.getGameStats().getTeam1Goals();
        totalGoals += gameSimulation.getGameStats().getTeam2Goals();
        totalPenalties += gameSimulation.getGameStats().getTeam1Penalties();
        totalPenalties += gameSimulation.getGameStats().getTeam2Penalties();
        totalShots += gameSimulation.getGameStats().getTeam1Shots();
        totalShots += gameSimulation.getGameStats().getTeam2Shots();
        totalSaves += gameSimulation.getGameStats().getTeam1Saves();
        totalSaves += gameSimulation.getGameStats().getTeam2Saves();
        numberOfGamesPlayed++;
    }

    private ITeam getWinner(IGameSimulation gameSimulation, ITeam teamA, ITeam teamB) {
        ITeam winningTeam;
        int team1Goals = gameSimulation.getGameStats().getTeam1Goals();
        int team2Goals = gameSimulation.getGameStats().getTeam2Goals();
        if (team1Goals > team2Goals) {
            winningTeam = teamA;
        } else if (team2Goals > team1Goals) {
            winningTeam = teamB;
        } else {
            winningTeam = null;
        }
        return winningTeam;
    }
}
