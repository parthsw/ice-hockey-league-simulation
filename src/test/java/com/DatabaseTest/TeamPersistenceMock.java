package com.DatabaseTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.Database.IDatabaseFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
import com.IceHockeyLeague.LeagueManager.Coach.ICoachPersistence;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Manager.IManager;
import com.IceHockeyLeague.LeagueManager.Manager.IManagerPersistence;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayerPersistence;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Team.ITeamPersistence;

import java.util.ArrayList;
import java.util.List;

public class TeamPersistenceMock implements ITeamPersistence {
    private final ILeagueManagerFactory leagueManagerFactory;
    private final IDatabaseFactory databaseFactory;

    public TeamPersistenceMock() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
        databaseFactory = appFactory.createDatabaseFactory();
    }
    @Override
    public boolean saveTeam(ITeam team) {
        team.setTeamID(1);
        team.setTeamName("Halifax");
        team.setIsUserCreated(false);
        team.setDivisionID(1);
        team.setTeamStrength(89.5f);

        ITeamPlayerPersistence teamPlayerDB = databaseFactory.createTeamPlayerPersistence();
        List<ITeamPlayer> teamPlayers = new ArrayList<>();
        team.loadPlayers(teamPlayerDB, teamPlayers);
        team.setPlayers(teamPlayers);

        ICoachPersistence coachDB = databaseFactory.createCoachPersistence();
        ICoach coach = leagueManagerFactory.createCoach();
        coach.loadTeamCoach(coachDB, coach);
        team.setCoach(coach);

        IManagerPersistence managerDB = databaseFactory.createManagerPersistence();
        IManager manager = leagueManagerFactory.createManager();
        manager.loadTeamManager(managerDB, manager);
        team.setManager(manager);
        return true;
    }

    @Override
    public boolean loadTeams(int divisionId, List<ITeam> teams) {
        ITeam team = leagueManagerFactory.createTeam();
        team.setDivisionID(divisionId);
        team.setTeamID(1);
        team.setTeamName("Boston");
        team.setIsUserCreated(true);

        ICoach coach = leagueManagerFactory.createCoach();
        ICoachPersistence coachDB = databaseFactory.createCoachPersistence();
        coachDB.loadTeamCoach(1, coach);
        team.setCoach(coach);

        IManager manager = leagueManagerFactory.createManager();
        IManagerPersistence managerDB = databaseFactory.createManagerPersistence();
        managerDB.loadTeamManager(1, manager);
        team.setManager(manager);

        List<ITeamPlayer> teamPlayers = new ArrayList<>();
        ITeamPlayerPersistence teamPlayerDB = databaseFactory.createTeamPlayerPersistence();
        teamPlayerDB.loadTeamPlayers(1, teamPlayers);
        team.setPlayers(teamPlayers);

        teams.add(team);
        return true;
    }

    @Override
    public boolean checkIfTeamNameExists(String teamName, List<ILeague> leagues) {
        ILeague league = leagueManagerFactory.createLeague();
        league.setLeagueID(1);
        league.setLeagueName("Dalhousie Hockey League");
        leagues.add(league);
        return true;
    }
}
