package com.IceHockeyLeague.LeagueManager.Division;

import java.util.List;

public interface IDivisionPersistence {
    boolean saveDivision(IDivision division);
    boolean loadDivisions(int conferenceId, List<IDivision> divisions);
}
