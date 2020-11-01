package com.IceHockeyLeague.LeagueManager.Team;

import com.IceHockeyLeague.LeagueManager.Coach.ICoachStats;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;

import java.util.List;

public interface ITeamTraining {
    void trainTeam(List<ITeamPlayer> teamPlayers, ICoachStats coachStats);
}
