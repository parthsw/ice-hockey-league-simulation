package com.IceHockeyLeagueTest.LeagueManagerTest.PlayerTest;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerStats;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayerPersistence;

import java.time.LocalDate;
import java.util.List;

public class TeamPlayerDBMock implements ITeamPlayerPersistence {
    private final AbstractLeagueManagerFactory leagueManagerFactory;

    public TeamPlayerDBMock() {
        leagueManagerFactory = AbstractLeagueManagerFactory.getFactory();
    }

    @Override
    public boolean saveTeamPlayer(ITeamPlayer teamPlayer) {
        return false;
    }

    @Override
    public boolean loadTeamPlayers(int teamId, List<ITeamPlayer> teamPlayers) {

        ITeamPlayer teamPlayer = leagueManagerFactory.getTeamPlayer();
        teamPlayer.setTeamID(1);
        teamPlayer.setIsCaptain(true);
        teamPlayer.setTeamPlayerID(1);
        teamPlayer.setPlayerName("Fred One");

        IPlayerStats stats = leagueManagerFactory.getPlayerStats();
        stats.setPosition("forward");
        stats.setShooting(10);
        stats.setChecking(2);
        stats.setSaving(14);
        stats.setSkating(18);
        teamPlayer.setPlayerStats(stats);

        teamPlayer.setPlayerAge(23);
        teamPlayer.setInjuredStatus(false);
        teamPlayer.setRetiredStatus(false);

        ITeamPlayer teamPlayer1 = leagueManagerFactory.getTeamPlayer();
        teamPlayer1.setTeamID(1);
        teamPlayer1.setIsCaptain(false);
        teamPlayer1.setTeamPlayerID(2);
        teamPlayer1.setPlayerName("Mike One");

        IPlayerStats stats1 = leagueManagerFactory.getPlayerStats();
        stats1.setPosition("defense");
        stats1.setShooting(12);
        stats1.setChecking(18);
        stats1.setSaving(16);
        stats1.setSkating(11);
        teamPlayer1.setPlayerStats(stats1);

        teamPlayer1.setPlayerAge(27);
        teamPlayer1.setInjuredStatus(true);
        teamPlayer1.setInjuryDate(LocalDate.of(2020, 10, 20));
        teamPlayer1.setDaysInjured(23);
        teamPlayer1.setRetiredStatus(false);

        teamPlayers.add(teamPlayer);
        teamPlayers.add(teamPlayer1);

        return true;
    }
}
