package com.IceHockeyLeague.Trading;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SwitchPlayer implements ISwitchPlayer {
    private final ILeagueManagerFactory leagueManagerFactory;
    private final Logger LOGGER = LogManager.getLogger(SwitchPlayer.class);
    public SwitchPlayer() {
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
    }

    @Override
    public IFreeAgent teamToFreeTrade(ITeamPlayer player, int leagueID) {
        IFreeAgent agent = leagueManagerFactory.createFreeAgent();
        agent.setLeagueId(leagueID);
        agent.setPlayerName(player.getPlayerName());
        agent.setInjuredStatus(player.getInjuredStatus());
        agent.setRetiredStatus(player.getRetiredStatus());
        agent.setPlayerStats(player.getPlayerStats());
        agent.setPlayerAgeInfo(player.getPlayerAgeInfo());
        LOGGER.info("Player Switched");
        return agent;
    }

    @Override
    public ITeamPlayer freeToTeamTrade(IFreeAgent freeAgent, int teamId) {
        ITeamPlayer player = leagueManagerFactory.createTeamPlayer();
        player.setTeamId(teamId);
        player.setIsCaptain(false);
        player.setPlayerName(freeAgent.getPlayerName());
        player.setInjuredStatus(freeAgent.getInjuredStatus());
        player.setRetiredStatus(freeAgent.getRetiredStatus());
        player.setPlayerStats(freeAgent.getPlayerStats());
        player.setPlayerAgeInfo(freeAgent.getPlayerAgeInfo());
        return player;
    }
}
