package com.IceHockeyLeagueTest.LeagueManagerTest.PlayerTest;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.IRandomChance;
import com.IceHockeyLeagueTest.LeagueManagerTest.TestLeagueManagerFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;

public class RandomChanceTest {

    @BeforeClass
    public static void setup() {
        AbstractLeagueManagerFactory.setFactory(new TestLeagueManagerFactory());
    }

    @Test
    public void getRandomFloatNumberTest() {
        IRandomChance randomChance = AbstractLeagueManagerFactory.getFactory().getRandomChance();
        float randomNumber = randomChance.getRandomFloatNumber(0, 30);

        Assert.assertTrue(randomNumber >= 0 && randomNumber <= 30);
        Assert.assertEquals(-1, randomChance.getRandomFloatNumber(23, 20), 0.0);
    }

    @Test
    public void roundFloatNumberTest() {
        IRandomChance randomChance = AbstractLeagueManagerFactory.getFactory().getRandomChance();
        float roundedNumber = randomChance.roundFloatNumber(23.4567f, 3);

        BigDecimal bigDecimal = new BigDecimal(Float.toString(roundedNumber));
        Assert.assertEquals(3, bigDecimal.stripTrailingZeros().scale());
    }

    @Test
    public void getRandomIntegerNumberTest() {
        IRandomChance randomChance = AbstractLeagueManagerFactory.getFactory().getRandomChance();
        int randomNumber = randomChance.getRandomIntegerNumber(3, 56);
        Assert.assertTrue(randomNumber >= 3 && randomNumber <= 56);

        randomNumber = randomChance.getRandomIntegerNumber(1,2);
        Assert.assertTrue(randomNumber >= 1 && randomNumber <= 2);

        Assert.assertEquals(-1, randomChance.getRandomIntegerNumber(4, 1), 0.0);
    }
}
