package com.IceHockeyLeague.Trading;

import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.List;

public interface ITrade {
    ITeam getSendingTeam();

    void setSendingTeam(ITeam sendingTeam);

    ITeam getReceivingTeam();

    void setReceivingTeam(ITeam receivingTeam);

    List<ITeamPlayer> getSendingPlayers();

    void setSendingPlayers(List<ITeamPlayer> sendingPlayers);

    List<ITeamPlayer> getReceivingPlayers();

    void setReceivingPlayers(List<ITeamPlayer> receivingPlayers);

    boolean validateTrade();

    List<ITeam> acceptTrade();

    boolean tradeDecision(float randomAcceptChance);

}
