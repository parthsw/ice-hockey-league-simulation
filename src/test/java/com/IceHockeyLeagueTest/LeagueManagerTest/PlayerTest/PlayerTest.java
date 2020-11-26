package com.IceHockeyLeagueTest.LeagueManagerTest.PlayerTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IAgingConfig;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IInjuryConfig;
import com.IceHockeyLeague.LeagueManager.Player.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.Month;

import static org.mockito.Mockito.when;

public class PlayerTest {
    private static final LocalDate CURRENT_DATE = LocalDate.of(2020, Month.NOVEMBER, 16);
    private static ILeagueManagerFactory leagueManagerFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
    }

    @Test
    public void ConstructorTest() {
        IPlayer player = leagueManagerFactory.createPlayer();
        Assert.assertFalse(player.getInjuredStatus());
        Assert.assertFalse(player.getRetiredStatus());
    }

    @Test
    public void getPlayerNameTest() {
        IPlayer player = leagueManagerFactory.createPlayer();
        player.setPlayerName("Joe Doe");
        Assert.assertEquals("Joe Doe", player.getPlayerName());
    }

    @Test
    public void setPlayerNameTest() {
        IPlayer player = leagueManagerFactory.createPlayer();
        player.setPlayerName("John");
        Assert.assertEquals("John", player.getPlayerName());
    }

    @Test
    public void getInjuredStatusTest() {
        IPlayer player = leagueManagerFactory.createPlayer();
        player.setInjuredStatus(true);
        Assert.assertTrue(player.getInjuredStatus());
    }

    @Test
    public void setInjuredStatusTest() {
        IPlayer player = leagueManagerFactory.createPlayer();
        player.setInjuredStatus(false);
        Assert.assertFalse(player.getInjuredStatus());
    }

    @Test
    public void getDaysInjuredTest() {
        IPlayer player = leagueManagerFactory.createPlayer();
        player.setDaysInjured(30);
        Assert.assertEquals(30, player.getDaysInjured());
    }

    @Test
    public void setDaysInjuredTest() {
        IPlayer player = leagueManagerFactory.createPlayer();
        player.setDaysInjured(13);
        Assert.assertEquals(13, player.getDaysInjured());
    }

    @Test
    public void getInjuryDateTest() {
        IPlayer player = leagueManagerFactory.createPlayer();
        player.setInjuryDate(LocalDate.of(2020, 10, 8));
        Assert.assertEquals(LocalDate.of(2020, 10, 8), player.getInjuryDate());
    }

    @Test
    public void setInjuryDateTest() {
        IPlayer player = leagueManagerFactory.createPlayer();
        player.setInjuryDate(LocalDate.of(2020, 11, 19));
        Assert.assertEquals(LocalDate.of(2020, 11, 19), player.getInjuryDate());
    }

    @Test
    public void getRetirementDateTest() {
        IPlayer player = leagueManagerFactory.createPlayer();
        player.setRetirementDate(LocalDate.of(2020, 9, 8));
        Assert.assertEquals(LocalDate.of(2020, 9, 8), player.getRetirementDate());
    }

    @Test
    public void setRetirementDate() {
        IPlayer player = leagueManagerFactory.createPlayer();
        player.setRetirementDate(LocalDate.of(2010, 11, 27));
        Assert.assertEquals(LocalDate.of(2010, 11, 27), player.getRetirementDate());
    }

    @Test
    public void getRetiredStatusTest() {
        IPlayer player = leagueManagerFactory.createPlayer();
        player.setRetiredStatus(true);
        Assert.assertTrue(player.getRetiredStatus());
    }

    @Test
    public void setRetiredStatusTest() {
        IPlayer player = leagueManagerFactory.createPlayer();
        player.setRetiredStatus(false);
        Assert.assertFalse(player.getRetiredStatus());
    }

    @Test
    public void getPlayerStatsTest() {
        IPlayer player = leagueManagerFactory.createPlayer();
        IPlayerStats expectedStats = createPlayerStats();

        player.setPlayerStats(expectedStats);
        IPlayerStats actualStats = player.getPlayerStats();

        Assert.assertEquals(expectedStats.getChecking(), actualStats.getChecking());
    }

    @Test
    public void getPlayerAgeInfoTest() {
        IPlayer player = leagueManagerFactory.createPlayer();
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();
        playerAgeInfo.setAgeInYears(22);
        player.setPlayerAgeInfo(playerAgeInfo);

        Assert.assertEquals(22, player.getPlayerAgeInfo().getAgeInYears());
    }

    @Test
    public void isBirthDayTest() {
        IPlayer player = leagueManagerFactory.createPlayer();
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();
        playerAgeInfo.setBirthDate(CURRENT_DATE);
        player.setPlayerAgeInfo(playerAgeInfo);

        Assert.assertTrue(player.isBirthDay(CURRENT_DATE));
    }

    @Test
    public void calculateStrength() {
        IPlayer player = leagueManagerFactory.createPlayer();
        IPlayerStats forwardStats = createPlayerStats();

        Assert.assertEquals(21.5, player.calculateStrength(forwardStats), 0.0);
    }

    @Test
    public void performStatDecayTest() {
        IPlayer player = leagueManagerFactory.createPlayer();
        IPlayerStats playerStats = createPlayerStats();
        player.setPlayerStats(playerStats);
        IRandomChance randomChanceMock = Mockito.mock(RandomChance.class);
        IAgingConfig agingConfig = leagueManagerFactory.createAgingConfig();
        agingConfig.setStatDecayChance(0.05f);

        when(randomChanceMock.getRandomFloatNumber(0, 1)).thenReturn(0.01f);
        player.performStatDecay(agingConfig, randomChanceMock);
        Assert.assertEquals(7, playerStats.getSkating());
    }

    @Test
    public void isInjuredTest() {
        IPlayerCareerProgression playerCareerProgression = leagueManagerFactory.createPlayerCareerProgression(leagueManagerFactory.createRandomChance());
        IPlayer player = leagueManagerFactory.createPlayer();
        IInjuryConfig injuryConfig = leagueManagerFactory.createInjuryConfig();
        Assert.assertFalse(player.isInjured(playerCareerProgression, injuryConfig, LocalDate.now()));
    }

    @Test
    public void isRecoveredTest() {
        IPlayerCareerProgression playerCareerProgression = leagueManagerFactory.createPlayerCareerProgression(leagueManagerFactory.createRandomChance());
        IPlayer player = leagueManagerFactory.createPlayer();

        player.setInjuryDate(LocalDate.of(2020, 10, 20));
        player.setInjuredStatus(true);
        player.setDaysInjured(8);
        Assert.assertTrue(player.isRecovered(playerCareerProgression, LocalDate.of(2020, 10, 28)));
    }

    @Test
    public void isRetiredTest() {
        IPlayerCareerProgression playerCareerProgression = leagueManagerFactory.createPlayerCareerProgression(leagueManagerFactory.createRandomChance());
        IPlayer player = leagueManagerFactory.createPlayer();
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();

        playerAgeInfo.setBirthDate(LocalDate.of(1969, Month.NOVEMBER, 16));
        playerAgeInfo.setAgeInYears(playerAgeInfo.calculatePlayerAgeInYears(CURRENT_DATE));
        playerAgeInfo.setElapsedDaysFromLastBDay(playerAgeInfo.calculateElapsedDaysFromLastBDay(CURRENT_DATE));
        player.setPlayerAgeInfo(playerAgeInfo);

        IAgingConfig agingConfig = leagueManagerFactory.createAgingConfig();
        agingConfig.setMaximumAge(50);
        agingConfig.setAverageRetirementAge(35);

        Assert.assertTrue(playerCareerProgression.isRetired(player, agingConfig, LocalDate.of(2020, 10, 29)));
    }

    @Test
    public void agePlayerByDaysTest() {
        IPlayer player = leagueManagerFactory.createPlayer();
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();
        playerAgeInfo.setBirthDate(LocalDate.of(2000, Month.NOVEMBER, 17));
        playerAgeInfo.setAgeInYears(playerAgeInfo.calculatePlayerAgeInYears(CURRENT_DATE));
        playerAgeInfo.setElapsedDaysFromLastBDay(playerAgeInfo.calculateElapsedDaysFromLastBDay(CURRENT_DATE));
        player.setPlayerAgeInfo(playerAgeInfo);

        player.agePlayerByDays(245, CURRENT_DATE);
        Assert.assertEquals(245, playerAgeInfo.getElapsedDaysFromLastBDay());
    }

    private IPlayerStats createPlayerStats() {
        IPlayerStats stats = leagueManagerFactory.createPlayerStats();
        stats.setSaving(9);
        stats.setChecking(11);
        stats.setShooting(8);
        stats.setSkating(8);
        stats.setPosition(PlayerPosition.FORWARD.toString());
        return stats;
    }

}
