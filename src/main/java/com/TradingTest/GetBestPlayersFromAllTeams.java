package com.TradingTest;

import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.List;

public class GetBestPlayersFromAllTeams {
    private List<ITeam> teams;
    private List<ITeamPlayer> bestPlayersSet;
    private ITeam bestChoice;

    public GetBestPlayersFromAllTeams(List<ITeam> teams) {
        setDefaults(teams);
    }

    private void setDefaults(List<ITeam> teams) {
        this.teams = teams;
    }

    public void getBestTradeOption(String position, int tradableNumber) {
        float totalStrength = -1;
        this.bestPlayersSet = null;
        this.bestChoice = null;
        for (ITeam team : this.teams) {
            GetTopNBestPlayersForGivenPosition object = new GetTopNBestPlayersForGivenPosition(team, tradableNumber, position);
            List<ITeamPlayer> bestPlayers = object.getPlayers();
            float combinedStrength = object.getCombinedStrendth();
            if (totalStrength < combinedStrength) {
                totalStrength = combinedStrength;
                this.bestPlayersSet = bestPlayers;
                this.bestChoice = team;
            }
        }

    }

    public ITeam getTeam() {
        return this.bestChoice;
    }

    public List<ITeamPlayer> getBestPlayersSet() {
        return bestPlayersSet;
    }
}
