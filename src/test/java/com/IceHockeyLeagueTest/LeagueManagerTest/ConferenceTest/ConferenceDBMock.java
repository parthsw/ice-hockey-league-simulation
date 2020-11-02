package com.IceHockeyLeagueTest.LeagueManagerTest.ConferenceTest;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Conference.IConferencePersistence;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.Division.IDivisionPersistence;

import java.util.ArrayList;
import java.util.List;

public class ConferenceDBMock implements IConferencePersistence {

    @Override
    public boolean saveConference(IConference conference) {
        conference.setLeagueID(1);
        conference.setConferenceID(1);
        conference.setConferenceName("Eastern Conference");

        IDivisionPersistence divisionDB = AbstractLeagueManagerFactory.getFactory().getDivisionDB();
        List<IDivision> divisions = new ArrayList<>();
        divisionDB.loadDivisions(1, divisions);
        conference.setDivisions(divisions);
        return true;
    }

    @Override
    public boolean loadConferences(int leagueId, List<IConference> conferences) {
        IConference conference  = AbstractLeagueManagerFactory.getFactory().getConference();
        conference.setLeagueID(1);
        conference.setConferenceID(1);
        conference.setConferenceName("Eastern Conference");

        IDivisionPersistence divisionDB = AbstractLeagueManagerFactory.getFactory().getDivisionDB();
        List<IDivision> divisions = new ArrayList<>();
        divisionDB.loadDivisions(1, divisions);
        conference.setDivisions(divisions);
        conferences.add(conference);

        IConference conference1  = AbstractLeagueManagerFactory.getFactory().getConference();
        conference1.setLeagueID(1);
        conference1.setConferenceID(2);
        conference1.setConferenceName("Western Conference");
        conference1.setDivisions(divisions);
        conferences.add(conference1);

        return true;
    }

}
