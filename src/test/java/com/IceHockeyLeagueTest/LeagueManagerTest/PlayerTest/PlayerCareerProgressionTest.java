package com.IceHockeyLeagueTest.LeagueManagerTest.PlayerTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IGamePlayConfig;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IAgingConfig;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IInjuryConfig;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Player.*;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.Persistence.ILeaguePersistence;
import com.PersistenceTest.PersistenceFactoryTest;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class PlayerCareerProgressionTest {
    private static final LocalDate CURRENT_DATE = LocalDate.of(2020, Month.NOVEMBER, 17);
    private static ILeagueManagerFactory leagueManagerFactory;
    private static PersistenceFactoryTest persistenceFactory;
    private static IPlayerCareerProgression playerCareerProgression;
    private static IPlayer player;
    private static IRandomChance randomChanceMock;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        persistenceFactory = AppFactoryTest.createPersistenceFactoryTest();
        randomChanceMock = Mockito.mock(RandomChance.class);
        playerCareerProgression = leagueManagerFactory.createPlayerCareerProgression(randomChanceMock);
        player = leagueManagerFactory.createPlayer();
        AbstractAppFactory.setTrophySystemFactory(appFactory.createTrophySystemFactory());
    }

    @Test
    public void isInjuredAlreadyInjuredTest() {
        IInjuryConfig injuryConfig = createInjuryConfig();
        player.setInjuredStatus(true);
        Assert.assertTrue(playerCareerProgression.isInjured(player, injuryConfig, LocalDate.of(2020, 10, 27)));
    }

    @Test
    public void isInjuredTrueTest() {
        IInjuryConfig injuryConfig = createInjuryConfig();
        player.setInjuredStatus(false);
        when(randomChanceMock.getRandomFloatNumber(0, 1)).thenReturn(0.4f);
        when(randomChanceMock.roundFloatNumber(0.4f, 2)).thenReturn(0.4f);
        when(randomChanceMock.getRandomIntegerNumber(injuryConfig.getInjuryDaysLow(), injuryConfig.getInjuryDaysHigh())).thenReturn(4);

        playerCareerProgression.isInjured(player, injuryConfig, LocalDate.of(2020, 10, 27));
        Assert.assertTrue(player.getInjuredStatus());
        Assert.assertEquals(4, player.getDaysInjured());
    }

    @Test
    public void isInjuredFalseTest() {
        IInjuryConfig injuryConfig = createInjuryConfig();
        player.setInjuredStatus(false);
        when(randomChanceMock.getRandomFloatNumber(0, 1)).thenReturn(0.7f);
        when(randomChanceMock.roundFloatNumber(0.7f, 2)).thenReturn(0.7f);
        Assert.assertFalse(playerCareerProgression.isInjured(player, injuryConfig, LocalDate.of(2020, 10, 27)));
    }

    @Test
    public void isRecoveredNotInjuredTest() {
        player.setInjuredStatus(false);
        playerCareerProgression.isRecovered(player, LocalDate.of(2020, 10, 27));
        Assert.assertFalse(player.getInjuredStatus());
    }

    @Test
    public void isRecoveredTrueTest() {
        player.setInjuredStatus(true);
        player.setInjuryDate(LocalDate.of(2020, 10, 23));
        player.setDaysInjured(4);
        playerCareerProgression.isRecovered(player, LocalDate.of(2020, 10, 27));
        Assert.assertFalse(player.getInjuredStatus());
    }

    @Test
    public void isRecoveredFalseTest() {
        player.setInjuredStatus(true);
        player.setInjuryDate(LocalDate.of(2020, 10, 23));
        player.setDaysInjured(9);
        Assert.assertFalse(playerCareerProgression.isRecovered(player, LocalDate.of(2020, 10, 27)));
    }

    @Test
    public void isRetiredOverMaxAgeTest() {
        IAgingConfig agingConfig = createAgingConfig();
        IPlayer player = leagueManagerFactory.createPlayer();
        IPlayerAgeInfo playerAgeInfo = createPlayerAgeInfo(LocalDate.of(1970, Month.NOVEMBER, 15));
        player.setPlayerAgeInfo(playerAgeInfo);

        player.isRetired(playerCareerProgression, agingConfig, CURRENT_DATE);
        Assert.assertTrue(player.getRetiredStatus());
    }

    @Test
    public void isRetiredFalseTest() {
        IAgingConfig agingConfig = createAgingConfig();
        IPlayer player = leagueManagerFactory.createPlayer();
        IPlayerAgeInfo playerAgeInfo = createPlayerAgeInfo(LocalDate.of(2000, Month.APRIL, 30));
        player.setPlayerAgeInfo(playerAgeInfo);

        when(randomChanceMock.getRandomFloatNumber(0, agingConfig.getMaximumAge())).thenReturn(11.4f);
        player.isRetired(playerCareerProgression, agingConfig, CURRENT_DATE);
        Assert.assertFalse(player.getRetiredStatus());
    }

    @Test
    public void isRetiredTrueTest() {
        IAgingConfig agingConfig = createAgingConfig();
        IPlayer player = leagueManagerFactory.createPlayer();
        IPlayerAgeInfo playerAgeInfo = createPlayerAgeInfo(LocalDate.of(1980, Month.DECEMBER, 7));
        player.setPlayerAgeInfo(playerAgeInfo);

        when(randomChanceMock.getRandomFloatNumber(0, agingConfig.getMaximumAge())).thenReturn(0.4f);
        player.isRetired(playerCareerProgression, agingConfig, CURRENT_DATE);
        Assert.assertTrue(player.getRetiredStatus());
    }

    @Test
    public void handleFreeAgentRetirementInvalidTest() {
        ILeaguePersistence leaguePersistenceMock = persistenceFactory.createLeaguePersistence();
        IFreeAgent freeAgent = leagueManagerFactory.createFreeAgent();
        ILeague league = leaguePersistenceMock.loadLeague("");
        Assert.assertFalse(playerCareerProgression.handleFreeAgentRetirement(freeAgent, league));
    }

    @Test
    public void handleFreeAgentRetirementValidTest() {
        ILeaguePersistence leaguePersistenceMock = persistenceFactory.createLeaguePersistence();
        ILeague league = leaguePersistenceMock.loadLeague("");
        IFreeAgent freeAgentToRemove;
        freeAgentToRemove = league.getFreeAgents().get(1);
        playerCareerProgression.handleFreeAgentRetirement(freeAgentToRemove, league);
        Assert.assertEquals(59, league.getFreeAgents().size());
    }

    @Test
    public void handleTeamPlayerRetirementValidTest() {
        ILeaguePersistence leaguePersistenceMock = persistenceFactory.createLeaguePersistence();
        ILeague league = leaguePersistenceMock.loadLeague("");
        ITeam team = getFirstTeam(league);
        List<ITeamPlayer> teamPlayers = team.getPlayers();
        ITeamPlayer teamPlayer = teamPlayers.get(0);

        playerCareerProgression.handleTeamPlayerRetirement(teamPlayer, team, league);
        Assert.assertEquals(30, teamPlayers.size());
        Assert.assertEquals(2, league.getRetiredTeamPlayers().size());
    }

    @Test
    public void handleTeamPlayerRetirementInvalidTest() {
        ILeaguePersistence leaguePersistenceMock = persistenceFactory.createLeaguePersistence();
        ITeamPlayer emptyPlayer = leagueManagerFactory.createTeamPlayer();
        ITeam team;
        ILeague league = leaguePersistenceMock.loadLeague("");
        team = getFirstTeam(league);
        Assert.assertFalse(playerCareerProgression.handleTeamPlayerRetirement(emptyPlayer, team, league));
    }

    @Test
    public void handleTeamPlayerRetirementGoaliePlayerTest() {
        ILeaguePersistence leaguePersistenceMock = persistenceFactory.createLeaguePersistence();
        ILeague league = leaguePersistenceMock.loadLeague("");
        ITeam team = getFirstTeam(league);
        List<ITeamPlayer> teamPlayers = team.getPlayers();
        ITeamPlayer goalieTeamPlayer = teamPlayers.get(0);
        IPlayerStats stats = leagueManagerFactory.createPlayerStats();
        stats.setPosition(PlayerPosition.GOALIE.toString());
        goalieTeamPlayer.setPlayerStats(stats);
        Assert.assertFalse(playerCareerProgression.handleTeamPlayerRetirement(goalieTeamPlayer, team, league));
    }

    @Test
    public void performLeaguePlayersRetirementTeamPlayersTest() {
        ILeaguePersistence leaguePersistenceMock = persistenceFactory.createLeaguePersistence();
        ILeague league = leaguePersistenceMock.loadLeague("");
        IGamePlayConfig gamePlayConfig = league.getGamePlayConfig();
        IAgingConfig agingConfig = gamePlayConfig.getAgingConfig();
        league.setLeagueDate(CURRENT_DATE);

        when(randomChanceMock.getRandomFloatNumber(0, agingConfig.getMaximumAge())).thenReturn(0.01f);
        playerCareerProgression.performLeaguePlayersRetirement(league);
        Assert.assertEquals(301, league.getRetiredTeamPlayers().size());
    }

    @Test
    public void performLeaguePlayersRetirementFreeAgentsTest() {
        ILeaguePersistence leaguePersistenceMock = persistenceFactory.createLeaguePersistence();
        ILeague league = leaguePersistenceMock.loadLeague("");
        IGamePlayConfig gamePlayConfig = league.getGamePlayConfig();
        IAgingConfig agingConfig = gamePlayConfig.getAgingConfig();
        List<IConference> emptyConferences = new ArrayList<>();
        league.setConferences(emptyConferences);
        league.setLeagueDate(CURRENT_DATE);

        when(randomChanceMock.getRandomFloatNumber(0, agingConfig.getMaximumAge())).thenReturn(0.01f);
        playerCareerProgression.performLeaguePlayersRetirement(league);
        Assert.assertEquals(61, league.getRetiredFreeAgents().size());
    }

    @Test
    public void adjustLeaguePlayersAgeTest() {
        ILeaguePersistence leaguePersistenceMock = persistenceFactory.createLeaguePersistence();
        ILeague league = leaguePersistenceMock.loadLeague("");

        playerCareerProgression.adjustLeaguePlayersAge(league, LocalDate.of(2021, Month.SEPTEMBER, 20));

        IFreeAgent firstAgent = league.getFreeAgents().get(1);
        IPlayerAgeInfo firstAgentAgeInfo = firstAgent.getPlayerAgeInfo();
        Assert.assertEquals(20, firstAgentAgeInfo.getAgeInYears());
        Assert.assertEquals(319, firstAgentAgeInfo.getElapsedDaysFromLastBDate());
    }

    private IInjuryConfig createInjuryConfig() {
        IInjuryConfig injuryConfig = leagueManagerFactory.createInjuryConfig();
        injuryConfig.setInjuryDaysHigh(5);
        injuryConfig.setInjuryDaysLow(1);
        injuryConfig.setRandomInjuryChance(0.5f);
        return injuryConfig;
    }

    private IAgingConfig createAgingConfig() {
        IAgingConfig agingConfig = leagueManagerFactory.createAgingConfig();
        agingConfig.setAverageRetirementAge(35);
        agingConfig.setMaximumAge(50);
        return agingConfig;
    }

    private IPlayerAgeInfo createPlayerAgeInfo(LocalDate birthDate) {
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();
        playerAgeInfo.setBirthDate(birthDate);
        playerAgeInfo.setAgeInYears(playerAgeInfo.calculatePlayerAgeInYears(CURRENT_DATE));
        playerAgeInfo.setElapsedDaysFromLastBDate(playerAgeInfo.calculateElapsedDaysFromLastBDate(CURRENT_DATE));
        return playerAgeInfo;
    }

    private ITeam getFirstTeam(ILeague league) {
        List<IConference> conferences = league.getConferences();
        IConference conference = conferences.get(0);
        List<IDivision> divisions = conference.getDivisions();
        IDivision division = divisions.get(0);
        List<ITeam> teams = division.getTeams();
        return teams.get(0);
    }

}
