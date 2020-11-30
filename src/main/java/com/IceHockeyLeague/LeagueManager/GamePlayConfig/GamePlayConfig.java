package com.IceHockeyLeague.LeagueManager.GamePlayConfig;

public class GamePlayConfig implements IGamePlayConfig {
    private int gamePlayConfigId;
    private int leagueId;
    private IAgingConfig agingConfig;
    private IGameResolverConfig gameResolverConfig;
    private ITradingConfig tradingConfig;
    private ITrainingConfig trainingConfig;
    private IInjuryConfig injuryConfig;

    public GamePlayConfig() {
        gamePlayConfigId = -1;
        leagueId = -1;
    }

    @Override
    public int getGamePlayConfigId() {
        return gamePlayConfigId;
    }

    @Override
    public void setGamePlayConfigId(int gamePlayConfigId) {
        this.gamePlayConfigId = gamePlayConfigId;
    }

    @Override
    public int getLeagueId() {
        return leagueId;
    }

    @Override
    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    @Override
    public void setAgingConfig(IAgingConfig agingConfig) {
        if (agingConfig == null) {
            throw new IllegalArgumentException("Please provide a valid concrete implementation of IAgingConfig");
        }
        this.agingConfig = agingConfig;
    }

    @Override
    public IAgingConfig getAgingConfig() {
        return agingConfig;
    }

    @Override
    public void setGameResolverConfig(IGameResolverConfig gameResolverConfig) {
        if (gameResolverConfig == null) {
            throw new IllegalArgumentException("Please provide a valid concrete implementation of IGameResolverConfig");
        }
        this.gameResolverConfig = gameResolverConfig;
    }

    @Override
    public IGameResolverConfig getGameResolverConfig() {
        return gameResolverConfig;
    }

    @Override
    public void setInjuryConfig(IInjuryConfig injuryConfig) {
        if (injuryConfig == null) {
            throw new IllegalArgumentException("Please provide a valid concrete implementation of IInjuryConfig");
        }
        this.injuryConfig = injuryConfig;
    }

    @Override
    public IInjuryConfig getInjuryConfig() {
        return injuryConfig;
    }

    @Override
    public void setTrainingConfig(ITrainingConfig trainingConfig) {
        if (trainingConfig == null) {
            throw new IllegalArgumentException("Please provide a valid concrete implementation of ITrainingConfig");
        }
        this.trainingConfig = trainingConfig;
    }

    @Override
    public ITrainingConfig getTrainingConfig() {
        return trainingConfig;
    }

    @Override
    public void setTradingConfig(ITradingConfig tradingConfig) {
        if (tradingConfig == null) {
            throw new IllegalArgumentException("Please provide a valid concrete implementation of ITradingConfig");
        }
        this.tradingConfig = tradingConfig;
    }

    @Override
    public ITradingConfig getTradingConfig() {
        return tradingConfig;
    }

}
