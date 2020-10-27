package com.IceHockeyLeagueTest.LeagueManagerTest.GamePlayConfigTest;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.*;

public class GamePlayConfigDBMock implements IGamePlayConfigPersistence {

    @Override
    public boolean saveGamePlayConfig(IGamePlayConfig gamePlayConfig) {
        gamePlayConfig.setGamePlayConfigID(2);
        gamePlayConfig.setLeagueID(2);

        IAgingConfig agingConfig = AbstractLeagueManagerFactory.getFactory().getAgingConfig();
        agingConfig.setAverageRetirementAge(33);
        agingConfig.setMaximumAge(57);
        gamePlayConfig.setAgingConfig(agingConfig);

        IGameResolverConfig gameResolverConfig = AbstractLeagueManagerFactory.getFactory().getGameResolverConfig();
        gameResolverConfig.setRandomWinChance(0.2f);
        gamePlayConfig.setGameResolverConfig(gameResolverConfig);

        IInjuryConfig injuryConfig = AbstractLeagueManagerFactory.getFactory().getInjuryConfig();
        injuryConfig.setRandomInjuryChance(0.14f);
        injuryConfig.setInjuryDaysLow(10);
        injuryConfig.setInjuryDaysHigh(340);
        gamePlayConfig.setInjuryConfig(injuryConfig);

        ITrainingConfig trainingConfig = AbstractLeagueManagerFactory.getFactory().getTrainingConfig();
        trainingConfig.setDaysUntilStatIncreaseCheck(150);
        gamePlayConfig.setTrainingConfig(trainingConfig);

        ITradingConfig tradingConfig = AbstractLeagueManagerFactory.getFactory().getTradingConfig();
        tradingConfig.setLossPoint(6);
        tradingConfig.setRandomTradeOfferChance(0.45f);
        tradingConfig.setMaxPlayersPerTrade(4);
        tradingConfig.setRandomAcceptanceChance(0.11f);
        gamePlayConfig.setTradingConfig(tradingConfig);

        return true;
    }

    @Override
    public boolean loadGamePlayConfig(int leagueId, IGamePlayConfig gamePlayConfig) {
        gamePlayConfig.setGamePlayConfigID(1);
        gamePlayConfig.setLeagueID(leagueId);

        IAgingConfig agingConfig = AbstractLeagueManagerFactory.getFactory().getAgingConfig();
        agingConfig.setAverageRetirementAge(35);
        agingConfig.setMaximumAge(50);
        gamePlayConfig.setAgingConfig(agingConfig);

        IGameResolverConfig gameResolverConfig = AbstractLeagueManagerFactory.getFactory().getGameResolverConfig();
        gameResolverConfig.setRandomWinChance(0.1f);
        gamePlayConfig.setGameResolverConfig(gameResolverConfig);

        IInjuryConfig injuryConfig = AbstractLeagueManagerFactory.getFactory().getInjuryConfig();
        injuryConfig.setRandomInjuryChance(0.05f);
        injuryConfig.setInjuryDaysLow(1);
        injuryConfig.setInjuryDaysHigh(260);
        gamePlayConfig.setInjuryConfig(injuryConfig);

        ITrainingConfig trainingConfig = AbstractLeagueManagerFactory.getFactory().getTrainingConfig();
        trainingConfig.setDaysUntilStatIncreaseCheck(100);
        gamePlayConfig.setTrainingConfig(trainingConfig);

        ITradingConfig tradingConfig = AbstractLeagueManagerFactory.getFactory().getTradingConfig();
        tradingConfig.setLossPoint(8);
        tradingConfig.setRandomTradeOfferChance(0.05f);
        tradingConfig.setMaxPlayersPerTrade(2);
        tradingConfig.setRandomAcceptanceChance(0.05f);
        gamePlayConfig.setTradingConfig(tradingConfig);

        return true;
    }
}
