package com.Trading;

import com.IceHockeyLeague.LeagueManager.Team.ITeam;

public class GenerateTrade {

    public void generateTrade(ITeam team, int maxPlayersPerTrade) {
        Trade trade = new Trade(maxPlayersPerTrade);
        trade.setSendingTeam(team);


    }
}
