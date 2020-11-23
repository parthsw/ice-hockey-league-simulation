package com.IceHockeyLeague.LeagueManager.Draft.DraftPick;

import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

public class DraftPick implements IDraftPick {
    private ITeam sendingTeam;
    private ITeam receivingTeam;
    private int draftRoundNumber;
    private ITeamPlayer playerAsked;
    private boolean decision;

    public DraftPick(ITeam team1, ITeam team2, int draftRoundNumber, ITeamPlayer playerAsked){
        this.sendingTeam = team1;
        this.receivingTeam = team2;
        this.draftRoundNumber = draftRoundNumber;
        this.playerAsked = playerAsked;
    }

    public ITeam getSendingTeam(){
        return sendingTeam;
    }

    public ITeam getReceivingTeam(){
        return receivingTeam;
    }

    public int getDraftPickRoundNumber(){
        return draftRoundNumber;
    }

    public ITeamPlayer getPlayerAsked(){
        return playerAsked;
    }

    public boolean getDecision() {
        return decision;
    }

    public void setDecision(boolean decision) {
        this.decision = decision;
    }
}
