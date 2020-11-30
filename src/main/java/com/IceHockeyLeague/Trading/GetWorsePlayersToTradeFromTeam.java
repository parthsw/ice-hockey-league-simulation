package com.IceHockeyLeague.Trading;

import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GetWorsePlayersToTradeFromTeam {
    public List<ITeamPlayer> getPlayersToTrade(int maxTradablePlayers, ITeam team) {
        List<ITeamPlayer> playerList = new ArrayList<>();
        List<ITeamPlayer> tempList = new ArrayList<>();
        List<ITeamPlayer> resultList = new ArrayList<>();
        Random random = new Random();
        ITeamPlayer badPlayer = null;
        playerList.addAll(team.getPlayers());
        float strength = Float.MAX_VALUE;

        for (int i = 0; i < maxTradablePlayers; i++) {
            for (ITeamPlayer player : playerList) {
                if (player.getPlayerStats().getStrength() < strength) {
                    strength = player.getPlayerStats().getStrength();
                    badPlayer = player;
                }
            }
            strength = Float.MAX_VALUE;
            playerList.remove(badPlayer);
            tempList.add(badPlayer);
        }

        int tradeSize = random.nextInt((maxTradablePlayers - 1) + 1) + 1;
        for (int i = 0; i < tradeSize; i++) {
            resultList.add(tempList.get(i));
        }
        return resultList;
    }
}
