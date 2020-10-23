package com.IceHockeyLeague.LeagueManager.Conference;

import com.IceHockeyLeague.LeagueManager.Division.IDivision;

import java.util.List;

public interface IConference {
    int getConferenceID();
    void setConferenceID(int id);

    String getConferenceName();
    void setConferenceName(String name);

    int getLeagueID();
    void setLeagueID(int id);

    IDivision getDivisionById(int id);
    void addDivision(IDivision division);
    List<IDivision> getDivisions();
    void setDivisions(List<IDivision> divisions);

    boolean isValid();
    String validateNameDuringCreate(List<IConference> conferences);

}
