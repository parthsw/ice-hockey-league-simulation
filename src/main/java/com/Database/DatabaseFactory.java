package com.Database;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.Coach.ICoachPersistence;
import com.IceHockeyLeague.LeagueManager.Conference.IConferencePersistence;
import com.IceHockeyLeague.LeagueManager.Division.IDivisionPersistence;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IGamePlayConfigPersistence;
import com.IceHockeyLeague.LeagueManager.League.ILeaguePersistence;
import com.IceHockeyLeague.LeagueManager.Manager.IManagerPersistence;
import com.IceHockeyLeague.LeagueManager.Player.IFreeAgentPersistence;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayerPersistence;
import com.IceHockeyLeague.LeagueManager.Team.ITeamPersistence;

public class DatabaseFactory implements IDatabaseFactory {
    private DBConnection dbConnectionInstance = null;

    private ILeaguePersistence leagueDB = null;
    private IConferencePersistence conferenceDB = null;
    private IDivisionPersistence divisionDB = null;
    private ITeamPersistence teamDB = null;
    private ITeamPlayerPersistence teamPlayerDB = null;
    private IFreeAgentPersistence freeAgentDB = null;
    private ICoachPersistence coachDB = null;
    private IManagerPersistence managerDB = null;
    private IGamePlayConfigPersistence gamePlayConfigDB = null;

    @Override
    public DBConnection createDBConnection() {
        if(dbConnectionInstance == null) {
            return new DBConnection();
        }
        return dbConnectionInstance;
    }

    public IStoredProcedure createStoredProcedure() {
        return new StoredProcedure(AbstractAppFactory.getDatabaseFactory());
    }

    @Override
    public IDateConversion createSQLDateConversion() {
        return new SQLDateConversion();
    }

    @Override
    public ILeaguePersistence createLeaguePersistence() {
        if(leagueDB == null) {
            leagueDB = new LeaguePersistence();
        }
        return leagueDB;
    }

    @Override
    public IConferencePersistence createConferencePersistence() {
        if(conferenceDB == null) {
            conferenceDB = new ConferencePersistence();
        }
        return conferenceDB;
    }

    @Override
    public IDivisionPersistence createDivisionPersistence() {
        if(divisionDB == null) {
            divisionDB = new DivisionPersistence();
        }
        return divisionDB;
    }

    @Override
    public ITeamPersistence createTeamPersistence() {
        if(teamDB == null) {
            teamDB = new TeamPersistence();
        }
        return teamDB;
    }

    @Override
    public ITeamPlayerPersistence createTeamPlayerPersistence() {
        if(teamPlayerDB == null) {
            teamPlayerDB = new TeamPlayerPersistence();
        }
        return teamPlayerDB;
    }

    @Override
    public IFreeAgentPersistence createFreeAgentPersistence() {
        if(freeAgentDB == null) {
            freeAgentDB = new FreeAgentPersistence();
        }
        return freeAgentDB;
    }

    @Override
    public ICoachPersistence createCoachPersistence() {
        if(coachDB == null) {
            coachDB = new CoachPersistence();
        }
        return coachDB;
    }

    @Override
    public IManagerPersistence createManagerPersistence() {
        if(managerDB == null) {
            managerDB = new ManagerPersistence();
        }
        return managerDB;
    }

    @Override
    public IGamePlayConfigPersistence createGamePlayConfigPersistence() {
        if(gamePlayConfigDB == null) {
            gamePlayConfigDB = new GamePlayConfigPersistence();
        }
        return gamePlayConfigDB;
    }
}
