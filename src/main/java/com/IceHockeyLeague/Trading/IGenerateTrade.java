package com.IceHockeyLeague.Trading;

import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.List;

public interface IGenerateTrade {

    void generateTrade(ITeam team1, int maxPlayersPerTrade, ITeam team2);

    Trade getGeneratedTrade();

    void decideTrade(float randomAcceptChance);

    List<ITeam> getResultTeams();

    boolean getTradeResult();
}
