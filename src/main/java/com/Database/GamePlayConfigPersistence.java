package com.Database;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.*;

import java.sql.*;

public class GamePlayConfigPersistence implements IGamePlayConfigPersistence {

    @Override
    public boolean saveGamePlayConfig(IGamePlayConfig gamePlayConfig) {
        DBConnection connectionManager = null;
        Connection connection = null;
        String gamePlayConfigID = null;
        CallableStatement myCall;

        try {
            connectionManager = AbstractDatabaseFactory.getFactory().getDBConnection();
            connection = connectionManager.getConnection();
            myCall = connection.prepareCall("{call insertIntoGamePlayConfig(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
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
            myCall.close();
            gamePlayConfig.setGamePlayConfigID(Integer.parseInt(gamePlayConfigID));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in insert game play configuration");
            return false;
        } finally {
            if (connection != null) {
                connectionManager.terminateConnection();
            }
        }
    }

    @Override
    public boolean loadGamePlayConfig(int leagueId, IGamePlayConfig gamePlayConfig) {
        DBConnection connectionManager = null;
        Connection connection = null;
        CallableStatement myCall;

        try {
            connectionManager = AbstractDatabaseFactory.getFactory().getDBConnection();
            connection = connectionManager.getConnection();

            myCall = connection.prepareCall("{call loadLeagueGamePlayConfig(?)}");
            myCall.setInt(1, leagueId);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                gamePlayConfig.setGamePlayConfigID(Integer.parseInt("gamePlayConfigID"));
                gamePlayConfig.setLeagueID(leagueId);

                IAgingConfig agingConfig = AbstractLeagueManagerFactory.getFactory().getAgingConfig();
                agingConfig.setAverageRetirementAge(Integer.parseInt("averageRetirementAge"));
                agingConfig.setMaximumAge(Integer.parseInt("maximumAge"));
                gamePlayConfig.setAgingConfig(agingConfig);

                IGameResolverConfig gameResolverConfig = AbstractLeagueManagerFactory.getFactory().getGameResolverConfig();
                gameResolverConfig.setRandomWinChance(Float.parseFloat("randomWinChance"));
                gamePlayConfig.setGameResolverConfig(gameResolverConfig);

                IInjuryConfig injuryConfig = AbstractLeagueManagerFactory.getFactory().getInjuryConfig();
                injuryConfig.setRandomInjuryChance(Float.parseFloat("randomInjuryChance"));
                injuryConfig.setInjuryDaysLow(Integer.parseInt("injuryDaysLow"));
                injuryConfig.setInjuryDaysHigh(Integer.parseInt("injuryDaysHigh"));
                gamePlayConfig.setInjuryConfig(injuryConfig);

                ITrainingConfig trainingConfig = AbstractLeagueManagerFactory.getFactory().getTrainingConfig();
                trainingConfig.setDaysUntilStatIncreaseCheck(Integer.parseInt("daysUntilStatIncreaseCheck"));
                gamePlayConfig.setTrainingConfig(trainingConfig);

                ITradingConfig tradingConfig = AbstractLeagueManagerFactory.getFactory().getTradingConfig();
                tradingConfig.setLossPoint(Integer.parseInt("lossPoint"));
                tradingConfig.setRandomTradeOfferChance(Float.parseFloat("randomTradeOfferChance"));
                tradingConfig.setMaxPlayersPerTrade(Integer.parseInt("maxPlayersPerTrade"));
                tradingConfig.setRandomAcceptanceChance(Float.parseFloat("randomAcceptanceChance"));
                gamePlayConfig.setTradingConfig(tradingConfig);
            }
            myCall.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in loading game play configuration");
            return false;
        } finally {
            if (connection != null) {
                connectionManager.terminateConnection();
            }
        }
    }
}
