package com.IceHockeyLeagueTest.LeagueManagerTest.DivisionTest;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.Division.IDivisionPersistence;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Team.ITeamPersistence;

import java.util.ArrayList;
import java.util.List;

public class DivisionDBMock implements IDivisionPersistence {
    @Override
    public boolean saveDivision(IDivision division) {
        division.setDivisionID(1);
        division.setDivisionName("Atlantic");
        division.setConferenceID(1);

        ITeamPersistence teamDB = AbstractLeagueManagerFactory.getFactory().getTeamDB();
        List<ITeam> teams = new ArrayList<>();
        teamDB.loadTeams(1, teams);
        division.setTeams(teams);
        return true;
    }

    @Override
    public boolean loadDivisions(int conferenceId, List<IDivision> divisions) {
        IDivision division = AbstractLeagueManagerFactory.getFactory().getDivision();
        division.setDivisionID(1);
        division.setDivisionName("Atlantic");
        division.setConferenceID(1);

        ITeamPersistence teamDB = AbstractLeagueManagerFactory.getFactory().getTeamDB();
        List<ITeam> teams = new ArrayList<>();
        teamDB.loadTeams(1, teams);
        division.setTeams(teams);
        divisions.add(division);

        IDivision division1 = AbstractLeagueManagerFactory.getFactory().getDivision();
        division1.setDivisionID(2);
        division1.setDivisionName("Pacific");
        division1.setConferenceID(2);
        division1.setTeams(teams);
        divisions.add(division1);
        return true;
    }
}
