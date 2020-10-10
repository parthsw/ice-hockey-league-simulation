package asdc.hl.leaguemodel.models;

public interface IManager {
    int getManagerID();
    void setManagerID(int id);

    String getManagerName();
    void setManagerName(String name);

    int getTeamID();
    void setTeamID(int id);

    String validateName();

    void load();
    void save();
}
