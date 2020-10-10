package asdc.hl.leaguemodel.models;

import asdc.hl.leaguemodel.IPersistence;

public interface ICoach {
    int getCoachID();
    void setCoachID(int id);

    String getCoachName();
    void setCoachName(String name);

    int getTeamID();
    void setTeamID(int id);

    String validateName();

    void load();
    void save();
}
