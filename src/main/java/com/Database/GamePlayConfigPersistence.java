package com.Database;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.*;

import java.sql.*;

public class GamePlayConfigPersistence implements IGamePlayConfigPersistence {
    private final IDatabaseFactory databaseFactory;
    private final ILeagueManagerFactory leagueManagerFactory;

    public GamePlayConfigPersistence() {
        databaseFactory = AbstractAppFactory.getDatabaseFactory();
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
    }

    @Override
    public boolean saveGamePlayConfig(IGamePlayConfig gamePlayConfig) {
        IStoredProcedure storedProcedure = null;
        CallableStatement myCall;
        String gamePlayConfigID = null;

        try {
            storedProcedure = databaseFactory.createStoredProcedure();
            myCall = storedProcedure.setup("insertIntoGamePlayConfig(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            myCall.setInt(1, gamePlayConfig.getLeagueID());

            IAgingConfig agingConfig = gamePlayConfig.getAgingConfig();
            myCall.setInt(2, agingConfig.getAverageRetirementAge());
            myCall.setInt(3, agingConfig.getMaximumAge());

            IGameResolverConfig gameResolverConfig = gamePlayConfig.getGameResolverConfig();
            myCall.setFloat(4, gameResolverConfig.getRandomWinChance());

            IInjuryConfig injuryConfig = gamePlayConfig.getInjuryConfig();
            myCall.setFloat(5, injuryConfig.getRandomInjuryChance());
            myCall.setInt(6, injuryConfig.getInjuryDaysLow());
            myCall.setInt(7, injuryConfig.getInjuryDaysHigh());

            ITrainingConfig trainingConfig = gamePlayConfig.getTrainingConfig();
            myCall.setInt(8, trainingConfig.getDaysUntilStatIncreaseCheck());

            ITradingConfig tradingConfig = gamePlayConfig.getTradingConfig();
            myCall.setInt(9, tradingConfig.getLossPoint());
            myCall.setFloat(10, tradingConfig.getRandomTradeOfferChance());
            myCall.setInt(11, tradingConfig.getMaxPlayersPerTrade());
            myCall.setFloat(12, tradingConfig.getRandomAcceptanceChance());

            myCall.registerOutParameter(13, Types.INTEGER);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                gamePlayConfigID = result.getString("gamePlayConfigID");
            }
            gamePlayConfig.setGamePlayConfigID(Integer.parseInt(gamePlayConfigID));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in insert game play configuration");
            return false;
        } finally {
            storedProcedure.cleanup();
        }
    }

    @Override
    public boolean loadGamePlayConfig(int leagueId, IGamePlayConfig gamePlayConfig) {
        IStoredProcedure storedProcedure = null;
        CallableStatement myCall;

        try {
            storedProcedure = databaseFactory.createStoredProcedure();
            myCall = storedProcedure.setup("loadLeagueGamePlayConfig(?)");

            myCall.setInt(1, leagueId);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                gamePlayConfig.setGamePlayConfigID(result.getInt("gamePlayConfigID"));
                gamePlayConfig.setLeagueID(leagueId);

                IAgingConfig agingConfig = leagueManagerFactory.createAgingConfig();
                agingConfig.setAverageRetirementAge(result.getInt("averageRetirementAge"));
                agingConfig.setMaximumAge(result.getInt("maximumAge"));
                gamePlayConfig.setAgingConfig(agingConfig);

                IGameResolverConfig gameResolverConfig = leagueManagerFactory.createGameResolverConfig();
                gameResolverConfig.setRandomWinChance(result.getFloat("randomWinChance"));
                gamePlayConfig.setGameResolverConfig(gameResolverConfig);

                IInjuryConfig injuryConfig = leagueManagerFactory.createInjuryConfig();
                injuryConfig.setRandomInjuryChance(result.getFloat("randomInjuryChance"));
                injuryConfig.setInjuryDaysLow(result.getInt("injuryDaysLow"));
                injuryConfig.setInjuryDaysHigh(result.getInt("injuryDaysHigh"));
                gamePlayConfig.setInjuryConfig(injuryConfig);

                ITrainingConfig trainingConfig = leagueManagerFactory.createTrainingConfig();
                trainingConfig.setDaysUntilStatIncreaseCheck(result.getInt("daysUntilStatIncreaseCheck"));
                gamePlayConfig.setTrainingConfig(trainingConfig);

                ITradingConfig tradingConfig = leagueManagerFactory.createTradingConfig();
                tradingConfig.setLossPoint(result.getInt("lossPoint"));
                tradingConfig.setRandomTradeOfferChance(result.getFloat("randomTradeOfferChance"));
                tradingConfig.setMaxPlayersPerTrade(result.getInt("maxPlayersPerTrade"));
                tradingConfig.setRandomAcceptanceChance(result.getFloat("randomAcceptanceChance"));
                gamePlayConfig.setTradingConfig(tradingConfig);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in loading game play configuration");
            return false;
        } finally {
            storedProcedure.cleanup();
        }
    }
}
