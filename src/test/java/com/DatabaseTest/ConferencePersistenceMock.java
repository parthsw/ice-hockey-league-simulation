package com.DatabaseTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.Database.IDatabaseFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Conference.IConferencePersistence;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.Division.IDivisionPersistence;

import java.util.ArrayList;
import java.util.List;

public class ConferencePersistenceMock implements IConferencePersistence {
    private final ILeagueManagerFactory leagueManagerFactory;
    private final IDatabaseFactory databaseFactory;

    public ConferencePersistenceMock() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
        databaseFactory = appFactory.createDatabaseFactory();
    }

    @Override
    public boolean saveConference(IConference conference) {
        conference.setLeagueID(1);
        conference.setConferenceID(1);
        conference.setConferenceName("Eastern Conference");

        IDivisionPersistence divisionDB = databaseFactory.createDivisionPersistence();
        List<IDivision> divisions = new ArrayList<>();
        divisionDB.loadDivisions(1, divisions);
        conference.setDivisions(divisions);
        return true;
    }

    @Override
    public boolean loadConferences(int leagueId, List<IConference> conferences) {
        IConference conference = leagueManagerFactory.createConference();
        conference.setLeagueID(1);
        conference.setConferenceID(1);
        conference.setConferenceName("Eastern Conference");

        IDivisionPersistence divisionDB = databaseFactory.createDivisionPersistence();
        List<IDivision> divisions = new ArrayList<>();
        divisionDB.loadDivisions(1, divisions);
        conference.setDivisions(divisions);
        conferences.add(conference);

        IConference conference1 = leagueManagerFactory.createConference();
        conference1.setLeagueID(1);
        conference1.setConferenceID(2);
        conference1.setConferenceName("Western Conference");
        conference1.setDivisions(divisions);
        conferences.add(conference1);

        return true;
    }

}
