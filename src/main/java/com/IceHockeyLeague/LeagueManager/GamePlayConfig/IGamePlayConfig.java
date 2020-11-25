package com.IceHockeyLeague.LeagueManager.GamePlayConfig;

public interface IGamePlayConfig {
    int getGamePlayConfigID();
    void setGamePlayConfigID(int id);

    int getLeagueID();
    void setLeagueID(int id);

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

}
