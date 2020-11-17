package com.IceHockeyLeague.LeagueManager.Player;

import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IAgingConfig;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IInjuryConfig;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.time.LocalDate;

public interface IPlayerCareerProgression {
    boolean isInjured(IPlayer player, IInjuryConfig injuryConfig, LocalDate currentDate);

    boolean isRecovered(IPlayer player, LocalDate currentDate);

    boolean isRetired(IPlayer player, IAgingConfig agingConfig, LocalDate currentDate);

    boolean handleFreeAgentRetirement(IFreeAgent freeAgent, ILeague league);

    boolean handleTeamPlayerRetirement(ITeamPlayer teamPlayer, ITeam team, ILeague league);
}
