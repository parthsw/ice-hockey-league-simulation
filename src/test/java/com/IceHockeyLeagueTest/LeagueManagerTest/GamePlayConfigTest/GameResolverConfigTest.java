package com.IceHockeyLeagueTest.LeagueManagerTest.GamePlayConfigTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IGameResolverConfig;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class GameResolverConfigTest {
    private static ILeagueManagerFactory leagueManagerFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactoryTest());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
    }

    @Test
    public void setRandomWinChanceTest() {
        IGameResolverConfig resolverConfig = leagueManagerFactory.createGameResolverConfig();
        resolverConfig.setRandomWinChance(0.07f);
        Assert.assertEquals(0.07f, resolverConfig.getRandomWinChance(), 0.0);
    }

    @Test
    public void getRandomWinChanceTest() {
        IGameResolverConfig resolverConfig = leagueManagerFactory.createGameResolverConfig();
        resolverConfig.setRandomWinChance(0.90f);
        Assert.assertEquals(0.90f, resolverConfig.getRandomWinChance(), 0.0);
    }
}
