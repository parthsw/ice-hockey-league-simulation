package com.Trading;

import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.ArrayList;
import java.util.List;

public class GetWorsePlayersToTradeFromTeam {
    public List<ITeamPlayer> getPlayersToTrade(int maxTradablePlayers, ITeam team) {
        List<ITeamPlayer> playerList = new ArrayList<>();
        List<ITeamPlayer> tempList = new ArrayList<>();
        List<ITeamPlayer> resultList = new ArrayList<>();
        ITeamPlayer badPlayer = null;
        playerList.addAll(team.getPlayers());
        float strength = Float.MAX_VALUE;
        int counter = 0;

        for (int i = 0; i < maxTradablePlayers; i++) {
            for (ITeamPlayer player : playerList) {
                counter++;
                if (player.getPlayerStats().getStrength() < strength) {
                    strength = player.getPlayerStats().getStrength();
                    badPlayer = player;
                }
            }
            strength = Float.MAX_VALUE;
            playerList.remove(badPlayer);
            tempList.add(badPlayer);
        }
        String position = tempList.get(0).getPlayerStats().getPosition();

        for (ITeamPlayer player : tempList) {
            if (player.getPlayerStats().getPosition().equalsIgnoreCase(position)) {
                resultList.add(player);
            } else {
                break;
            }
        }

        return resultList;
    }
}
