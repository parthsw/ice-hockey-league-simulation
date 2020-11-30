package com.IceHockeyLeague.LeagueManager.Team;

import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Manager.IManager;
import com.IceHockeyLeague.LeagueManager.Player.IPlayer;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.Roster.ITeamRoster;

import java.util.List;

public interface ITeam {
    int getTeamID();
    void setTeamID(int id);

    String getTeamName();
    void setTeamName(String name);

    boolean getIsUserCreated();
    void setIsUserCreated(boolean isUserCreated);

    float getTeamStrength();
    void setTeamStrength(float strength);

    int getDivisionID();
    void setDivisionID(int id);

    void setLossPointValue(int value);
    int getLossPointValue();
    void incrementLossPointValue();
    void decrementLossPointValue();

    IPlayer getPlayerById(int id);
    void addPlayer(ITeamPlayer player);
    boolean removePlayer(ITeamPlayer player);
    List<ITeamPlayer> getPlayers();
    void setPlayers(List<ITeamPlayer> players);

    ICoach getCoach();
    void setCoach(ICoach coach);

    IManager getManager();
    void setManager(IManager manager);

    boolean checkTeamPlayers();

    boolean isNullOrEmpty(String teamName);

    boolean isTeamNameExist(List<ITeam> teams, String teamName);

    float calculateTeamStrength(ITeamStrengthCalculator teamStrengthCalculator);

    void setTeamRoster(ITeamRoster teamRoster);

    List<ITeamPlayer> getActiveRoster();

    List<ITeamPlayer> getInactiveRoster();

    void validateRoster(List<IFreeAgent> agents);

}
