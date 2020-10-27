package com.IceHockeyLeagueTest.LeagueManagerTest;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Coach.*;
import com.IceHockeyLeague.LeagueManager.Conference.*;
import com.IceHockeyLeague.LeagueManager.Division.*;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.*;
import com.IceHockeyLeague.LeagueManager.ILeagueCreator;
import com.IceHockeyLeague.LeagueManager.League.*;
import com.IceHockeyLeague.LeagueManager.LeagueCreator;
import com.IceHockeyLeague.LeagueManager.Manager.*;
import com.IceHockeyLeague.LeagueManager.Player.*;
import com.IceHockeyLeague.LeagueManager.Team.*;
import com.IceHockeyLeagueTest.LeagueManagerTest.CoachTest.CoachDBMock;
import com.IceHockeyLeagueTest.LeagueManagerTest.ConferenceTest.ConferenceDBMock;
import com.IceHockeyLeagueTest.LeagueManagerTest.DivisionTest.DivisionDBMock;
import com.IceHockeyLeagueTest.LeagueManagerTest.LeagueTest.LeagueDBMock;
import com.IceHockeyLeagueTest.LeagueManagerTest.ManagerTest.ManagerDBMock;
import com.IceHockeyLeagueTest.LeagueManagerTest.PlayerTest.FreeAgentDBMock;
import com.IceHockeyLeagueTest.LeagueManagerTest.PlayerTest.TeamPlayerDBMock;
import com.IceHockeyLeagueTest.LeagueManagerTest.TeamTest.TeamDBMock;

public class TestLeagueManagerFactory extends AbstractLeagueManagerFactory {

    private ILeaguePersistence leagueDB = null;
    private IConferencePersistence conferenceDB = null;
    private IDivisionPersistence divisionDB = null;
    private ITeamPersistence teamDB = null;
    private ITeamPlayerPersistence teamPlayerDB = null;
    private IFreeAgentPersistence freeAgentDB = null;
    private ICoachPersistence coachDB = null;
    private IManagerPersistence managerDB = null;

    @Override
    public ILeagueCreator getLeagueCreator() {
        return new LeagueCreator();
    }

    @Override
    public ILeague getLeague() {
        return new League();
    }

    @Override
    public ILeaguePersistence getLeagueDB() {
        if (leagueDB == null) {
            leagueDB = new LeagueDBMock();
        }
        return leagueDB;
    }

    @Override
    public IConference getConference() {
        return new Conference();
    }

    @Override
    public IConferencePersistence getConferenceDB() {
        if(conferenceDB == null) {
            conferenceDB = new ConferenceDBMock();
        }
        return conferenceDB;
    }

    @Override
    public IDivision getDivision() {
        return new Division();
    }

    @Override
    public IDivisionPersistence getDivisionDB() {
        if(divisionDB == null) {
            divisionDB = new DivisionDBMock();
        }
        return null;
    }

    @Override
    public ITeam getTeam() {
        return new Team();
    }

    @Override
    public ITeamStrengthCalculator getTeamStrengthCalculator() {
        return new TeamStrengthCalculator();
    }

    @Override
    public ITeamPersistence getTeamDB() {
        if(teamDB == null) {
            teamDB = new TeamDBMock();
        }
        return teamDB;
    }

    @Override
    public IPlayer getPlayer() {
        return new Player();
    }

    @Override
    public IPlayerStats getPlayerStats() {
        return new PlayerStats();
    }

    @Override
    public ITeamPlayer getTeamPlayer() {
        return new TeamPlayer();
    }

    @Override
    public ITeamPlayerPersistence getTeamPlayerDB() {
        if(teamPlayerDB == null) {
            teamPlayerDB = new TeamPlayerDBMock();
        }
        return teamPlayerDB;
    }

    @Override
    public IFreeAgent getFreeAgent() {
        return new FreeAgent();
    }

    @Override
    public IFreeAgentPersistence getFreeAgentDB() {
        if(freeAgentDB == null) {
            freeAgentDB = new FreeAgentDBMock();
        }
        return freeAgentDB;
    }

    @Override
    public ICoach getCoach() {
        return new Coach();
    }

    @Override
    public ICoachStats getCoachStats() {
        return new CoachStats();
    }

    @Override
    public ICoachPersistence getCoachDB() {
        if(coachDB == null) {
            coachDB = new CoachDBMock();
        }
        return coachDB;
    }

    @Override
    public IManager getManager() {
        return new Manager();
    }

    @Override
    public IManagerPersistence getManagerDB() {
        if(managerDB == null) {
            managerDB = new ManagerDBMock();
        }
        return managerDB;
    }

    @Override
    public IGamePlayConfig getGamePlayConfig() {
        return new GamePlayConfig();
    }

    @Override
    public IAgingConfig getAgingConfig() {
        return new AgingConfig();
    }

    @Override
    public IGameResolverConfig getGameResolverConfig() {
        return new GameResolverConfig();
    }

    @Override
    public IInjuryConfig getInjuryConfig() {
        return new InjuryConfig();
    }

    @Override
    public ITrainingConfig getTrainingConfig() {
        return new TrainingConfig();
    }

    @Override
    public ITradingConfig getTradingConfig() {
        return new TradingConfig();
    }
}
