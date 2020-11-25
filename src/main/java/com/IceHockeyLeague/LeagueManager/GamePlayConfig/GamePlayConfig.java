package com.IceHockeyLeague.LeagueManager.GamePlayConfig;

public class GamePlayConfig implements IGamePlayConfig {
    private int gamePlayConfigID;
    private int leagueID;
    private IAgingConfig agingConfig;
    private IGameResolverConfig gameResolverConfig;
    private ITradingConfig tradingConfig;
    private ITrainingConfig trainingConfig;
    private IInjuryConfig injuryConfig;

    public GamePlayConfig() {
        gamePlayConfigID = -1;
        leagueID = -1;
    }

    @Override
    public int getGamePlayConfigID() {
        return gamePlayConfigID;
    }

    @Override
    public void setGamePlayConfigID(int id) {
        gamePlayConfigID = id;
    }

    @Override
    public int getLeagueID() {
        return leagueID;
    }

    @Override
    public void setLeagueID(int id) {
        leagueID = id;
    }

    @Override
    public void setAgingConfig(IAgingConfig agingConfig) {
        this.agingConfig = agingConfig;
    }

    @Override
    public IAgingConfig getAgingConfig() {
        return agingConfig;
    }

    @Override
    public void setGameResolverConfig(IGameResolverConfig gameResolverConfig) {
        this.gameResolverConfig = gameResolverConfig;
    }

    @Override
    public IGameResolverConfig getGameResolverConfig() {
        return gameResolverConfig;
    }

    @Override
    public void setInjuryConfig(IInjuryConfig injuryConfig) {
        this.injuryConfig = injuryConfig;
    }

    @Override
    public IInjuryConfig getInjuryConfig() {
        return injuryConfig;
    }

    @Override
    public void setTrainingConfig(ITrainingConfig trainingConfig) {
        this.trainingConfig = trainingConfig;
    }

    @Override
    public ITrainingConfig getTrainingConfig() {
        return trainingConfig;
    }

    @Override
    public void setTradingConfig(ITradingConfig tradingConfig) {
        this.tradingConfig = tradingConfig;
    }

    @Override
    public ITradingConfig getTradingConfig() {
        return tradingConfig;
    }

}
