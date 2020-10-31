package com.IceHockeyLeague.LeagueManager.Team;

import com.IceHockeyLeague.LeagueManager.Coach.ICoachStats;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;

import java.util.List;

public class TeamTraining {

    public void trainTeam(List<ITeamPlayer> teamPlayers, ICoachStats coachStats) {
        for (ITeamPlayer teamPlayer: teamPlayers) {
            // Generate random value between 0 and 1 for a single stat
            // If generated random value is less than the coach's stat
           // boolean flag = teamPlayer.getIsInjured();
            //returning league object
        }
    }
}
