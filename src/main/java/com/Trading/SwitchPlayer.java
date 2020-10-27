package com.Trading;

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
        agent.setIsInjured(player.getIsInjured());
        agent.setIsRetired(player.getIsRetired());
        agent.setPlayerStats(player.getPlayerStats());
        agent.setPlayerAge(player.getPlayerAge());
//        agent.setPlayerPosition(player.getPlayerPosition());
        return agent;
    }

    @Override
    public ITeamPlayer freeToTeamTrade(IFreeAgent freeAgent, int teamId) {
        ITeamPlayer player = new TeamPlayer();
        player.setTeamID(teamId);
        player.setIsCaptain(false);
        player.setPlayerName(freeAgent.getPlayerName());
        player.setIsInjured(freeAgent.getIsInjured());
        player.setIsRetired(freeAgent.getIsRetired());
        player.setPlayerStats(freeAgent.getPlayerStats());
//        player.setPlayerPosition(freeAgent.getPlayerPosition());
        player.setPlayerAge(freeAgent.getPlayerAge());
        return player;
    }
}
