package com.IceHockeyLeagueTest.LeagueManagerTest.PlayerTest;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IAgingConfig;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IInjuryConfig;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.ILeaguePersistence;
import com.IceHockeyLeague.LeagueManager.LeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.*;
import com.IceHockeyLeagueTest.LeagueManagerTest.TestLeagueManagerFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

public class PlayerCareerProgressionTest {
    private static AbstractLeagueManagerFactory leagueManagerFactory;
    private static IPlayerCareerProgression playerCareerProgression;
    private static IPlayer player;
    private static IRandomChance randomChanceMock;

    @BeforeClass
    public static void setup() {
        randomChanceMock = Mockito.mock(RandomChance.class);
        LeagueManagerFactory.setFactory(new TestLeagueManagerFactory());
        leagueManagerFactory = AbstractLeagueManagerFactory.getFactory();
        playerCareerProgression = new PlayerCareerProgression(randomChanceMock);
        player = leagueManagerFactory.getPlayer();
    }

    @Test
    public void isInjuredTest() {
        IInjuryConfig injuryConfig = leagueManagerFactory.getInjuryConfig();
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
        IAgingConfig agingConfig = leagueManagerFactory.getAgingConfig();
        agingConfig.setAverageRetirementAge(35);
        agingConfig.setMaximumAge(50);
        LocalDate currentDate = LocalDate.of(2020, 10, 30);

        IPlayer player = leagueManagerFactory.getPlayer();
        player.setPlayerAge(50);
        player.setElapsedDaysFromLastBDay(1);

        player.isRetired(playerCareerProgression, agingConfig, currentDate);
        Assert.assertTrue(player.getRetiredStatus());
        Assert.assertEquals(currentDate, player.getRetirementDate());

        IPlayer player1 = leagueManagerFactory.getPlayer();
        player1.setPlayerAge(20);
        player1.setElapsedDaysFromLastBDay(200);

        when(randomChanceMock.getRandomFloatNumber(0, agingConfig.getMaximumAge())).thenReturn(11.4f);
        player1.isRetired(playerCareerProgression, agingConfig, currentDate);
        Assert.assertFalse(player1.getRetiredStatus());
        Assert.assertNull(player1.getRetirementDate());

        IPlayer player2 = leagueManagerFactory.getPlayer();
        player2.setPlayerAge(39);
        player2.setElapsedDaysFromLastBDay(344);

        when(randomChanceMock.getRandomFloatNumber(0, agingConfig.getMaximumAge())).thenReturn(0.4f);
        player2.isRetired(playerCareerProgression, agingConfig, currentDate);
        Assert.assertTrue(player2.getRetiredStatus());
        Assert.assertEquals(currentDate, player2.getRetirementDate());
    }

    @Test
    public void handleFreeAgentRetirementTest() {
        ILeaguePersistence leagueDB = leagueManagerFactory.getLeagueDB();
        IFreeAgent freeAgent = leagueManagerFactory.getFreeAgent();
        ILeague league = leagueManagerFactory.getLeague();
        leagueDB.loadLeague(1, league);

        Assert.assertFalse(playerCareerProgression.handleFreeAgentRetirement(freeAgent, league));

        IFreeAgent freeAgentToRemove = league.getFreeAgents().get(1);
        playerCareerProgression.handleFreeAgentRetirement(freeAgentToRemove, league);
        Assert.assertEquals(2, league.getFreeAgents().size());
    }

}
