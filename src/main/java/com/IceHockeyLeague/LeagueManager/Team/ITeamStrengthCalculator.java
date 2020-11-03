package com.IceHockeyLeague.LeagueManager.Team;

import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;

import java.util.List;

public interface ITeamStrengthCalculator {
    float calculate(List<ITeamPlayer> players);
}
