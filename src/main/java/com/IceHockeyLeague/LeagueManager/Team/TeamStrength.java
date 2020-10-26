package com.IceHockeyLeague.LeagueManager.Team;

import com.IceHockeyLeague.LeagueManager.Player.IPlayer;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;

import java.util.List;

public class TeamStrength implements ITeamStrength {

    @Override
    public double calculate(List<ITeamPlayer> players) {
        double teamStrength = 0;
        for(IPlayer player: players) {
            double strength = player.calculateStrength(player.getPlayerStats());
            if(player.getIsInjured()) {
                strength = strength / 2;
            }
            teamStrength += strength;
        }
        return teamStrength;
    }
}
