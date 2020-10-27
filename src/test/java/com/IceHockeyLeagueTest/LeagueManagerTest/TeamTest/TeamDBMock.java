package com.IceHockeyLeagueTest.LeagueManagerTest.TeamTest;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
import com.IceHockeyLeague.LeagueManager.Manager.IManager;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
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
        return false;
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

        return true;
    }
}
