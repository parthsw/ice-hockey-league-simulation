package com.PersistenceTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.Database.IDatabaseFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.Division.IDivisionPersistence;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Team.ITeamPersistence;

import java.util.ArrayList;
import java.util.List;

public class DivisionPersistenceMock implements IDivisionPersistence {
    private final ILeagueManagerFactory leagueManagerFactory;
    private final IDatabaseFactory databaseFactory;

    public DivisionPersistenceMock() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
        databaseFactory = appFactory.createDatabaseFactory();
    }

    @Override
    public boolean saveDivision(IDivision division) {
        division.setDivisionID(1);
        division.setDivisionName("Atlantic");
        division.setConferenceID(1);

        ITeamPersistence teamDB = databaseFactory.createTeamPersistence();
        List<ITeam> teams = new ArrayList<>();
        teamDB.loadTeams(1, teams);
        division.setTeams(teams);
        return true;
    }

    @Override
    public boolean loadDivisions(int conferenceId, List<IDivision> divisions) {
        IDivision division = leagueManagerFactory.createDivision();
        division.setDivisionID(1);
        division.setDivisionName("Atlantic");
        division.setConferenceID(1);

        ITeamPersistence teamDB = databaseFactory.createTeamPersistence();
        List<ITeam> teams = new ArrayList<>();
        teamDB.loadTeams(1, teams);
        division.setTeams(teams);
        divisions.add(division);

        IDivision division1 = leagueManagerFactory.createDivision();
        division1.setDivisionID(2);
        division1.setDivisionName("Pacific");
        division1.setConferenceID(2);
        division1.setTeams(teams);
        divisions.add(division1);
        return true;
    }
}
