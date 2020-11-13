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

import java.time.LocalDate;

public class PlayerTest {
    private static final String FORWARD = "forward";
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
    public void getPlayerAgeTest() {
        IPlayer player = leagueManagerFactory.createPlayer();
        player.setPlayerAge(20);
        Assert.assertEquals(20, player.getPlayerAge());
    }

    @Test
    public void setPlayerAgeTest() {
        IPlayer player = leagueManagerFactory.createPlayer();
        player.setPlayerAge(46);
        Assert.assertEquals(46, player.getPlayerAge());
    }

    @Test
    public void getElapsedDaysFromLastBDayTest() {
        IPlayer player = leagueManagerFactory.createPlayer();
        player.setElapsedDaysFromLastBDay(12);
        Assert.assertEquals(12, player.getElapsedDaysFromLastBDay());
    }

    @Test
    public void setElapsedDaysFromLastBDayTest() {
        IPlayer player = leagueManagerFactory.createPlayer();
        player.setElapsedDaysFromLastBDay(-1);
        Assert.assertEquals(0, player.getElapsedDaysFromLastBDay());
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
    public void calculateStrength() {
        IPlayer player = leagueManagerFactory.createPlayer();
        IPlayerStats forwardStats = createPlayerStats();

        Assert.assertEquals(21.5, player.calculateStrength(forwardStats), 0.0);
    }

    @Test
    public void isValidTest() {
        IPlayer player = leagueManagerFactory.createPlayer();
        Assert.assertFalse(player.isValid());
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
        player.setPlayerAge(51);

        IAgingConfig agingConfig = leagueManagerFactory.createAgingConfig();
        agingConfig.setMaximumAge(50);
        agingConfig.setAverageRetirementAge(35);

        Assert.assertTrue(playerCareerProgression.isRetired(player, agingConfig, LocalDate.of(2020, 10, 29)));

    }

    @Test
    public void agePlayerByDaysTest() {
        IPlayer player1 = leagueManagerFactory.createPlayer();
        player1.setPlayerAge(30);
        player1.agePlayerByDays(40);
        Assert.assertEquals(30, player1.getPlayerAge());
        Assert.assertEquals(40, player1.getElapsedDaysFromLastBDay());

        IPlayer player2 = leagueManagerFactory.createPlayer();
        player2.setPlayerAge(32);
        player2.agePlayerByDays(397);
        Assert.assertEquals(33, player2.getPlayerAge());
        Assert.assertEquals(32, player2.getElapsedDaysFromLastBDay());

        IPlayer player3 = leagueManagerFactory.createPlayer();
        player3.setPlayerAge(26);
        player3.agePlayerByDays(365);
        Assert.assertEquals(27, player3.getPlayerAge());
        Assert.assertEquals(0, player3.getElapsedDaysFromLastBDay());
    }

    private IPlayerStats createPlayerStats() {
        IPlayerStats stats = leagueManagerFactory.createPlayerStats();
        stats.setSaving(9);
        stats.setChecking(11);
        stats.setShooting(8);
        stats.setSkating(8);
        stats.setPosition(FORWARD);
        return stats;
    }
}
