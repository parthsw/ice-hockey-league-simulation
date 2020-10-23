package com.IceHockeyLeague.LeagueManager.Division;

import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Team.ITeamPersistence;

import java.util.List;

public interface IDivision {
    int getDivisionID();
    void setDivisionID(int id);

    String getDivisionName();
    void setDivisionName(String name);

    int getConferenceID();
    void setConferenceID(int id);

    ITeam getTeamById(int id);
    void addTeam(ITeam team);
    List<ITeam> getTeams();
    void setTeams(List<ITeam> teams);

    boolean saveDivision(IDivisionPersistence divisionDB);
    boolean loadTeams(ITeamPersistence teamDB, List<ITeam> teams);

    boolean isNullOrEmpty(String divsionName);

    boolean isDivisionNameExist(List<IDivision> divisions);

}
