package com.IceHockeyLeague.LeagueManager.Team;

import com.IceHockeyLeague.LeagueManager.Player.IPlayer;

import java.util.List;

public class TeamStrengthCalculator {

    public static int strength(List<IPlayer> players) {
        int strength = 0;
        for(IPlayer player: players) {
            strength += player.calculateStrength(player.getPlayerStats());

            if(player.getIsInjured()) {
                strength = strength / 2;
            }
        }
        return strength;
    }

}
