package com.IceHockeyLeague.LeagueManager.Division;

import java.util.List;

public class DivisionPersistence implements IDivisionPersistence {
    @Override
    public boolean saveDivision(IDivision division) {
        return false;
    }

    @Override
    public boolean loadDivisions(int conferenceId, List<IDivision> divisions) {
        return false;
    }
}
