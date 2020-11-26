package com.Database;

import com.IceHockeyLeague.LeagueManager.Coach.ICoachPersistence;
import com.IceHockeyLeague.LeagueManager.Conference.IConferencePersistence;
import com.IceHockeyLeague.LeagueManager.Division.IDivisionPersistence;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IGamePlayConfigPersistence;
import com.IceHockeyLeague.LeagueManager.League.ILeaguePersistence;
import com.IceHockeyLeague.LeagueManager.Manager.IManagerPersistence;
import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgentPersistence;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayerPersistence;
import com.IceHockeyLeague.LeagueManager.Team.ITeamPersistence;

public interface IDatabaseFactory {

    DBConnection createDBConnection();

    IStoredProcedure createStoredProcedure();

    IDateConversion createSQLDateConversion();

    ILeaguePersistence createLeaguePersistence();

    IConferencePersistence createConferencePersistence();

    IDivisionPersistence createDivisionPersistence();

    ITeamPersistence createTeamPersistence();

    ITeamPlayerPersistence createTeamPlayerPersistence();

    IFreeAgentPersistence createFreeAgentPersistence();

    ICoachPersistence createCoachPersistence();

    IManagerPersistence createManagerPersistence();

    IGamePlayConfigPersistence createGamePlayConfigPersistence();

    PropertiesLoader createPropertiesLoader();
}
