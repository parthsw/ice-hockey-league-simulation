package com.IceHockeyLeagueTest.LeagueManagerTest.GamePlayConfigTest;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IGameResolverConfig;
import com.IceHockeyLeagueTest.LeagueManagerTest.TestLeagueManagerFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class GameResolverConfigTest {

    @BeforeClass
    public static void setup() {
        AbstractLeagueManagerFactory.setFactory(new TestLeagueManagerFactory());
    }

    @Test
    public void setRandomWinChanceTest() {
        IGameResolverConfig resolverConfig = AbstractLeagueManagerFactory.getFactory().getGameResolverConfig();
        resolverConfig.setRandomWinChance(0.07f);
        Assert.assertEquals(0.07f, resolverConfig.getRandomWinChance(), 0.0);
    }

    @Test
    public void getRandomWinChanceTest() {
        IGameResolverConfig resolverConfig = AbstractLeagueManagerFactory.getFactory().getGameResolverConfig();
        resolverConfig.setRandomWinChance(0.90f);
        Assert.assertEquals(0.90f, resolverConfig.getRandomWinChance(), 0.0);
    }
}
