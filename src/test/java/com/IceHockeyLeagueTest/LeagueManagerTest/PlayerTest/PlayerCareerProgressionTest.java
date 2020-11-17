package com.IceHockeyLeagueTest.LeagueManagerTest.PlayerTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.Database.IDatabaseFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IAgingConfig;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IInjuryConfig;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.ILeaguePersistence;
import com.IceHockeyLeague.LeagueManager.Player.*;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.mockito.Mockito.when;

public class PlayerCareerProgressionTest {
    private static final LocalDate CURRENT_DATE = LocalDate.of(2020, Month.NOVEMBER, 17);
    private static ILeagueManagerFactory leagueManagerFactory;
    private static IDatabaseFactory databaseFactory;
    private static IPlayerCareerProgression playerCareerProgression;
    private static IPlayer player;
    private static IRandomChance randomChanceMock;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        AbstractAppFactory.setDatabaseFactory(appFactory.createDatabaseFactory());
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        databaseFactory = AbstractAppFactory.getDatabaseFactory();
        randomChanceMock = Mockito.mock(RandomChance.class);
        playerCareerProgression = leagueManagerFactory.createPlayerCareerProgression(randomChanceMock);
        player = leagueManagerFactory.createPlayer();
    }

    @Test
    public void isInjuredTest() {
        IInjuryConfig injuryConfig = leagueManagerFactory.createInjuryConfig();
        injuryConfig.setInjuryDaysHigh(5);
        injuryConfig.setInjuryDaysLow(1);
        injuryConfig.setRandomInjuryChance(0.5f);

        player.setInjuredStatus(true);
        Assert.assertTrue(playerCareerProgression.isInjured(player, injuryConfig, LocalDate.of(2020, 10, 27)));

        player.setInjuredStatus(false);
        when(randomChanceMock.getRandomFloatNumber(0, 1)).thenReturn(0.4f);
        when(randomChanceMock.roundFloatNumber(0.4f, 2)).thenReturn(0.4f);
        when(randomChanceMock.getRandomIntegerNumber(injuryConfig.getInjuryDaysLow(), injuryConfig.getInjuryDaysHigh())).thenReturn(4);
        playerCareerProgression.isInjured(player, injuryConfig, LocalDate.of(2020, 10, 27));
        Assert.assertTrue(player.getInjuredStatus());
        Assert.assertEquals(4, player.getDaysInjured());

        player.setInjuredStatus(false);
        when(randomChanceMock.getRandomFloatNumber(0, 1)).thenReturn(0.7f);
        when(randomChanceMock.roundFloatNumber(0.7f, 2)).thenReturn(0.7f);
        Assert.assertFalse(playerCareerProgression.isInjured(player, injuryConfig, LocalDate.of(2020, 10, 27)));
    }

    @Test
    public void isRecoveredTest() {
        player.setInjuredStatus(false);
        playerCareerProgression.isRecovered(player, LocalDate.of(2020,10,27));

        Assert.assertEquals(0, player.getDaysInjured());
        Assert.assertNull(player.getInjuryDate());
        Assert.assertFalse(player.getInjuredStatus());

        player.setInjuredStatus(true);
        player.setInjuryDate(LocalDate.of(2020, 10, 23));
        player.setDaysInjured(4);
        playerCareerProgression.isRecovered(player, LocalDate.of(2020,10,27));

        Assert.assertEquals(0, player.getDaysInjured());
        Assert.assertNull(player.getInjuryDate());
        Assert.assertFalse(player.getInjuredStatus());

        player.setInjuredStatus(true);
        player.setInjuryDate(LocalDate.of(2020, 10, 23));
        player.setDaysInjured(9);
        Assert.assertFalse(playerCareerProgression.isRecovered(player, LocalDate.of(2020,10,27)));
    }

    @Test
    public void isRetiredTest() {
        IAgingConfig agingConfig = leagueManagerFactory.createAgingConfig();
        agingConfig.setAverageRetirementAge(35);
        agingConfig.setMaximumAge(50);
        LocalDate currentDate = LocalDate.of(2020, 10, 30);

        IPlayer player = leagueManagerFactory.createPlayer();
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();

        playerAgeInfo.setBirthDate(LocalDate.of(1970, Month.NOVEMBER, 15));
        playerAgeInfo.setAgeInYears(playerAgeInfo.calculatePlayerAgeInYears(CURRENT_DATE));
        playerAgeInfo.setElapsedDaysFromLastBDay(playerAgeInfo.calculateElapsedDaysFromLastBDay(CURRENT_DATE));
        player.setPlayerAgeInfo(playerAgeInfo);

        player.isRetired(playerCareerProgression, agingConfig, currentDate);
        Assert.assertTrue(player.getRetiredStatus());
        Assert.assertEquals(currentDate, player.getRetirementDate());

        IPlayer player1 = leagueManagerFactory.createPlayer();
        IPlayerAgeInfo playerAgeInfo1 = leagueManagerFactory.createPlayerAgeInfo();
        playerAgeInfo1.setBirthDate(LocalDate.of(2000, Month.APRIL, 30));
        playerAgeInfo1.setAgeInYears(playerAgeInfo1.calculatePlayerAgeInYears(CURRENT_DATE));
        playerAgeInfo1.setElapsedDaysFromLastBDay(playerAgeInfo1.calculateElapsedDaysFromLastBDay(CURRENT_DATE));
        player1.setPlayerAgeInfo(playerAgeInfo1);

        when(randomChanceMock.getRandomFloatNumber(0, agingConfig.getMaximumAge())).thenReturn(11.4f);
        player1.isRetired(playerCareerProgression, agingConfig, currentDate);
        Assert.assertFalse(player1.getRetiredStatus());
        Assert.assertNull(player1.getRetirementDate());

        IPlayer player2 = leagueManagerFactory.createPlayer();
        IPlayerAgeInfo playerAgeInfo2 = leagueManagerFactory.createPlayerAgeInfo();
        playerAgeInfo2.setBirthDate(LocalDate.of(1980, Month.DECEMBER, 7));
        playerAgeInfo2.setAgeInYears(playerAgeInfo2.calculatePlayerAgeInYears(CURRENT_DATE));
        playerAgeInfo2.setElapsedDaysFromLastBDay(playerAgeInfo2.calculateElapsedDaysFromLastBDay(CURRENT_DATE));
        player2.setPlayerAgeInfo(playerAgeInfo2);

        when(randomChanceMock.getRandomFloatNumber(0, agingConfig.getMaximumAge())).thenReturn(0.4f);
        player2.isRetired(playerCareerProgression, agingConfig, currentDate);
        Assert.assertTrue(player2.getRetiredStatus());
        Assert.assertEquals(currentDate, player2.getRetirementDate());
    }

    @Test
    public void handleFreeAgentRetirementTest() {
        ILeaguePersistence leagueDB = databaseFactory.createLeaguePersistence();
        IFreeAgent freeAgent = leagueManagerFactory.createFreeAgent();
        ILeague league = leagueManagerFactory.createLeague();
        leagueDB.loadLeague(1, league);

        Assert.assertFalse(playerCareerProgression.handleFreeAgentRetirement(freeAgent, league));

        IFreeAgent freeAgentToRemove = league.getFreeAgents().get(1);
        playerCareerProgression.handleFreeAgentRetirement(freeAgentToRemove, league);
        Assert.assertEquals(2, league.getFreeAgents().size());
    }

    @Test
    public void handleTeamPlayerRetirementTest() {
        ILeague league = leagueManagerFactory.createLeague();
        league.loadCompleteLeague(1);

        List<IConference> conferences = league.getConferences();
        IConference conference = conferences.get(0);
        List<IDivision> divisions = conference.getDivisions();
        IDivision division = divisions.get(0);
        List<ITeam> teams = division.getTeams();
        ITeam team = teams.get(0);
        List<ITeamPlayer> teamPlayers = team.getPlayers();
        ITeamPlayer teamPlayer = teamPlayers.get(0);

        playerCareerProgression.handleTeamPlayerRetirement(teamPlayer, team, league);
        Assert.assertEquals(20, teamPlayers.size());
        Assert.assertEquals(1, league.getRetiredTeamPlayers().size());
        Assert.assertEquals(2, league.getFreeAgents().size());

        ITeamPlayer emptyPlayer = leagueManagerFactory.createTeamPlayer();
        Assert.assertFalse(playerCareerProgression.handleTeamPlayerRetirement(emptyPlayer, team, league));

        ITeamPlayer goalieTeamPlayer = teamPlayers.get(0);
        IPlayerStats stats = leagueManagerFactory.createPlayerStats();
        stats.setPosition(PlayerPosition.GOALIE.toString());
        goalieTeamPlayer.setPlayerStats(stats);
        Assert.assertFalse(playerCareerProgression.handleTeamPlayerRetirement(goalieTeamPlayer, team, league));
    }

}
