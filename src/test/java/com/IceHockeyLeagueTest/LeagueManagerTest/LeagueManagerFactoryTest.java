package com.IceHockeyLeagueTest.LeagueManagerTest;

import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Coach.*;
import com.IceHockeyLeague.LeagueManager.Conference.*;
import com.IceHockeyLeague.LeagueManager.Division.*;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.*;
import com.IceHockeyLeague.LeagueManager.ILeagueCreator;
import com.IceHockeyLeague.LeagueManager.League.*;
import com.IceHockeyLeague.LeagueManager.LeagueCreator;
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
import org.mockito.Mockito;

import java.util.Random;

public class LeagueManagerFactoryTest implements ILeagueManagerFactory {
    private IRandomChance randomChanceGenerator = null;

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
        if(randomChanceGenerator == null) {
            Random randomMock = Mockito.mock(Random.class);
            randomChanceGenerator = new RandomChance(randomMock);
        }
        return randomChanceGenerator;
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
}
