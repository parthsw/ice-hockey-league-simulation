package com.IceHockeyLeague.LeagueManager.GameSimulator;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.IRandomChance;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Player.PlayerPosition;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.TrophySystem.BestPerformanceSubject;
import com.TrophySystem.IPerformanceObserver;
import com.TrophySystem.ITrophySystemFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.beancontext.BeanContextChildSupport;
import java.util.List;
import java.util.stream.Collectors;

public class GameSimulation extends BestPerformanceSubject implements IGameSimulation {
    private static final Logger LOGGER = LogManager.getLogger(GameSimulation.class);
    private ILeagueManagerFactory leagueManagerFactory;
    private IGameStats gameStats;
    private ITeam team1;
    private ITeam team2;
    private List<ITeamPlayer> team1AllPlayers;
    private List<ITeamPlayer> team1Forwards;
    private List<ITeamPlayer> team1Defensemen;
    private List<ITeamPlayer> team1Goalies;
    private List<ITeamPlayer> team2AllPlayers;
    private List<ITeamPlayer> team2Forwards;
    private List<ITeamPlayer> team2Defensemen;
    private List<ITeamPlayer> team2Goalies;
    private final IGameSimulationConfig gameSimulationConfig;
    private final ITrophySystemFactory trophySystemFactory;
    private IPerformanceObserver goalScorerObserver;
    private IPerformanceObserver goalieObserver;
    private IPerformanceObserver defenceMenObserver;
    private IPerformanceObserver playerObserver;

    public GameSimulation(ITeam teamA, ITeam teamB, IGameSimulationConfig config) {
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        trophySystemFactory =AbstractAppFactory.getTrophySystemFactory();
        team1 = teamA;
        team2 = teamB;
        gameSimulationConfig = config;
        gameStats = leagueManagerFactory.createGameStats();
        initialize();
        goalScorerObserver = trophySystemFactory.goalScorerObserver();
        goalieObserver = trophySystemFactory.goalieObserver();
        defenceMenObserver = trophySystemFactory.defenceMenObserver();
        playerObserver = trophySystemFactory.playerObserver();
    }

    @Override
    public IGameStats getGameStats() {
        return gameStats;
    }

    @Override
    public void setGameStats(IGameStats gameStats) {
        this.gameStats = gameStats;
    }

    private void initialize() {
        team1AllPlayers = team1.getPlayers();
        team1Forwards = team1AllPlayers.stream().filter(p -> p.getPlayerStats().getPosition().equalsIgnoreCase(PlayerPosition.FORWARD.toString())).collect(Collectors.toList());
        team1Defensemen = team1AllPlayers.stream().filter(p -> p.getPlayerStats().getPosition().equalsIgnoreCase(PlayerPosition.DEFENSE.toString())).collect(Collectors.toList());
        team1Goalies = team1AllPlayers.stream().filter(p -> p.getPlayerStats().getPosition().equalsIgnoreCase(PlayerPosition.GOALIE.toString())).collect(Collectors.toList());
        team2AllPlayers = team2.getPlayers();
        team2Forwards = team2AllPlayers.stream().filter(p -> p.getPlayerStats().getPosition().equalsIgnoreCase(PlayerPosition.FORWARD.toString())).collect(Collectors.toList());
        team2Defensemen = team2AllPlayers.stream().filter(p -> p.getPlayerStats().getPosition().equalsIgnoreCase(PlayerPosition.DEFENSE.toString())).collect(Collectors.toList());
        team2Goalies = team2AllPlayers.stream().filter(p -> p.getPlayerStats().getPosition().equalsIgnoreCase(PlayerPosition.GOALIE.toString())).collect(Collectors.toList());
    }

    @Override
    public void simulateGame() {
        calculateShotsInGame();
        calculatePenaltiesInGame();
        calculateSavesInGame();
        calculateGoalsInGame();
        attributeGameEventsToPlayers();
        LOGGER.info("Game Stats: " + team1.getTeamName() + " vs " + team2.getTeamName());
        LOGGER.info("TeamA Goals: " + gameStats.getTeam1Goals());
        LOGGER.info("TeamA Penalties: " + gameStats.getTeam1Penalties());
        LOGGER.info("TeamA Shots: " + gameStats.getTeam1Shots());
        LOGGER.info("TeamA Saves: " + gameStats.getTeam1Saves());
        LOGGER.info("TeamB Goals: " + gameStats.getTeam2Goals());
        LOGGER.info("TeamB Penalties: " + gameStats.getTeam2Penalties());
        LOGGER.info("TeamB Shots: " + gameStats.getTeam2Shots());
        LOGGER.info("TeamB Saves: " + gameStats.getTeam2Saves());

    }

    private void calculateShotsInGame() {
        int totalSkatingStatsForTeam1 = 0;
        int totalSkatingStatsForTeam2 = 0;
        float averageSkatingStatsForTeam1;
        float averageSkatingStatsForTeam2;
        int difference;
        for (ITeamPlayer teamPlayer: team1AllPlayers) {
            totalSkatingStatsForTeam1 += teamPlayer.getPlayerStats().getSkating();
        }
        for (ITeamPlayer teamPlayer: team2AllPlayers) {
            totalSkatingStatsForTeam2 += teamPlayer.getPlayerStats().getSkating();
        }

        gameStats.setTeam1Shots(gameSimulationConfig.getAverageShotsPerTeam());
        gameStats.setTeam2Shots(gameSimulationConfig.getAverageShotsPerTeam());
        averageSkatingStatsForTeam1 = (float) totalSkatingStatsForTeam1 / team1AllPlayers.size();
        averageSkatingStatsForTeam2 = (float) totalSkatingStatsForTeam2 / team2AllPlayers.size();
        difference = Math.round(averageSkatingStatsForTeam1 - averageSkatingStatsForTeam2);

        if (difference < -10) {
            difference = -10;
        } else if (difference > 10) {
            difference = 10;
        }

        gameStats.setTeam1Shots(gameStats.getTeam1Shots() + difference);
        gameStats.setTeam2Shots(gameStats.getTeam2Shots() - difference);
    }

    private void calculatePenaltiesInGame() {
        int totalCheckingStatsForTeam1 = 0;
        int totalCheckingStatsForTeam2 = 0;
        float averageCheckingStatsForTeam1;
        float averageCheckingStatsForTeam2;

        for (ITeamPlayer teamPlayer: team1Defensemen) {
            totalCheckingStatsForTeam1 += teamPlayer.getPlayerStats().getChecking();
        }
        for (ITeamPlayer teamPlayer: team2Defensemen) {
            totalCheckingStatsForTeam2 += teamPlayer.getPlayerStats().getChecking();
        }

        averageCheckingStatsForTeam1 = (float) totalCheckingStatsForTeam1 / team1Defensemen.size();
        averageCheckingStatsForTeam2 = (float) totalCheckingStatsForTeam2 / team2Defensemen.size();

        gameStats.setTeam1Penalties(gameSimulationConfig.getAveragePenaltiesPerTeam());
        gameStats.setTeam2Penalties(gameSimulationConfig.getAveragePenaltiesPerTeam());

        if (averageCheckingStatsForTeam1 > 17) {
            gameStats.setTeam1Penalties(gameStats.getTeam1Penalties() + 2);
        } else if (averageCheckingStatsForTeam1 > 14 && averageCheckingStatsForTeam1 < 17) {
            gameStats.setTeam1Penalties(gameStats.getTeam1Penalties() + 1);
        } else if (averageCheckingStatsForTeam1 > 7 && averageCheckingStatsForTeam1 < 10) {
            gameStats.setTeam1Penalties(gameStats.getTeam1Penalties() - 1);
        } else if (averageCheckingStatsForTeam1 < 7) {
            gameStats.setTeam1Penalties(gameStats.getTeam1Penalties() - 2);
        }

        if (averageCheckingStatsForTeam2 > 17) {
            gameStats.setTeam2Penalties(gameStats.getTeam2Penalties() + 2);
        } else if (averageCheckingStatsForTeam2 > 14 && averageCheckingStatsForTeam2 < 17) {
            gameStats.setTeam2Penalties(gameStats.getTeam2Penalties() + 1);
        } else if (averageCheckingStatsForTeam2 > 7 && averageCheckingStatsForTeam2 < 10) {
            gameStats.setTeam2Penalties(gameStats.getTeam2Penalties() - 1);
        } else if (averageCheckingStatsForTeam2 < 7) {
            gameStats.setTeam2Penalties(gameStats.getTeam2Penalties() - 2);
        }
    }

    private void calculateSavesInGame() {
        int totalSavingStatsForTeam1 = 0;
        int totalSavingStatsForTeam2 = 0;
        float averageSavingStatsForTeam1;
        float averageSavingStatsForTeam2;

        for (ITeamPlayer teamPlayer: team1Goalies) {
            totalSavingStatsForTeam1 += teamPlayer.getPlayerStats().getChecking();
        }
        for (ITeamPlayer teamPlayer: team2Goalies) {
            totalSavingStatsForTeam2 += teamPlayer.getPlayerStats().getChecking();
        }

        averageSavingStatsForTeam1 = (float) totalSavingStatsForTeam1 / team1Goalies.size();
        averageSavingStatsForTeam2 = (float) totalSavingStatsForTeam2 / team2Goalies.size();

        gameStats.setTeam1Saves(gameStats.getTeam2Shots() - gameSimulationConfig.getAverageGoalsPerTeam());
        gameStats.setTeam2Saves(gameStats.getTeam1Shots() - gameSimulationConfig.getAverageGoalsPerTeam());

        if (averageSavingStatsForTeam1 > 17) {
            gameStats.setTeam1Saves(gameStats.getTeam1Saves() + 2);
        } else if (averageSavingStatsForTeam1 > 14 && averageSavingStatsForTeam1 < 17) {
            gameStats.setTeam1Saves(gameStats.getTeam1Saves() + 1);
        } else if (averageSavingStatsForTeam1 > 7 && averageSavingStatsForTeam1 < 10) {
            gameStats.setTeam1Saves(gameStats.getTeam1Saves() - 1);
        } else if (averageSavingStatsForTeam1 < 7) {
            gameStats.setTeam1Saves(gameStats.getTeam1Saves() - 2);
        }

        if (averageSavingStatsForTeam2 > 17) {
            gameStats.setTeam2Saves(gameStats.getTeam2Saves() + 2);
        } else if (averageSavingStatsForTeam2 > 14 && averageSavingStatsForTeam2 < 17) {
            gameStats.setTeam2Saves(gameStats.getTeam2Saves() + 1);
        } else if (averageSavingStatsForTeam2 > 7 && averageSavingStatsForTeam2 < 10) {
            gameStats.setTeam2Saves(gameStats.getTeam2Saves() - 1);
        } else if (averageSavingStatsForTeam2 < 7) {
            gameStats.setTeam2Saves(gameStats.getTeam2Saves() - 2);
        }
    }

    private void calculateGoalsInGame() {
        gameStats.setTeam1Goals(gameStats.getTeam1Shots() - gameStats.getTeam2Saves());
        gameStats.setTeam2Goals(gameStats.getTeam2Shots() - gameStats.getTeam1Saves());
        if(gameStats.getTeam1Goals() < 0) {
            gameStats.setTeam1Goals(0);
        }
        if (gameStats.getTeam2Goals() < 0) {
            gameStats.setTeam2Goals(0);
        }
    }

    private void attributeGameEventsToPlayers() {
        IRandomChance randomChance = leagueManagerFactory.createRandomChance();

        int numberOfTeam1Forwards = team1Forwards.size();
        int numberOfTeam1Defensemen = team1Defensemen.size();
        int numberOfTeam1Goalies = team1Goalies.size();
        int numberOfTeam2Forwards = team2Forwards.size();
        int numberOfTeam2Defensemen = team2Defensemen.size();
        int numberOfTeam2Goalies = team2Goalies.size();

        for (int i = 0; i < gameStats.getTeam1Goals(); i++) {
            int playerIndex = randomChance.getRandomIntegerNumber(0, numberOfTeam1Forwards - 1);
            this.attachObserver(goalScorerObserver);
            this.score = 1;
            this.performerName = team1Forwards.get(playerIndex).getPlayerName();
            this.notifyObserver();
            LOGGER.info("Goal scored by " + team1Forwards.get(playerIndex).getPlayerName() + " of " + team1.getTeamName());
        }
        for (int i = 0; i < gameStats.getTeam2Goals(); i++) {
            int playerIndex = randomChance.getRandomIntegerNumber(0, numberOfTeam2Forwards - 1);
            this.attachObserver(goalScorerObserver);
            this.score = 1;
            this.performerName = team1Forwards.get(playerIndex).getPlayerName();
            this.notifyObserver();
            LOGGER.info("Goal scored by " + team2Forwards.get(playerIndex).getPlayerName() + " of " + team2.getTeamName());
        }

        for (int i = 0; i < gameStats.getTeam1Penalties(); i++) {
            int playerIndex = randomChance.getRandomIntegerNumber(0, numberOfTeam1Defensemen - 1);
            this.attachObserver(defenceMenObserver);
            this.score = 1;
            this.performerName = team1Defensemen.get(playerIndex).getPlayerName();
            this.notifyObserver();
            LOGGER.info("Penalty awarded to " + team1Defensemen.get(playerIndex).getPlayerName() + " of " + team1.getTeamName());
        }
        for (int i = 0; i < gameStats.getTeam2Penalties(); i++) {
            int playerIndex = randomChance.getRandomIntegerNumber(0, numberOfTeam2Defensemen - 1);
            this.attachObserver(defenceMenObserver);
            this.score = 1;
            this.performerName = team1Defensemen.get(playerIndex).getPlayerName();
            this.notifyObserver();
            LOGGER.info("Penalty awarded to " + team2Defensemen.get(playerIndex).getPlayerName() + " of " + team2.getTeamName());
        }

        for (int i = 0; i < gameStats.getTeam1Saves(); i++) {
            int playerIndex = randomChance.getRandomIntegerNumber(0, numberOfTeam1Goalies - 1);
            this.attachObserver(goalieObserver);
            this.score = 1;
            this.performerName = team1Goalies.get(playerIndex).getPlayerName();
            this.notifyObserver();
            LOGGER.info("Goal saved by " + team1Goalies.get(playerIndex).getPlayerName() + " of " + team1.getTeamName());
        }
        for (int i = 0; i < gameStats.getTeam2Saves(); i++) {
            int playerIndex = randomChance.getRandomIntegerNumber(0, numberOfTeam1Goalies - 1);
            this.attachObserver(goalieObserver);
            this.score = 1;
            this.performerName = team1Goalies.get(playerIndex).getPlayerName();
            this.notifyObserver();
            LOGGER.info("Goal saved by " + team2Goalies.get(playerIndex).getPlayerName() + " of " + team2.getTeamName());
        }
    }
}
