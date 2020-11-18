package com.IceHockeyLeagueTest.LeagueManagerTest.PlayerTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerAgeInfo;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

public class PlayerAgeInfoTest {
    private static final LocalDate CURRENT_DATE = LocalDate.of(2020, Month.NOVEMBER, 17);
    private static ILeagueManagerFactory leagueManagerFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
    }

    @Test
    public void getAgeInYearsTest() {
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();
        playerAgeInfo.setAgeInYears(20);
        Assert.assertEquals(20, playerAgeInfo.getAgeInYears());
    }

    @Test
    public void setAgeInYearsTest() {
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();
        playerAgeInfo.setAgeInYears(46);
        Assert.assertEquals(46, playerAgeInfo.getAgeInYears());
    }

    @Test
    public void getElapsedDaysFromLastBDayTest() {
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();
        playerAgeInfo.setElapsedDaysFromLastBDay(12);
        Assert.assertEquals(12, playerAgeInfo.getElapsedDaysFromLastBDay());
    }

    @Test
    public void setElapsedDaysFromLastBDayTest() {
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();
        playerAgeInfo.setElapsedDaysFromLastBDay(-1);
        Assert.assertEquals(0, playerAgeInfo.getElapsedDaysFromLastBDay());
    }

    @Test
    public void getBirthDateTest() {
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();
        playerAgeInfo.setBirthDate(LocalDate.of(1995, Month.OCTOBER, 31));
        Assert.assertEquals(1995, playerAgeInfo.getBirthDate().getYear());
    }

    @Test
    public void setBirthDateTest() {
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();
        playerAgeInfo.setBirthDate(LocalDate.of(2001, Month.NOVEMBER, 12));
        Assert.assertEquals(12, playerAgeInfo.getBirthDate().getDayOfMonth());
    }

    @Test
    public void isPlayerBirthDayTrueTest() {
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();
        playerAgeInfo.setBirthDate(CURRENT_DATE);
        Assert.assertTrue(playerAgeInfo.isPlayerBirthDay(CURRENT_DATE));
    }

    @Test
    public void isPlayerBirthDayFalseTest() {
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();
        playerAgeInfo.setBirthDate(LocalDate.of(2013, Month.OCTOBER, 6));
        Assert.assertFalse(playerAgeInfo.isPlayerBirthDay(CURRENT_DATE));
    }

    @Test
    public void calculatePlayerAgeInYearsTest() {
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();
        playerAgeInfo.setBirthDate(LocalDate.of(1993, Month.JULY, 23));
        Assert.assertEquals(27, playerAgeInfo.calculatePlayerAgeInYears(CURRENT_DATE));
    }

    @Test
    public void calculateElapsedDaysFromLastBDayBeforeDateTest() {
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();
        playerAgeInfo.setBirthDate(LocalDate.of(2001, Month.JANUARY, 7));
        Assert.assertEquals(316, playerAgeInfo.calculateElapsedDaysFromLastBDay(CURRENT_DATE));
    }

    @Test
    public void calculateElapsedDaysFromLastBDayAfterDateTest() {
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();
        playerAgeInfo.setBirthDate(LocalDate.of(2003, Month.DECEMBER, 7));
        Assert.assertEquals(347, playerAgeInfo.calculateElapsedDaysFromLastBDay(CURRENT_DATE));
    }

    @Test
    public void calculateElapsedDaysFromLastBDaySameDateTest() {
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();
        playerAgeInfo.setBirthDate(LocalDate.of(2003, Month.NOVEMBER, 17));
        Assert.assertEquals(0, playerAgeInfo.calculateElapsedDaysFromLastBDay(CURRENT_DATE));
    }

    @Test
    public void agePlayerByDaysOnBDayTest() {
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();
        playerAgeInfo.setBirthDate(LocalDate.of(2000, Month.NOVEMBER, 17));
        playerAgeInfo.setAgeInYears(playerAgeInfo.calculatePlayerAgeInYears(CURRENT_DATE));
        playerAgeInfo.setElapsedDaysFromLastBDay(playerAgeInfo.calculateElapsedDaysFromLastBDay(CURRENT_DATE));

        playerAgeInfo.agePlayerByDays(40, CURRENT_DATE);
        Assert.assertEquals(40, playerAgeInfo.getElapsedDaysFromLastBDay());
    }

    @Test
    public void agePlayerByDaysAfterBDayTest() {
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();
        playerAgeInfo.setBirthDate(LocalDate.of(2000, Month.NOVEMBER, 17));
        playerAgeInfo.setAgeInYears(19);
        playerAgeInfo.setElapsedDaysFromLastBDay(366);

        playerAgeInfo.agePlayerByDays(3, CURRENT_DATE);
        Assert.assertEquals(20, playerAgeInfo.getAgeInYears());
        Assert.assertEquals(3, playerAgeInfo.getElapsedDaysFromLastBDay());
    }

    @Test
    public void agePlayerByDaysBeforeBDayTest() {
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();
        playerAgeInfo.setBirthDate(LocalDate.of(2000, Month.NOVEMBER, 17));
        playerAgeInfo.setAgeInYears(19);
        playerAgeInfo.setElapsedDaysFromLastBDay(363);

        playerAgeInfo.agePlayerByDays(3, CURRENT_DATE);
        Assert.assertEquals(20, playerAgeInfo.getAgeInYears());
        Assert.assertEquals(0, playerAgeInfo.getElapsedDaysFromLastBDay());
    }
}
