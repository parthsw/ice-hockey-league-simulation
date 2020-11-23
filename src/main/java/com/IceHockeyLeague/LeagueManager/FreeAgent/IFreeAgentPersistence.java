package com.IceHockeyLeague.LeagueManager.FreeAgent;

import java.util.List;

public interface IFreeAgentPersistence {
    boolean saveFreeAgent(IFreeAgent freeAgent);
    boolean loadFreeAgents(int leagueId, List<IFreeAgent> freeAgents);
}
