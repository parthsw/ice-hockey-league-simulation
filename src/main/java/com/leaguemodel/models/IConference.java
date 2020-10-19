package com.leaguemodel.models;

import java.util.List;

public interface IConference {
    int getConferenceID();
    void setConferenceID(int id);

    String getConferenceName();
    void setConferenceName(String name);

    int getLeagueID();
    void setLeagueID(int id);

    IDivision getDivisionByName(String name);
    void addDivision(IDivision division);

    List<IDivision> getDivisions();
    void setDivisions(List<IDivision> divisions);

    String validateNameDuringImport();
    String validateNameDuringLoad();
    String validateNameDuringCreate(List<IConference> conferences);

    String validateBusinessRules();

    void save();
    void load();
}
