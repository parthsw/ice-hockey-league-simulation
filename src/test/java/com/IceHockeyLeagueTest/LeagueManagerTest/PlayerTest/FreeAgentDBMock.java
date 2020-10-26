package com.IceHockeyLeagueTest.LeagueManagerTest.PlayerTest;

import com.IceHockeyLeague.LeagueManager.Player.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.IFreeAgentPersistence;

import java.util.List;

public class FreeAgentDBMock implements IFreeAgentPersistence {
    @Override
    public boolean saveFreeAgent(IFreeAgent freeAgent) {
        return false;
    }

    @Override
    public boolean loadFreeAgents(int leagueId, List<IFreeAgent> freeAgents) {
        return false;
    }
}
