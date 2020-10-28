package com.IceHockeyLeague.LeagueManager.Player;

import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IInjuryConfig;

import java.time.LocalDate;

public interface IPlayerInjuryManager {
    boolean isInjured(IPlayer player, IInjuryConfig injuryConfig, LocalDate currentDate);
    boolean isRecovered(IPlayer player, LocalDate currentDate);
}
