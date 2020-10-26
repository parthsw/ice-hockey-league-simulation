package com.IceHockeyLeague.LeagueManager.Player;
import java.util.*;

public interface ITeamPlayer extends IPlayer {
    int getTeamPlayerID();
    void setTeamPlayerID(int teamPlayerID);

    boolean isCaptain();
    void setIsCaptain(boolean captain);

    int getTeamID();
    void setTeamID(int teamID);

    boolean saveTeamPlayer(ITeamPlayerPersistence teamPlayerDB);

    boolean checkTeamPlayers(List<ITeamPlayer> players);
}
