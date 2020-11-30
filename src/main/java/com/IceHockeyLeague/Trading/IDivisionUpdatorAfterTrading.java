package com.IceHockeyLeague.Trading;

import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

public interface IDivisionUpdatorAfterTrading {

    void divisionUpdatorAfterTradingFunction(ILeague league, ITeam oldSendingTeam, ITeam newSendingTeam, ITeam oldReceivingTeam, ITeam newReceivingTeam);
}
