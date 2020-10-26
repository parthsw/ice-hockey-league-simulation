package com.IceHockeyLeague.LeagueManager.Player;

public class FreeAgent extends Player implements IFreeAgent {
    private int freeAgentID;
    private int leagueID;

    public FreeAgent() {
        setDefaults();
    }

    @Override
    public int getFreeAgentID() {
        return freeAgentID;
    }

    @Override
    public void setFreeAgentID(int freeAgentID) {
        this.freeAgentID = freeAgentID;
    }

    @Override
    public int getLeagueID() {
        return leagueID;
    }

    @Override
    public void setLeagueID(int leagueID) {
        this.leagueID = leagueID;
    }

    private void setDefaults() {
        freeAgentID = -1;
        leagueID = -1;
    }

    @Override
    public boolean saveFreeAgent(IFreeAgentPersistence freeAgentDB) {
        return freeAgentDB.saveFreeAgent(this);
    }
}
