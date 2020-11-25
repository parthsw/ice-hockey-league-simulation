package com.IceHockeyLeague.LeagueManager.Team.TeamRoster;

import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;

import java.util.ArrayList;
import java.util.List;

public class ActiveRoster implements IActiveRoster {
    private List<ITeamPlayer> activePlayers;

    public List<ITeamPlayer> getActivePlayers() {
        return activePlayers;
    }

    public void setActivePlayers(List<ITeamPlayer> activePlayers) {
        this.activePlayers = activePlayers;
    }

    private List<ITeamPlayer> getInjuredPlayers() {
        List<ITeamPlayer> injuredPlayers = new ArrayList<>();
        for (ITeamPlayer player : this.activePlayers) {
            if (player.getInjuredStatus()) {
                injuredPlayers.add(player);
            }
        }
        return injuredPlayers;
    }

    private void removeInjuredPlayer(List<ITeamPlayer> newPlayers, List<ITeamPlayer> injuredPlayers) {
        this.activePlayers.removeAll(injuredPlayers);
        this.activePlayers.addAll(newPlayers);
    }

    public void validateActiveRoster(IInactiveRoster inactiveRoster) {
        List<ITeamPlayer> injuredPlayers = this.getInjuredPlayers();
        List<ITeamPlayer> newPlayers = new ArrayList<>();
        for (ITeamPlayer injuredPlayer : injuredPlayers) {
            ITeamPlayer replacementPlayer = inactiveRoster.getReplacementPlayer(injuredPlayer, injuredPlayer.getPlayerStats().getPosition());
            newPlayers.add(replacementPlayer);
        }
        this.removeInjuredPlayer(newPlayers, injuredPlayers);
    }


}
