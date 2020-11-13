package com.Trading;

import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;

import java.util.List;
import java.util.Random;

public class TradeDecision {
    private float randomAcceptChance;
    private List<ITeamPlayer> offered;
    private List<ITeamPlayer> requested;

    public TradeDecision(List<ITeamPlayer> offered, List<ITeamPlayer> requested, float randomAcceptChance) {
        this.offered = offered;
        this.requested = requested;
        this.randomAcceptChance = randomAcceptChance;
    }

    public boolean tradeDecision() {
        Random rand = new Random();
        float sumOffered = 0;
        float sumRequested = 0;

        for (ITeamPlayer player : this.offered) {
            sumOffered += player.getPlayerStats().getStrength();
        }

        for (ITeamPlayer player : this.requested) {
            sumRequested += player.getPlayerStats().getStrength();
        }

        if (rand.nextFloat() <= this.randomAcceptChance) {
            return true;
        } else if (sumOffered > sumRequested) {
            return true;
        } else {
            return false;
        }
    }
}
