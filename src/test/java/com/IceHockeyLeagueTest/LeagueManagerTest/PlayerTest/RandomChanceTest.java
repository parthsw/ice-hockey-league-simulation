package com.IceHockeyLeagueTest.LeagueManagerTest.PlayerTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.IRandomChance;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;

public class RandomChanceTest {
    private static ILeagueManagerFactory leagueManagerFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
    }

    @Test
    public void getRandomFloatNumberTest() {
        IRandomChance randomChance = leagueManagerFactory.createRandomChance();
        float randomNumber = randomChance.getRandomFloatNumber(0, 30);
        Assert.assertTrue(randomNumber >= 0 && randomNumber <= 30);
    }

    @Test
    public void getRandomFloatNumberInvalidInputTest() {
        IRandomChance randomChance = leagueManagerFactory.createRandomChance();
        Assert.assertEquals(-1, randomChance.getRandomFloatNumber(23, 20), 0.0);
    }

    @Test
    public void roundFloatNumberTest() {
        IRandomChance randomChance = leagueManagerFactory.createRandomChance();
        float roundedNumber = randomChance.roundFloatNumber(23.4567f, 3);
        BigDecimal bigDecimal = new BigDecimal(Float.toString(roundedNumber));
        Assert.assertEquals(3, bigDecimal.stripTrailingZeros().scale());
    }

    @Test
    public void getRandomIntegerNumberTest() {
        IRandomChance randomChance = leagueManagerFactory.createRandomChance();
        int randomNumber = randomChance.getRandomIntegerNumber(3, 56);
        Assert.assertTrue(randomNumber >= 3 && randomNumber <= 56);
        randomNumber = randomChance.getRandomIntegerNumber(1,2);
        Assert.assertTrue(randomNumber >= 1 && randomNumber <= 2);
    }

    @Test
    public void getRandomIntegerNumberInvalidInputTest() {
        IRandomChance randomChance = leagueManagerFactory.createRandomChance();
        Assert.assertEquals(-1, randomChance.getRandomIntegerNumber(4, 1), 0.0);
    }
}
