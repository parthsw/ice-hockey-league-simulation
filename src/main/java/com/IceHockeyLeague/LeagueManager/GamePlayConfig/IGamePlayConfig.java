package com.IceHockeyLeague.LeagueManager.GamePlayConfig;

public interface IGamePlayConfig {
    int getGamePlayConfigId();

    void setGamePlayConfigId(int gamePlayConfigId);

    int getLeagueId();

    void setLeagueId(int leagueId);

    void setAgingConfig(IAgingConfig agingConfig);

    IAgingConfig getAgingConfig();

    void setGameResolverConfig(IGameResolverConfig gameResolverConfig);

    IGameResolverConfig getGameResolverConfig();

    void setInjuryConfig(IInjuryConfig injuryConfig);

    IInjuryConfig getInjuryConfig();

    void setTrainingConfig(ITrainingConfig trainingConfig);

    ITrainingConfig getTrainingConfig();

    void setTradingConfig(ITradingConfig tradingConfig);

    ITradingConfig getTradingConfig();

    boolean saveGamePlayConfig(IGamePlayConfigPersistence gamePlayConfigDb);

    boolean loadGamePlayConfig(IGamePlayConfigPersistence gamePlayConfigDb, IGamePlayConfig gamePlayConfig);
}
