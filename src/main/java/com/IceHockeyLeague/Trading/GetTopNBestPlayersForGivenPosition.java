package com.IceHockeyLeague.Trading;

import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class GetTopNBestPlayersForGivenPosition implements IGetTopNBestPlayersForGivenPosition {
    private ITeam team = null;
    private int maxTradeNumber;
    private List<ITeamPlayer> relaventPlayers = new ArrayList<>();
    private List<ITeamPlayer> bestPlayers = new ArrayList<>();
    private final Logger LOGGER = LogManager.getLogger(GetTopNBestPlayersForGivenPosition.class);

    public GetTopNBestPlayersForGivenPosition(ITeam team, int maxTradeNumber) {
        this.team = team;
        this.maxTradeNumber = maxTradeNumber;
        this.relaventPlayers.addAll(this.team.getPlayers());
        LOGGER.info("GetTopNBestPlayersForGivenPosition initiated");
    }

    public List<ITeamPlayer> getPlayers() {

        for (int i = 0; i < this.team.getPlayers().size(); i++) {
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

            if (this.bestPlayers.size() == this.maxTradeNumber) {
                break;
            }
        }
        return this.bestPlayers;

    }

    public float getCombinedStrendth() {
        float sum = 0;
        if (this.bestPlayers.size() == 0) {
            return 0;
        }
        for (ITeamPlayer player : this.bestPlayers) {
            sum += player.getPlayerStats().getStrength();
        }
        return sum;
    }


}
