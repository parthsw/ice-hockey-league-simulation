package com.DatabaseTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerStats;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayerPersistence;

import java.time.LocalDate;
import java.util.List;

public class TeamPlayerPersistenceMock implements ITeamPlayerPersistence {
    private final ILeagueManagerFactory leagueManagerFactory;

    public TeamPlayerPersistenceMock() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
    }

    @Override
    public boolean saveTeamPlayer(ITeamPlayer teamPlayer) {
        teamPlayer.setTeamPlayerID(1);
        teamPlayer.setTeamID(1);
        teamPlayer.setPlayerName("Fred One");
        teamPlayer.setIsCaptain(true);
        teamPlayer.setPlayerAge(23);
        teamPlayer.setElapsedDaysFromLastBDay(212);
        teamPlayer.setInjuredStatus(false);
        teamPlayer.setDaysInjured(0);
        teamPlayer.setInjuryDate(null);
        teamPlayer.setRetiredStatus(false);
        teamPlayer.setRetirementDate(null);

        IPlayerStats stats = leagueManagerFactory.createPlayerStats();
        stats.setPosition("forward");
        stats.setShooting(10);
        stats.setChecking(2);
        stats.setSaving(14);
        stats.setSkating(18);
        stats.setStrength(29);
        teamPlayer.setPlayerStats(stats);
        return true;
    }

    @Override
    public boolean loadTeamPlayers(int teamId, List<ITeamPlayer> teamPlayers) {

        ITeamPlayer teamPlayer = leagueManagerFactory.createTeamPlayer();
        teamPlayer.setTeamPlayerID(1);
        teamPlayer.setTeamID(1);
        teamPlayer.setPlayerName("Fred One");
        teamPlayer.setIsCaptain(true);
        teamPlayer.setPlayerAge(23);
        teamPlayer.setElapsedDaysFromLastBDay(212);
        teamPlayer.setInjuredStatus(false);
        teamPlayer.setDaysInjured(0);
        teamPlayer.setInjuryDate(null);
        teamPlayer.setRetiredStatus(false);
        teamPlayer.setRetirementDate(null);

        IPlayerStats stats = leagueManagerFactory.createPlayerStats();
        stats.setPosition("forward");
        stats.setShooting(10);
        stats.setChecking(2);
        stats.setSaving(14);
        stats.setSkating(18);
        stats.setStrength(29);
        teamPlayer.setPlayerStats(stats);

        ITeamPlayer teamPlayer1 = leagueManagerFactory.createTeamPlayer();
        teamPlayer1.setTeamPlayerID(2);
        teamPlayer1.setTeamID(1);
        teamPlayer1.setPlayerName("Mike One");
        teamPlayer1.setIsCaptain(false);
        teamPlayer1.setPlayerAge(27);
        teamPlayer1.setElapsedDaysFromLastBDay(22);
        teamPlayer1.setInjuredStatus(true);
        teamPlayer1.setDaysInjured(23);
        teamPlayer1.setInjuryDate(LocalDate.of(2020, 10, 20));
        teamPlayer1.setRetiredStatus(false);
        teamPlayer1.setRetirementDate(null);

        IPlayerStats stats1 = leagueManagerFactory.createPlayerStats();
        stats1.setPosition("defense");
        stats1.setShooting(12);
        stats1.setChecking(18);
        stats1.setSaving(16);
        stats1.setSkating(11);
        stats1.setStrength(35);
        teamPlayer1.setPlayerStats(stats1);

        teamPlayers.add(teamPlayer);
        teamPlayers.add(teamPlayer1);
        return true;
    }
}
