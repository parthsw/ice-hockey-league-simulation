package com.IceHockeyLeague.LeagueManager.Coach;

import java.util.List;

public interface ICoachPersistence {
    boolean saveCoach(ICoach coach);
    boolean loadCoach(int teamId, ICoach coach);	//Coach that is part of a team
    List<ICoach> loadUnassignedCoaches(int leagueId);	//Coaches that are part of the league but not assigned to a team
}
