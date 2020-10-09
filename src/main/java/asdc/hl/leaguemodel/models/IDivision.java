package asdc.hl.leaguemodel.models;

import java.util.List;

public interface IDivision {
    int getDivisionID();
    void setDivisionID(int id);

    String getDivisionName();
    void setDivisionName(String name);

    int getConferenceID();
    void setConferenceID(int id);

    ITeam getTeamByName(String name);
    void addTeam(ITeam team);

    List<ITeam> getTeams();
    void setTeams(List<ITeam> teams);

    boolean checkIfDivisionNameExists();

    String validateNameDuringImport();
    String validateNameDuringLoad();
    String validateNameDuringCreate(List<IDivision> divisions);

    String validateBusinessRules();

    void save();
    void load();
}
