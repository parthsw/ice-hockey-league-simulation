package com.IceHockeyLeague.LeagueManager;

import com.IceHockeyLeague.LeagueManager.Coach.*;
import com.IceHockeyLeague.LeagueManager.Conference.*;
import com.IceHockeyLeague.LeagueManager.Division.*;
import com.IceHockeyLeague.LeagueManager.Draft.DraftManager;
import com.IceHockeyLeague.LeagueManager.Draft.DraftPick.DraftPick;
import com.IceHockeyLeague.LeagueManager.Draft.DraftPick.DraftPickManager;
import com.IceHockeyLeague.LeagueManager.Draft.DraftPick.IDraftPick;
import com.IceHockeyLeague.LeagueManager.Draft.DraftPick.IDraftPickManager;
import com.IceHockeyLeague.LeagueManager.Draft.IDraftManager;
import com.IceHockeyLeague.LeagueManager.FreeAgent.FreeAgent;
import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.*;
import com.IceHockeyLeague.LeagueManager.League.*;
import com.IceHockeyLeague.LeagueManager.Manager.*;
import com.IceHockeyLeague.LeagueManager.Player.*;
import com.IceHockeyLeague.LeagueManager.Scheduler.ISchedule;
import com.IceHockeyLeague.LeagueManager.Scheduler.IScheduleSystem;
import com.IceHockeyLeague.LeagueManager.Scheduler.Schedule;
import com.IceHockeyLeague.LeagueManager.Scheduler.ScheduleSystem;
import com.IceHockeyLeague.LeagueManager.Standings.IStanding;
import com.IceHockeyLeague.LeagueManager.Standings.IStandingSystem;
import com.IceHockeyLeague.LeagueManager.Standings.Standing;
import com.IceHockeyLeague.LeagueManager.Standings.StandingSystem;
import com.IceHockeyLeague.LeagueManager.Team.*;

import java.util.Random;

public class LeagueManagerFactory implements ILeagueManagerFactory {
    private IRandomChance randomChance = null;

    @Override
    public ILeagueCreator createLeagueCreator() {
        return new LeagueCreator();
    }

    @Override
    public ILeague createLeague() {
        return new League();
    }

    @Override
    public IConference createConference() {
        return new Conference();
    }

    @Override
    public IDivision createDivision() {
        return new Division();
    }

    @Override
    public ITeam createTeam() {
        return new Team();
    }

    @Override
    public ITeamStrengthCalculator createTeamStrengthCalculator() {
        return new TeamStrengthCalculator();
    }

    @Override
    public ITeamTraining createTeamTraining() {
        return new TeamTraining();
    }

    @Override
    public IPlayer createPlayer() {
        return new Player();
    }

    @Override
    public IRandomPlayersGenerator createRandomPlayersGenerator(IRandomChance randomChance) {
        return new RandomPlayersGenerator(randomChance);
    }

    @Override
    public IPlayerStats createPlayerStats() {
        return new PlayerStats();
    }

    @Override
    public IPlayerAgeInfo createPlayerAgeInfo() {
        return new PlayerAgeInfo();
    }

    @Override
    public IPlayerCareerProgression createPlayerCareerProgression(IRandomChance randomChance) {
        return new PlayerCareerProgression(randomChance);
    }

    @Override
    public ITeamPlayer createTeamPlayer() {
        return new TeamPlayer();
    }

    @Override
    public IFreeAgent createFreeAgent() {
        return new FreeAgent();
    }

    @Override
    public ICoach createCoach() {
        return new Coach();
    }

    @Override
    public ICoachStats createCoachStats() {
        return new CoachStats();
    }

    @Override
    public IManager createManager() {
        return new Manager();
    }

    @Override
    public IGamePlayConfig createGamePlayConfig() {
        return new GamePlayConfig();
    }

    @Override
    public IAgingConfig createAgingConfig() {
        return new AgingConfig();
    }

    @Override
    public IGameResolverConfig createGameResolverConfig() {
        return new GameResolverConfig();
    }

    @Override
    public IInjuryConfig createInjuryConfig() {
        return new InjuryConfig();
    }

    @Override
    public ITrainingConfig createTrainingConfig() {
        return new TrainingConfig();
    }

    @Override
    public ITradingConfig createTradingConfig() {
        return new TradingConfig();
    }

    @Override
    public IRandomChance createRandomChance() {
        if(randomChance == null) {
            return new RandomChance(new Random());
        }
        return randomChance;
    }

    @Override
    public ISchedule createSchedule() {
        return new Schedule();
    }

    @Override
    public IScheduleSystem createScheduleSystem() {
        return new ScheduleSystem();
    }

    @Override
    public IStanding createStanding() {
        return new Standing();
    }

    @Override
    public IStandingSystem createStandingSystem() {
        return new StandingSystem();
    }

    @Override
    public IDraftManager createDraftManager() {
        return new DraftManager();
    }

    @Override
    public IDraftPick createDraftPick(ITeam teamTradingAway, ITeam teamReceiving, int roundNumber, ITeamPlayer player) {
        return new DraftPick(teamTradingAway, teamReceiving, roundNumber, player);
    }

    @Override
    public IDraftPickManager createDraftPickManager() {
        return new DraftPickManager();
    }

}
