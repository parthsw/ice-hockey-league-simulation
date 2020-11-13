package com.Trading;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;

public class SwitchPlayer implements ISwitchPlayer {
    private final ILeagueManagerFactory leagueManagerFactory;

    public SwitchPlayer() {
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
    }

    @Override
    public IFreeAgent teamToFreeTrade(ITeamPlayer player, int leagueID) {
        IFreeAgent agent = leagueManagerFactory.createFreeAgent();
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
        ITeamPlayer player = leagueManagerFactory.createTeamPlayer();
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
