package com.IceHockeyLeague.LeagueManager;

import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
import com.IceHockeyLeague.LeagueManager.Coach.ICoachStats;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.Draft.DraftPick.IDraftPick;
import com.IceHockeyLeague.LeagueManager.Draft.DraftPick.IDraftPickManager;
import com.IceHockeyLeague.LeagueManager.Draft.IDraftManager;
import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.GameSimulator.IGameSimulation;
import com.IceHockeyLeague.LeagueManager.GameSimulator.IGameSimulationConfig;
import com.IceHockeyLeague.LeagueManager.GameSimulator.IGameSimulationSystem;
import com.IceHockeyLeague.LeagueManager.GameSimulator.IGameStats;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.*;
import com.IceHockeyLeague.LeagueManager.Manager.IManager;
import com.IceHockeyLeague.LeagueManager.Player.*;
import com.IceHockeyLeague.LeagueManager.Scheduler.ISchedule;
import com.IceHockeyLeague.LeagueManager.Scheduler.IScheduleSystem;
import com.IceHockeyLeague.LeagueManager.Standings.IStanding;
import com.IceHockeyLeague.LeagueManager.Standings.IStandingSystem;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Team.ITeamStrengthCalculator;
import com.IceHockeyLeague.LeagueManager.Team.ITeamTraining;
import com.IceHockeyLeague.LeagueManager.Team.Roster.IActiveRoster;
import com.IceHockeyLeague.LeagueManager.Team.Roster.IInactiveRoster;
import com.IceHockeyLeague.LeagueManager.Team.Roster.ITeamRoster;

public interface ILeagueManagerFactory {
    ILeagueCreator createLeagueCreator();

    ILeague createLeague();

    IConference createConference();

    IDivision createDivision();

    ITeam createTeam();

    ITeamStrengthCalculator createTeamStrengthCalculator();

    ITeamTraining createTeamTraining();

    IPlayer createPlayer();

    IRandomPlayersGenerator createRandomPlayersGenerator(IRandomChance randomChance);

    IPlayerStats createPlayerStats();

    IPlayerAgeInfo createPlayerAgeInfo();

    IPlayerCareerProgression createPlayerCareerProgression(IRandomChance randomChance);

    ITeamPlayer createTeamPlayer();

    IFreeAgent createFreeAgent();

    ICoach createCoach();

    ICoachStats createCoachStats();

    IManager createManager();

    IGamePlayConfig createGamePlayConfig();

    IAgingConfig createAgingConfig();

    IInjuryConfig createInjuryConfig();

    ITrainingConfig createTrainingConfig();

    ITradingConfig createTradingConfig();

    IRandomChance createRandomChance();

    ISchedule createSchedule();

    IScheduleSystem createScheduleSystem();

    IStanding createStanding();

    IStandingSystem createStandingSystem();

    IDraftManager createDraftManager();

    IDraftPick createDraftPick(ITeam teamTradingAway, ITeam teamReceiving, int roundNumber, ITeamPlayer player);

    IDraftPickManager createDraftPickManager();

    ITeamRoster createTeamRoster();

    IActiveRoster createActiveRoster();

    IInactiveRoster createInactiveRoster();

    IGameSimulationSystem createGameSimulationSystem();

    IGameSimulationConfig createGameSimulationConfig();

    IGameSimulation createGameSimulation(ITeam teamA, ITeam teamB, IGameSimulationConfig config);

    IGameStats createGameStats();
}
