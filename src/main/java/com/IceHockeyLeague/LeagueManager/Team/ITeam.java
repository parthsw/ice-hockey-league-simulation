package com.IceHockeyLeague.LeagueManager.Team;

import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
import com.IceHockeyLeague.LeagueManager.Manager.IManager;
import com.IceHockeyLeague.LeagueManager.Player.IPlayer;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;

import java.util.List;

public interface ITeam {
    int getTeamID();
    void setTeamID(int id);

    String getTeamName();
    void setTeamName(String name);

    boolean getIsUserCreated();
    void setIsUserCreated(boolean isUserCreated);

    int getDivisionID();
    void setDivisionID(int id);

    IPlayer getPlayerById(int id);
    void addPlayer(ITeamPlayer player);
    List<ITeamPlayer> getPlayers();
    void setPlayers(List<ITeamPlayer> players);

    ICoach getCoach();
    void setCoach(ICoach coach);

    IManager getManager();
    void setManager(IManager manager);

    boolean isValid();
}