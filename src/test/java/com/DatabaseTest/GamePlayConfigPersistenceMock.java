package com.DatabaseTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.*;

public class GamePlayConfigPersistenceMock implements IGamePlayConfigPersistence {
    private final ILeagueManagerFactory leagueManagerFactory;

    public GamePlayConfigPersistenceMock() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
    }

    @Override
    public boolean saveGamePlayConfig(IGamePlayConfig gamePlayConfig) {
        gamePlayConfig.setGamePlayConfigId(2);
        gamePlayConfig.setLeagueId(2);

        IAgingConfig agingConfig = leagueManagerFactory.createAgingConfig();
        agingConfig.setAverageRetirementAge(33);
        agingConfig.setMaximumAge(57);
        gamePlayConfig.setAgingConfig(agingConfig);

        IGameResolverConfig gameResolverConfig = leagueManagerFactory.createGameResolverConfig();
        gameResolverConfig.setRandomWinChance(0.2f);
        gamePlayConfig.setGameResolverConfig(gameResolverConfig);

        IInjuryConfig injuryConfig = leagueManagerFactory.createInjuryConfig();
        injuryConfig.setRandomInjuryChance(0.14f);
        injuryConfig.setInjuryDaysLow(10);
        injuryConfig.setInjuryDaysHigh(340);
        gamePlayConfig.setInjuryConfig(injuryConfig);

        ITrainingConfig trainingConfig = leagueManagerFactory.createTrainingConfig();
        trainingConfig.setDaysUntilStatIncreaseCheck(150);
        gamePlayConfig.setTrainingConfig(trainingConfig);

        ITradingConfig tradingConfig = leagueManagerFactory.createTradingConfig();
        tradingConfig.setLossPoint(6);
        tradingConfig.setRandomTradeOfferChance(0.45f);
        tradingConfig.setMaxPlayersPerTrade(4);
        tradingConfig.setRandomAcceptanceChance(0.11f);
        gamePlayConfig.setTradingConfig(tradingConfig);

        return true;
    }

    @Override
    public boolean loadGamePlayConfig(int leagueId, IGamePlayConfig gamePlayConfig) {
        gamePlayConfig.setGamePlayConfigId(1);
        gamePlayConfig.setLeagueId(leagueId);

        IAgingConfig agingConfig = leagueManagerFactory.createAgingConfig();
        agingConfig.setAverageRetirementAge(35);
        agingConfig.setMaximumAge(50);
        gamePlayConfig.setAgingConfig(agingConfig);

        IGameResolverConfig gameResolverConfig = leagueManagerFactory.createGameResolverConfig();
        gameResolverConfig.setRandomWinChance(0.1f);
        gamePlayConfig.setGameResolverConfig(gameResolverConfig);

        IInjuryConfig injuryConfig = leagueManagerFactory.createInjuryConfig();
        injuryConfig.setRandomInjuryChance(0.05f);
        injuryConfig.setInjuryDaysLow(1);
        injuryConfig.setInjuryDaysHigh(260);
        gamePlayConfig.setInjuryConfig(injuryConfig);

        ITrainingConfig trainingConfig = leagueManagerFactory.createTrainingConfig();
        trainingConfig.setDaysUntilStatIncreaseCheck(100);
        gamePlayConfig.setTrainingConfig(trainingConfig);

        ITradingConfig tradingConfig = leagueManagerFactory.createTradingConfig();
        tradingConfig.setLossPoint(8);
        tradingConfig.setRandomTradeOfferChance(0.05f);
        tradingConfig.setMaxPlayersPerTrade(2);
        tradingConfig.setRandomAcceptanceChance(0.05f);
        gamePlayConfig.setTradingConfig(tradingConfig);

        return true;
    }
}
