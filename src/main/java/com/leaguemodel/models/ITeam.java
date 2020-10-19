package com.leaguemodel.models;

import java.util.List;

public interface ITeam {
    int getTeamID();
    void setTeamID(int id);

    int getDivisionID();
    void setDivisionID(int id);

    String getTeamName();
    void setTeamName(String name);

    IPlayer getPlayerByName(String name);
    void addPlayer(IPlayer player);

    List<IPlayer> getPlayers();
    void setPlayers(List<IPlayer> players);

    ICoach getCoachByName(String name);
    void addCoach(ICoach coach);

    List<IManager> getManagers();
    void setManagers(List<IManager> managers);

    IManager getManagerByName(String name);
    void addManager(IManager manager);

    List<ICoach> getCoaches();
    void setCoaches(List<ICoach> coaches);

    boolean checkIfTeamNameExists();

    String validateNameDuringImport();
    String validateNameDuringLoad();
    String validateNameDuringCreate(List<ITeam> teamList);

    String validateBusinessRules();

    void save();
    void load();
}
