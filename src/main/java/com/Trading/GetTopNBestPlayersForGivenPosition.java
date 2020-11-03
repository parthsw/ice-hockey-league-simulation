package com.Trading;

import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.ArrayList;
import java.util.List;

public class GetTopNBestPlayersForGivenPosition {
    private ITeam team = null;
    private int tradeNumber;
    private String position;
    private List<ITeamPlayer> relaventPlayers = new ArrayList<>();
    private List<ITeamPlayer> bestPlayers = new ArrayList<>();

    public GetTopNBestPlayersForGivenPosition(ITeam team, int tradeNumber, String position) {
        this.team = team;
        this.tradeNumber = tradeNumber;
        this.position = position;
    }



    public List<ITeamPlayer> getPlayers() {
        for (ITeamPlayer player : this.team.getPlayers()) {
            if (player.getPlayerStats().getPosition().equalsIgnoreCase(this.position)) {
                this.relaventPlayers.add(player);
            }
        }

        for (int i = 0; i < 20; i++) {
            ITeamPlayer bestPlayer = null;
            float strength = -1;
            for (ITeamPlayer player : this.relaventPlayers) {
                if (player.getPlayerStats().getStrength() > strength) {
                    bestPlayer = player;
                    strength = bestPlayer.getPlayerStats().getStrength();
                }
            }
            this.bestPlayers.add(bestPlayer);
            this.relaventPlayers.remove(bestPlayer);

            if (this.bestPlayers.size() == this.tradeNumber) {
                break;
            }
        }
        return this.bestPlayers;

    }

    public float getCombinedStrendth() {
        float sum = 0;
        for (ITeamPlayer player : this.bestPlayers) {
            sum += player.getPlayerStats().getStrength();
        }
        return sum;
    }


}
