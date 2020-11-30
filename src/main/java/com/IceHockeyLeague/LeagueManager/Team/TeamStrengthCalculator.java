package com.IceHockeyLeague.LeagueManager.Team;

import com.IceHockeyLeague.LeagueManager.Player.IPlayer;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;

import java.util.List;

public class TeamStrengthCalculator implements ITeamStrengthCalculator {

    @Override
    public float calculate(List<ITeamPlayer> players) {
        float teamStrength = 0f;
        for (IPlayer player : players) {
            float strength = player.calculateStrength(player.getPlayerStats());
            if (player.getInjuredStatus()) {
                strength = strength / 2;
            }
            teamStrength += strength;
        }
        return teamStrength;
    }

}
