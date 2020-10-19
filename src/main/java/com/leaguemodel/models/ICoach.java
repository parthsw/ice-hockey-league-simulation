package com.leaguemodel.models;

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
