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
    public void getElapsedDaysFromLastBDateTest() {
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();
        playerAgeInfo.setElapsedDaysFromLastBDate(12);
        Assert.assertEquals(12, playerAgeInfo.getElapsedDaysFromLastBDate());
    }

    @Test
    public void setElapsedDaysFromLastBDateTest() {
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();
        playerAgeInfo.setElapsedDaysFromLastBDate(-1);
        Assert.assertEquals(0, playerAgeInfo.getElapsedDaysFromLastBDate());
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
    public void isPlayerBirthDateTrueTest() {
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();
        playerAgeInfo.setBirthDate(CURRENT_DATE);
        Assert.assertTrue(playerAgeInfo.isPlayerBirthDate(CURRENT_DATE));
    }

    @Test
    public void isPlayerBirthDateFalseTest() {
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();
        playerAgeInfo.setBirthDate(LocalDate.of(2013, Month.OCTOBER, 6));
        Assert.assertFalse(playerAgeInfo.isPlayerBirthDate(CURRENT_DATE));
    }

    @Test
    public void calculatePlayerAgeInYearsTest() {
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();
        playerAgeInfo.setBirthDate(LocalDate.of(1993, Month.JULY, 23));
        Assert.assertEquals(27, playerAgeInfo.calculatePlayerAgeInYears(CURRENT_DATE));
    }

    @Test
    public void calculateElapsedDaysFromLastBDateBeforeDateTest() {
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();
        playerAgeInfo.setBirthDate(LocalDate.of(2001, Month.JANUARY, 7));
        Assert.assertEquals(316, playerAgeInfo.calculateElapsedDaysFromLastBDate(CURRENT_DATE));
    }

    @Test
    public void calculateElapsedDaysFromLastBDateAfterDateTest() {
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();
        playerAgeInfo.setBirthDate(LocalDate.of(2003, Month.DECEMBER, 7));
        Assert.assertEquals(347, playerAgeInfo.calculateElapsedDaysFromLastBDate(CURRENT_DATE));
    }

    @Test
    public void calculateElapsedDaysFromLastBDateSameDateTest() {
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();
        playerAgeInfo.setBirthDate(LocalDate.of(2003, Month.NOVEMBER, 17));
        Assert.assertEquals(0, playerAgeInfo.calculateElapsedDaysFromLastBDate(CURRENT_DATE));
    }

    @Test
    public void agePlayerByDaysOnBDateTest() {
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();
        playerAgeInfo.setBirthDate(LocalDate.of(2000, Month.NOVEMBER, 17));
        playerAgeInfo.setAgeInYears(playerAgeInfo.calculatePlayerAgeInYears(CURRENT_DATE));
        playerAgeInfo.setElapsedDaysFromLastBDate(playerAgeInfo.calculateElapsedDaysFromLastBDate(CURRENT_DATE));

        playerAgeInfo.agePlayerByDays(40, CURRENT_DATE);
        Assert.assertEquals(40, playerAgeInfo.getElapsedDaysFromLastBDate());
    }

    @Test
    public void agePlayerByDaysAfterBDateTest() {
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();
        playerAgeInfo.setBirthDate(LocalDate.of(2000, Month.NOVEMBER, 17));
        playerAgeInfo.setAgeInYears(19);
        playerAgeInfo.setElapsedDaysFromLastBDate(366);

        playerAgeInfo.agePlayerByDays(3, CURRENT_DATE);
        Assert.assertEquals(20, playerAgeInfo.getAgeInYears());
        Assert.assertEquals(3, playerAgeInfo.getElapsedDaysFromLastBDate());
    }

    @Test
    public void agePlayerByDaysBeforeBDateTest() {
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();
        playerAgeInfo.setBirthDate(LocalDate.of(2000, Month.NOVEMBER, 17));
        playerAgeInfo.setAgeInYears(19);
        playerAgeInfo.setElapsedDaysFromLastBDate(363);

        playerAgeInfo.agePlayerByDays(3, CURRENT_DATE);
        Assert.assertEquals(20, playerAgeInfo.getAgeInYears());
        Assert.assertEquals(0, playerAgeInfo.getElapsedDaysFromLastBDate());
    }

    @Test
    public void getBirthDateForGivenYearLeapYearTest() {
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();
        playerAgeInfo.setBirthDate(LocalDate.of(1996, Month.OCTOBER, 31));

        LocalDate givenYearBirthDay = playerAgeInfo.getBirthDateForGivenYear(2012);
        Assert.assertEquals(2012, givenYearBirthDay.getYear());
    }

    @Test
    public void getBirthDateForGivenYearLeapYearFebruaryTest() {
        IPlayerAgeInfo playerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();
        playerAgeInfo.setBirthDate(LocalDate.of(1996, Month.FEBRUARY, 29));

        LocalDate givenYearBirthDay = playerAgeInfo.getBirthDateForGivenYear(2013);
        Assert.assertEquals(28, givenYearBirthDay.getDayOfMonth());
    }

}
