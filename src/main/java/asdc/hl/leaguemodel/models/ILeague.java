package asdc.hl.leaguemodel.models;

import java.util.List;

public interface ILeague {
    String getLeagueName();
    void setLeagueName(String name);

    int getLeagueID();
    void setLeagueID(int id);

    IConference getConferenceByName(String name);
    void addConference(IConference conference);

    List<IConference> getConferences();
    void setConferences(List<IConference> conferences);

    List<IPlayer> getFreeAgents();
    void setFreeAgents(List<IPlayer> freeAgents);

    String validateNameDuringImport();
    String validateNameDuringLoad();
    String validateNameDuringCreate();

    String validateBusinessRules();

    void save();
    void load();
}
