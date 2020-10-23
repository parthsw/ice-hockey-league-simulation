package com.IceHockeyLeague.LeagueManager.Coach;

import java.util.List;

public interface ICoachPersistence {
    boolean saveCoach(ICoach coach);
    boolean loadTeamCoach(int leagueId, int teamId, ICoach coach);
    boolean loadLeagueCoaches(int leagueId, List<ICoach> coaches);
}
