package com.IceHockeyLeague.Trading;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.List;

public class GetBestPlayersFromAllTeams implements IGetBestPlayersFromAllTeams {
    private ITradingFactory tradingFactory;
    private List<ITeam> teams;
    private List<ITeamPlayer> bestPlayersSet;
    private ITeam bestChoice;

    public GetBestPlayersFromAllTeams(List<ITeam> teams) {
        setDefaults(teams);
    }

    private void setDefaults(List<ITeam> teams) {
        this.teams = teams;
        tradingFactory = AbstractAppFactory.getTradingFactory();
    }

    public void getBestTradeOption(int tradableNumber) {
        float totalStrength = -1;
        this.bestPlayersSet = null;
        this.bestChoice = null;
        for (ITeam team : this.teams) {
            GetTopNBestPlayersForGivenPosition object = tradingFactory.createGetTopNBestPlayersForGivenPosition(team, tradableNumber);
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
