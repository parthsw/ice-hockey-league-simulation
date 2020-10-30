package com.Trading;

import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.List;

public class SimulateTrade {
    private GetAllTeamsFromLeague getAllTeamsObject;
    private GetTradableTeams getTradableTeamsObject;


    public void simulateTrade(ILeague league, int lossPointValue, int maxPlayersPerTrade) {
        this.getAllTeamsObject = new GetAllTeamsFromLeague(league);
        List<ITeam> allteams = this.getAllTeamsObject.getTeams();
        this.getTradableTeamsObject = new GetTradableTeams(allteams, lossPointValue);
        List<ITeam> tradableTeams = this.getTradableTeamsObject.getTeams();


    }
}
