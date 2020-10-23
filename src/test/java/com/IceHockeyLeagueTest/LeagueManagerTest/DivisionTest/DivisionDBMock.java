package com.IceHockeyLeagueTest.LeagueManagerTest.DivisionTest;

import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.Division.IDivisionPersistence;

import java.util.List;

public class DivisionDBMock implements IDivisionPersistence {
    @Override
    public boolean saveDivision(IDivision division) {
        return false;
    }

    @Override
    public boolean loadDivisions(int conferenceId, List<IDivision> divisions) {
        return false;
    }
}
