package com.IceHockeyLeague.LeagueManager.Player;

import java.util.List;

public class FreeAgentPersistence implements IFreeAgentPersistence {
    @Override
    public boolean saveFreeAgent(IFreeAgent freeAgent) {
        return false;
    }

    @Override
    public boolean loadFreeAgents(int leagueId, List<IFreeAgent> freeAgents) {
        return false;
    }
}
