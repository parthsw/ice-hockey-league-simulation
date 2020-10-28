package com.IceHockeyLeague.LeagueManager.Coach;

import java.util.List;

public interface ICoachPersistence {
    boolean saveTeamCoach(ICoach coach);
    boolean saveLeagueCoach(ICoach coach);
    boolean loadTeamCoach(int teamId, ICoach coach);
    boolean loadLeagueCoaches(int leagueId, List<ICoach> coaches);
}
