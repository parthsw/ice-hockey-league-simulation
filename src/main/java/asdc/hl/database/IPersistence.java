package asdc.hl.database;

import asdc.hl.leaguemodel.models.*;

public interface IPersistence {
    void saveLeague(ILeague league);
    void loadLeagueWithName(String name, ILeague league);
    void saveConference(IConference conference);
    void loadConferenceWithName(String name, IConference conference);
    void saveDivision(IDivision division);
    void loadDivisionWithName(String name, IDivision division);
    void saveTeam(ITeam team);
    void loadTeamWithName(String name, ITeam team);
    void savePlayer(IPlayer player);
    void loadPlayerWithName(String name, IPlayer player);
    void saveManager(IManager manager);
    void loadManagerWithName(String name, IManager manager);
    void saveCoach(ICoach coach);
    void loadCoachWithName(String name, ICoach coach);

}

