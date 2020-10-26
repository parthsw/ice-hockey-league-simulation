package com.IceHockeyLeague.LeagueManager.Team;

import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;

import java.util.List;

public interface ITeamStrength {
    double calculate(List<ITeamPlayer> players);
}
