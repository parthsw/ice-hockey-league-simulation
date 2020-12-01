package com.IceHockeyLeague.Trading;

import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GetPlayerFromTeam implements IGetPlayerFromTeam {
    private final Logger LOGGER = LogManager.getLogger(GetPlayerFromTeam.class);
    private ITeamPlayer worseTeamPlayer;

    public ITeamPlayer getWorsePlayerInTeamWithPosition(List<ITeamPlayer> players, String position) {
        if (players.isEmpty()) {
            LOGGER.error("Empty players provided");
        }
        int bestStrength = Integer.MAX_VALUE;
        for (ITeamPlayer player : players) {
            if (player.getPlayerStats().getPosition().equalsIgnoreCase(position) && bestStrength > player.getPlayerStats().getStrength()) {
                this.worseTeamPlayer = player;
                bestStrength = (int) player.getPlayerStats().getStrength();
            }
        }
        return this.worseTeamPlayer;
    }

    public ITeamPlayer getWorseSkaterInTeam(List<ITeamPlayer> players) {
        if (players.isEmpty()) {
            LOGGER.error("Empty players provided");
        }
        int bestStrength = Integer.MAX_VALUE;
        for (ITeamPlayer player : players) {
            if ((player.getPlayerStats().getPosition().equalsIgnoreCase("Forward") || player.getPlayerStats().getPosition().equals("Defence")) && bestStrength > player.getPlayerStats().getStrength()) {
                this.worseTeamPlayer = player;
                bestStrength = (int) player.getPlayerStats().getStrength();
            }
        }
        return this.worseTeamPlayer;
    }

}
