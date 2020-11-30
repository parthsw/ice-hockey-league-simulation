package com.PersistenceTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;


import java.util.ArrayList;
import java.util.List;

public class ConferencePersistenceMock {
    private final ILeagueManagerFactory leagueManagerFactory;
    private static PersistenceFactoryTest persistenceFactory;

    public ConferencePersistenceMock() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
        persistenceFactory = AppFactoryTest.createPersistenceFactoryTest();
    }

    public boolean loadConferences(int leagueId, List<IConference> conferences) {
        IConference conference = leagueManagerFactory.createConference();
        conference.setLeagueID(1);
        conference.setConferenceID(1);
        conference.setConferenceName("Eastern Conference");

        DivisionPersistenceMock divisionPersistenceMock = persistenceFactory.createDivisionPersistence();
        List<IDivision> divisions = new ArrayList<>();
        divisionPersistenceMock.loadDivisions(1,divisions);
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
