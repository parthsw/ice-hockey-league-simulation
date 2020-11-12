package com.IceHockeyLeague.LeagueManager;

import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.*;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Team.ITeamStrengthCalculator;
import com.IceHockeyLeague.LeagueManager.Player.*;
import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
import com.IceHockeyLeague.LeagueManager.Coach.ICoachStats;
import com.IceHockeyLeague.LeagueManager.Manager.IManager;


public interface ILeagueManagerFactory {

    ILeagueCreator createLeagueCreator();

    ILeague createLeague();

    IConference createConference();

    IDivision createDivision();

    ITeam createTeam();

    ITeamStrengthCalculator createTeamStrengthCalculator();

    IPlayer createPlayer();

    IPlayerStats createPlayerStats();

    IPlayerCareerProgression createPlayerCareerProgression(IRandomChance randomChance);

    ITeamPlayer createTeamPlayer();

    IFreeAgent createFreeAgent();

    ICoach createCoach();

    ICoachStats createCoachStats();

    IManager createManager();

    IGamePlayConfig createGamePlayConfig();

    IAgingConfig createAgingConfig();

    IGameResolverConfig createGameResolverConfig();

    IInjuryConfig createInjuryConfig();

    ITrainingConfig createTrainingConfig();

    ITradingConfig createTradingConfig();

    IRandomChance createRandomChance();
}
