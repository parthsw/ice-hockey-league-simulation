package com.IceHockeyLeagueTest.LeagueManagerTest.PlayerTest;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IInjuryConfig;
import com.IceHockeyLeague.LeagueManager.LeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.*;
import com.IceHockeyLeagueTest.LeagueManagerTest.TestLeagueManagerFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

public class PlayerInjuryManagerTest {
    private static AbstractLeagueManagerFactory leagueManagerFactory;
    private static IPlayerInjuryManager playerInjuryManager;
    private static IPlayer player;
    private static IRandomChance randomChanceMock;

    @BeforeClass
    public static void setup() {
        randomChanceMock = Mockito.mock(RandomChance.class);
        LeagueManagerFactory.setFactory(new TestLeagueManagerFactory());
        leagueManagerFactory = AbstractLeagueManagerFactory.getFactory();
        playerInjuryManager = new PlayerInjuryManager(randomChanceMock);
        player = leagueManagerFactory.getPlayer();
    }

    @Test
    public void isInjuredTest() {
        IInjuryConfig injuryConfig = leagueManagerFactory.getInjuryConfig();
        injuryConfig.setInjuryDaysHigh(5);
        injuryConfig.setInjuryDaysLow(1);
        injuryConfig.setRandomInjuryChance(0.5f);

        player.setInjuredStatus(true);
        Assert.assertTrue(playerInjuryManager.isInjured(player, injuryConfig, LocalDate.of(2020, 10, 27)));

        player.setInjuredStatus(false);
        when(randomChanceMock.getRandomFloatNumber(0, 1)).thenReturn(0.4f);
        when(randomChanceMock.roundFloatNumber(0.4f, 2)).thenReturn(0.4f);
        when(randomChanceMock.getRandomIntegerNumber(injuryConfig.getInjuryDaysLow(), injuryConfig.getInjuryDaysHigh())).thenReturn(4);
        playerInjuryManager.isInjured(player, injuryConfig, LocalDate.of(2020, 10, 27));
        Assert.assertTrue(player.getInjuredStatus());
        Assert.assertEquals(4, player.getDaysInjured());

        player.setInjuredStatus(false);
        when(randomChanceMock.getRandomFloatNumber(0, 1)).thenReturn(0.7f);
        when(randomChanceMock.roundFloatNumber(0.7f, 2)).thenReturn(0.7f);
        Assert.assertFalse(playerInjuryManager.isInjured(player, injuryConfig, LocalDate.of(2020, 10, 27)));
    }

    @Test
    public void isRecoveredTest() {
        player.setInjuredStatus(false);
        playerInjuryManager.isRecovered(player, LocalDate.of(2020,10,27));

        Assert.assertEquals(0, player.getDaysInjured());
        Assert.assertNull(player.getInjuryDate());
        Assert.assertFalse(player.getInjuredStatus());

        player.setInjuredStatus(true);
        player.setInjuryDate(LocalDate.of(2020, 10, 23));
        player.setDaysInjured(4);
        playerInjuryManager.isRecovered(player, LocalDate.of(2020,10,27));

        Assert.assertEquals(0, player.getDaysInjured());
        Assert.assertNull(player.getInjuryDate());
        Assert.assertFalse(player.getInjuredStatus());

        player.setInjuredStatus(true);
        player.setInjuryDate(LocalDate.of(2020, 10, 23));
        player.setDaysInjured(9);
        Assert.assertFalse(playerInjuryManager.isRecovered(player, LocalDate.of(2020,10,27)));
    }
}
