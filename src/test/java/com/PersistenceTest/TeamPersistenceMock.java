package com.PersistenceTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Manager.IManager;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.ArrayList;
import java.util.List;

public class TeamPersistenceMock {
    private final ILeagueManagerFactory leagueManagerFactory;
    private static PersistenceFactoryTest persistenceFactory;

    public TeamPersistenceMock() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
        persistenceFactory = AppFactoryTest.createPersistenceFactoryTest();
    }

    public boolean loadTeams(int divisionId, List<ITeam> teams) {
        ITeam team = leagueManagerFactory.createTeam();
        team.setDivisionID(divisionId);
        team.setTeamID(1);
        team.setTeamName("Boston");
        team.setIsUserCreated(true);

        ICoach coach = leagueManagerFactory.createCoach();
        CoachPersistenceMock coachPersistenceMock = persistenceFactory.createCoachPersistence();
        coachPersistenceMock.loadTeamCoach(1, coach);
        team.setCoach(coach);

        IManager manager = leagueManagerFactory.createManager();
        ManagerPersistenceMock managerPersistenceMock = persistenceFactory.createManagerPersistence();
        managerPersistenceMock.loadTeamManager(1, manager);
        team.setManager(manager);

        List<ITeamPlayer> teamPlayers = new ArrayList<>();
        TeamPlayerPersistenceMock teamPlayerPersistenceMock = persistenceFactory.createTeamPlayerPersistence();
        teamPlayerPersistenceMock.loadTeamPlayers(1, teamPlayers);
        team.setPlayers(teamPlayers);

        teams.add(team);
        return true;
    }

    public boolean checkIfTeamNameExists(String teamName, List<ILeague> leagues) {
        ILeague league = leagueManagerFactory.createLeague();
        league.setLeagueID(1);
        league.setLeagueName("Dalhousie Hockey League");
        leagues.add(league);
        return true;
    }
}
