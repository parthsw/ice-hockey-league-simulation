package com.TradingTest;

import com.IceHockeyLeague.LeagueManager.Player.FreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Player.TeamPlayer;

public class SwitchPlayer implements ISwitchPlayer {
    @Override
    public IFreeAgent teamToFreeTrade(ITeamPlayer player, int leagueID) {
        IFreeAgent agent = new FreeAgent();
        agent.setLeagueID(leagueID);
        agent.setPlayerName(player.getPlayerName());
        agent.setInjuredStatus(player.getInjuredStatus());
        agent.setRetiredStatus(player.getRetiredStatus());
        agent.setPlayerStats(player.getPlayerStats());
        agent.setPlayerAge(player.getPlayerAge());
        return agent;
    }

    @Override
    public ITeamPlayer freeToTeamTrade(IFreeAgent freeAgent, int teamId) {
        ITeamPlayer player = new TeamPlayer();
        player.setTeamID(teamId);
        player.setIsCaptain(false);
        player.setPlayerName(freeAgent.getPlayerName());
        player.setInjuredStatus(freeAgent.getInjuredStatus());
        player.setRetiredStatus(freeAgent.getRetiredStatus());
        player.setPlayerStats(freeAgent.getPlayerStats());
        player.setPlayerAge(freeAgent.getPlayerAge());
        return player;
    }
}
