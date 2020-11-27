package com.PersistenceTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerAgeInfo;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerStats;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayerPersistence;

import java.time.LocalDate;
import java.time.Month;
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
        IPlayerStats stats = leagueManagerFactory.createPlayerStats();
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();

        teamPlayer.setTeamPlayerID(1);
        teamPlayer.setTeamID(1);
        teamPlayer.setPlayerName("Fred One");
        teamPlayer.setIsCaptain(true);
        teamPlayer.setInjuredStatus(false);
        teamPlayer.setDaysInjured(0);
        teamPlayer.setInjuryDate(null);
        teamPlayer.setRetiredStatus(false);
        teamPlayer.setRetirementDate(null);

        stats.setPosition("forward");
        stats.setShooting(10);
        stats.setChecking(2);
        stats.setSaving(14);
        stats.setSkating(18);
        stats.setStrength(29);
        teamPlayer.setPlayerStats(stats);

        playerAgeInfo.setBirthDate(LocalDate.of(1997, Month.APRIL, 18));
        playerAgeInfo.setAgeInYears(23);
        playerAgeInfo.setElapsedDaysFromLastBDay(212);
        teamPlayer.setPlayerAgeInfo(playerAgeInfo);

        return true;
    }

    @Override
    public boolean loadTeamPlayers(int teamId, List<ITeamPlayer> teamPlayers) {

        ITeamPlayer teamPlayer = leagueManagerFactory.createTeamPlayer();
        IPlayerStats stats = leagueManagerFactory.createPlayerStats();
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();

        teamPlayer.setTeamPlayerID(1);
        teamPlayer.setTeamID(1);
        teamPlayer.setPlayerName("Fred One");
        teamPlayer.setIsCaptain(true);
        teamPlayer.setInjuredStatus(false);
        teamPlayer.setDaysInjured(0);
        teamPlayer.setInjuryDate(null);
        teamPlayer.setRetiredStatus(false);
        teamPlayer.setRetirementDate(null);

        stats.setPosition("forward");
        stats.setShooting(10);
        stats.setChecking(2);
        stats.setSaving(14);
        stats.setSkating(18);
        stats.setStrength(29);
        teamPlayer.setPlayerStats(stats);

        playerAgeInfo.setBirthDate(LocalDate.of(1997, Month.APRIL, 18));
        playerAgeInfo.setAgeInYears(23);
        playerAgeInfo.setElapsedDaysFromLastBDay(212);
        teamPlayer.setPlayerAgeInfo(playerAgeInfo);

        ITeamPlayer teamPlayer1 = leagueManagerFactory.createTeamPlayer();
        IPlayerStats stats1 = leagueManagerFactory.createPlayerStats();
        IPlayerAgeInfo playerAgeInfo1 = leagueManagerFactory.createPlayerAgeInfo();

        teamPlayer1.setTeamPlayerID(2);
        teamPlayer1.setTeamID(1);
        teamPlayer1.setPlayerName("Mike One");
        teamPlayer1.setIsCaptain(false);
        teamPlayer1.setInjuredStatus(true);
        teamPlayer1.setDaysInjured(23);
        teamPlayer1.setInjuryDate(LocalDate.of(2020, 10, 20));
        teamPlayer1.setRetiredStatus(false);
        teamPlayer1.setRetirementDate(null);

        stats1.setPosition("defense");
        stats1.setShooting(12);
        stats1.setChecking(18);
        stats1.setSaving(16);
        stats1.setSkating(11);
        stats1.setStrength(35);
        teamPlayer1.setPlayerStats(stats1);

        playerAgeInfo1.setBirthDate(LocalDate.of(1993, Month.OCTOBER, 25));
        playerAgeInfo1.setAgeInYears(27);
        playerAgeInfo1.setElapsedDaysFromLastBDay(22);
        teamPlayer1.setPlayerAgeInfo(playerAgeInfo1);

        teamPlayers.add(teamPlayer);
        teamPlayers.add(teamPlayer1);
        return true;
    }
}
