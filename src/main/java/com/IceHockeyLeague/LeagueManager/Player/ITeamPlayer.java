package com.IceHockeyLeague.LeagueManager.Player;

import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

public interface ITeamPlayer extends IPlayer {
    int getTeamPlayerID();
    void setTeamPlayerID(int teamPlayerID);

    boolean isCaptain();
    void setIsCaptain(boolean captain);

    int getTeamID();
    void setTeamID(int teamID);

    IFreeAgent convertToFreeAgent(IFreeAgent freeAgent);
    void generateTeamPlayer(IPlayer player);

    boolean saveTeamPlayer(ITeamPlayerPersistence teamPlayerDB);

    boolean handleTeamPlayerRetirement(IPlayerCareerProgression playerCareerProgression, ITeam team, ILeague league);
}
