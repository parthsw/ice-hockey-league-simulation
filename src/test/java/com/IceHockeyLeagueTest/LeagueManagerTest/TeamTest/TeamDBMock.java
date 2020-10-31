package com.IceHockeyLeagueTest.LeagueManagerTest.TeamTest;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
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

public class TeamDBMock implements ITeamPersistence {
    private final AbstractLeagueManagerFactory leagueManagerFactory;

    public TeamDBMock() {
        leagueManagerFactory = AbstractLeagueManagerFactory.getFactory();
    }

    @Override
    public boolean saveTeam(ITeam team) {
        team.setTeamID(1);
        team.setTeamName("Halifax");
        team.setIsUserCreated(false);
        team.setDivisionID(1);
        team.setTeamStrength(89.5f);

        ITeamPlayerPersistence teamPlayerDB = leagueManagerFactory.getTeamPlayerDB();
        List<ITeamPlayer> teamPlayers = new ArrayList<>();
        team.loadPlayers(teamPlayerDB, teamPlayers);
        team.setPlayers(teamPlayers);

        ICoachPersistence coachDB = leagueManagerFactory.getCoachDB();
        ICoach coach = leagueManagerFactory.getCoach();
        coach.loadTeamCoach(coachDB, coach);
        team.setCoach(coach);

        IManagerPersistence managerDB = leagueManagerFactory.getManagerDB();
        IManager manager = leagueManagerFactory.getManager();
        manager.loadTeamManager(managerDB, manager);
        team.setManager(manager);
        return true;
    }

    @Override
    public boolean loadTeams(int divisionId, List<ITeam> teams) {
        ITeam team = leagueManagerFactory.getTeam();
        team.setDivisionID(divisionId);
        team.setTeamID(1);
        team.setTeamName("Boston");
        team.setIsUserCreated(true);

        ICoach coach = leagueManagerFactory.getCoach();
        leagueManagerFactory.getCoachDB().loadTeamCoach(1, coach);
        team.setCoach(coach);

        IManager manager = leagueManagerFactory.getManager();
        leagueManagerFactory.getManagerDB().loadTeamManager(1, manager);
        team.setManager(manager);

        List<ITeamPlayer> teamPlayers = new ArrayList<>();
        leagueManagerFactory.getTeamPlayerDB().loadTeamPlayers(1, teamPlayers);
        team.setPlayers(teamPlayers);

        teams.add(team);
        return true;
    }

    @Override
    public boolean checkIfTeamNameExists(String teamName, List<ILeague> leagues) {
        ILeague league = leagueManagerFactory.getLeague();
        league.setLeagueID(1);
        league.setLeagueName("Dalhousie Hockey League");
        leagues.add(league);

        ILeague league1 = leagueManagerFactory.getLeague();
        league1.setLeagueID(2);
        league1.setLeagueName("Saint Marys League");
        leagues.add(league1);

        return true;
    }
}
