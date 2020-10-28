package com.IceHockeyLeague.LeagueManager.GamePlayConfig;

public interface IGamePlayConfigPersistence {
    boolean saveGamePlayConfig(IGamePlayConfig gamePlayConfig);
    boolean loadGamePlayConfig(int leagueId, IGamePlayConfig gamePlayConfig);
}
