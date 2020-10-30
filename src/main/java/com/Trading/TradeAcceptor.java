package com.Trading;

import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.ArrayList;
import java.util.List;

public class TradeAcceptor {
    private ITeam team1;
    private ITeam team2;
    private List<ITeamPlayer> fromTeam1;
    private List<ITeamPlayer> fromTeam2;

    public TradeAcceptor(ITeam team1, ITeam team2, List<ITeamPlayer> fromTeam1, List<ITeamPlayer> fromTeam2) {
        setDefaults(team1, team2, fromTeam1, fromTeam2);
    }

    private void setDefaults(ITeam team1, ITeam team2, List<ITeamPlayer> fromTeam1, List<ITeamPlayer> fromTeam2) {
        this.team1 = team1;
        this.team2 = team2;
        this.fromTeam1 = fromTeam1;
        this.fromTeam2 = fromTeam2;
    }

    public List<ITeam> acceptTrade() {
        List<ITeamPlayer> team1Players = new ArrayList<>();
        team1Players.addAll(this.team1.getPlayers());
        List<ITeamPlayer> team2Players = new ArrayList<>();
        team2Players.addAll(this.team2.getPlayers());

        for (ITeamPlayer player : this.fromTeam1) {
            player.setTeamID(this.team2.getTeamID());
            team2Players.add(player);
        }
        team1Players.removeAll(this.fromTeam1);

        for (ITeamPlayer player : this.fromTeam2) {
            player.setTeamID(this.team1.getTeamID());
            team1Players.add(player);
        }
        team2Players.removeAll(this.fromTeam2);

        this.team1.setPlayers(team1Players);
        this.team2.setPlayers(team2Players);
        List<ITeam> result = new ArrayList<>();
        result.add(this.team1);
        result.add(this.team2);
        return result;
    }
}
