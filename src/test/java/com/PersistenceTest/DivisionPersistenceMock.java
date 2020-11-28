package com.PersistenceTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Team.TeamStrengthCalculator;
import java.util.ArrayList;
import java.util.List;

public class DivisionPersistenceMock {
    private final ILeagueManagerFactory leagueManagerFactory;
    private static PersistenceFactoryTest persistenceFactory;

    public DivisionPersistenceMock() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
        persistenceFactory = AppFactoryTest.createPersistenceFactory();
    }

    public boolean loadDivisions(int conferenceId, List<IDivision> divisions) {
        IDivision division = leagueManagerFactory.createDivision();
        division.setDivisionID(1);
        division.setDivisionName("Atlantic");
        division.setConferenceID(1);

        TeamPersistenceMock teamPersistenceMock = persistenceFactory.createTeamPersistence();
        List<ITeam> teams = new ArrayList<>();
        teamPersistenceMock.loadTeams(1,teams);
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
