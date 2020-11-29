package com.IceHockeyLeague.LeagueManager.Player;

import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

public interface ITeamPlayer extends IPlayer {
    int getTeamPlayerId();

    void setTeamPlayerId(int teamPlayerId);

    boolean isCaptain();

    void setIsCaptain(boolean captain);

    int getTeamId();

    void setTeamId(int teamId);

    IFreeAgent convertToFreeAgent(IFreeAgent freeAgent);

    void generateTeamPlayer(IPlayer player);

    void generatePlayer(IPlayer player);

    boolean saveTeamPlayer(ITeamPlayerPersistence teamPlayerDb);

    boolean handleTeamPlayerRetirement(IPlayerCareerProgression playerCareerProgression, ITeam team, ILeague league);
}
